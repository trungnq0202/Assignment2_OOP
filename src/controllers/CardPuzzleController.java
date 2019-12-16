package controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import models.PlayerCard;
import models.PlayerCardList;
import models.Sound;

import java.util.Timer;
import java.util.TimerTask;

public class CardPuzzleController {

    @FXML private GridPane playerCardPuzzle;
    private MainController mainController;      //Communicate with others controller through maincontroller
    private Sound cardFlipSound;                //Sound object for making card flipping sound
    private PlayerCardList playerCardList;      //List of playerCard objects
    private int cardFlippedCount;               //Maximum numbers of card can be flipped at a time
    private PlayerCard prevCardFlipped;         //Previous card being flipped, for comparing with the card being flipped right now
    private int pairMatchedCount;               //Count number of pair matched

    //Injecting maincontroller
    public void injectMainController(MainController mainController){this.mainController = mainController;}

    @FXML public void initialize(){}

    public CardPuzzleController(){
        cardFlipSound = new Sound("CARD_FLIPPING_SOUND");
        playerCardList = null;
        cardFlippedCount = 2;                   //Maximum numbers of card can be flipped at a time is 2
        prevCardFlipped = null;
        pairMatchedCount = 0;
    }

    //Create RotateTransition object for card flipping animation
    public RotateTransition createflipAnimation(PlayerCard playerCard){
        RotateTransition animation = new RotateTransition(Duration.millis(300), playerCard);
        animation.setAxis(Rotate.Y_AXIS);
        animation.setFromAngle(0);
        animation.setToAngle(180);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setCycleCount(1);
        return animation;
    }

    //Flip open the playerCard action
    public void flipOpen(PlayerCard playerCard , Runnable action){
        RotateTransition flip = createflipAnimation(playerCard); //Create RotateTransition object
        flip.setOnFinished(e -> action.run()); //After the card is flipped open,if this is the 2nd card being flipped at this time, check if it mactches the prev card being flipped
                                                //if not matched, flip close these 2 cards
        playerCard.setIsFlippedOpen(true);      //Set this card "isFlippedOpen" state
        flip.play();
        if (mainController.checkIsEnabledSound()) cardFlipSound.makeSound(); //If the sound is currently enabled, make a "button clicked sound"
        playerCard.setPlayerImage();
    }

    //Flip close the playerCard action
    public void flipClose(PlayerCard playerCard){
        RotateTransition flip = createflipAnimation(playerCard);
        playerCard.setIsFlippedOpen(false);
        flip.play();
        if (mainController.checkIsEnabledSound()) cardFlipSound.makeSound();
        playerCard.setDefaultImage();
    }

    //Remove the current card list from the puzzle view
    public void removePrevCardList(){
        for (int i = 0; i < playerCardList.getPlayerCardListSize(); i++){
            playerCardPuzzle.getChildren().remove(playerCardList.getPlayerCardByNo(i));
        }
    }

    //Method for creating new card list and put it on the card puzzle view
    public void createNewCardPuzzle(){
        if (playerCardList != null) removePrevCardList();
        pairMatchedCount = 0;
        playerCardList = new PlayerCardList();
        int tempCardNo = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                PlayerCard playerCard = playerCardList.getPlayerCardByNo(tempCardNo);
                setCardFlipEventHandler(playerCard);
                playerCardPuzzle.add(playerCard, col, row);
                tempCardNo++;
            }
        }
    }

    //Set the automatically flip-close time for the card being flipped open
    public void setCardCloseTime(PlayerCard playerCard){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //If (this card is currently flipped open) and (this card is not matched yet)
                if (playerCard.getIsFlippedOpen() && !playerCard.getMatchedStatus()){
                    prevCardFlipped = null;
                    flipClose(playerCard);  //flip close this card
                    cardFlippedCount++;     //Enable the next click (card flipped)
                }
            }
        };
        int delay = (4 - mainController.getGameLevel()) * 1000; //Time set is based on the current game level
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }

    private void setCardFlipEventHandler(PlayerCard playerCard){
        playerCard.setOnMouseClicked((MouseEvent e)->{
            //If (the game is not running) or (the card is already flipped open) or (no more card flipping is allowed)
            if (!mainController.checkIsGameRunning() || playerCard.getIsFlippedOpen() || cardFlippedCount == 0) return; // do nothing

            //Note that 1 click has executed
            cardFlippedCount--;

            //If there is no previous card being flipped
            if (prevCardFlipped == null) {
                prevCardFlipped = playerCard;   //Remember this card as the "previous card being flipped" for comparing with the next flipped open card
                flipOpen(playerCard, ()->{});   //Flip open this card
                setCardCloseTime(playerCard);           //Set timer for this card to close if not successfully matched with the other same card

                //If there is a previous card being flipped to make the comparison
            } else {
                flipOpen(playerCard, () -> {    //Flip this card open
                    if (prevCardFlipped != null && playerCard.getPlayerCardID() == prevCardFlipped.getPlayerCardID()){ //If this card is the same as the previous flipped one
                        prevCardFlipped.setMatchedStatus(true);      //Set these 2 cards' matched status to true so that it wont be automatically flipped close
                        playerCard.setMatchedStatus(true);
                        pairMatchedCount++;                          //Increase the number of pairs have been matched
                        if (pairMatchedCount == 10) mainController.handleGameWon();  //If all the pairs have been matched, handle game win
                    } else { //If this card is no the same as the previous flipped one
                        if (prevCardFlipped != null) flipClose(prevCardFlipped); //Flip close the previous card
                        flipClose(playerCard);      //Flip close this card
                    }
                    prevCardFlipped = null;
                    cardFlippedCount = 2; //Allow for the next 2 click to flip the card
                });
            }
        });
    }
}
