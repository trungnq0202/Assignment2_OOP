package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {
    @FXML private GridPane mainView;
    @FXML private SoundController soundController;
    @FXML private CardPuzzleController cardPuzzleController;
    @FXML private CountdownTimerController countdownTimerController;
    @FXML private LevelController levelController;
    @FXML private ScoreController scoreController;

    public MainController(){
    }

    //After the fxml files are loaded and all the nested controllers are loaded, this method is called by the program
    @FXML private void initialize(){
        injectMainControllerInNestedControllers();
        startNewGame(); //Start a new game for the user
    }

    public void startNewGame(){
        levelController.promptGetLevelWindow();         //Make a popup window for getting user chosen level
        cardPuzzleController.createNewCardPuzzle();     //Create a new list of card and display on the puzzle view
        promptStartGameInstructionWindow();             //Show game instruction
        countdownTimerController.resetTimer();          //Reset the timer for
    }

    public void injectMainControllerInNestedControllers(){
        cardPuzzleController.injectMainController(this);
        soundController.injectMainController(this);
        countdownTimerController.injectMainController(this);
        levelController.injectMainController(this);
        scoreController.injectMainController(this);
    }

    public boolean getIsEnabledSound(){
        return soundController.getIsSoundEnabled();
    }

    //Get the current game chosen level
    public int getGameLevel(){return levelController.getGameLevel();}


    //Display a startup game instruction for the user to read and follow
    public void promptStartGameInstructionWindow(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Start Game Instruction");
        Label label1 = new Label("Please press the 'Start' button to start playing !");
        Label label2 = new Label("Press the 'Pause' button if you want to pause, then press 'Start' button again to resume playing.");
        Label label3 = new Label("Press the 'New Game' button if you want to play a different game.");
        Button finishBtn = new Button("Ok!");
        finishBtn.setOnAction(e -> {
            popUpWindow.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label2, label3 ,finishBtn);
        layout.setAlignment(Pos.CENTER);
        Scene startGameInstructScene = new Scene(layout,600, 250);
        popUpWindow.setScene(startGameInstructScene);
        popUpWindow.showAndWait();
    }

    //Display game won message, show score and time taken, then ask the user to play another game or quit
    public void promptGameWonWindow(int lastGameScore){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);

        Label congratsMessage = new Label("Congratulations, you won!");

        Label lastGameScoreLabel = new Label("You scored " + lastGameScore + " points in the last game in "
                + countdownTimerController.getTimeElapsed() + "second(s)" );

        Button playAgainBtn = new Button("Play Again");
        Button quitBtn = new Button("Quit");

        playAgainBtn.setOnMouseClicked(e -> {
            popUpWindow.close();
            startNewGame();
        });

        quitBtn.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(playAgainBtn,quitBtn);
        hbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll(congratsMessage,lastGameScoreLabel, hbox);
        layout.setAlignment(Pos.CENTER);
        Scene startGameInstructScene = new Scene(layout,500, 250);
        popUpWindow.setScene(startGameInstructScene);
        popUpWindow.initStyle(StageStyle.UNDECORATED);
        popUpWindow.show();

    }

    //Display game won message, ask the user to play another game or quit
    public void promptGameLostWindow(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);

        Label lostMessage = new Label("Time over, you've lost the game!");

        Button playAgainBtn = new Button("Play Again");
        Button quitBtn = new Button("Quit");

        playAgainBtn.setOnMouseClicked(e -> {
            popUpWindow.close();
            startNewGame();
        });

        quitBtn.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(playAgainBtn,quitBtn);
        hbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll(lostMessage, hbox);
        layout.setAlignment(Pos.CENTER);
        Scene startGameInstructScene = new Scene(layout,500, 250);
        popUpWindow.setScene(startGameInstructScene);
        popUpWindow.initStyle(StageStyle.UNDECORATED);
        popUpWindow.show();
    }

    public boolean getIsGameRunning(){
        return countdownTimerController.getIsGameRunning();
    }


    //Method Handling the case when the user won the game
    public void handleGameWon(){
        int lastGameScore = 120 - countdownTimerController.getTimeElapsed();    //calculate score
        countdownTimerController.stopTimer();       //Stop the timer
        scoreController.updateGameScoreView(lastGameScore); //Update total score
        promptGameWonWindow(lastGameScore);
    }

    //Method Handling the case when the user won the game
    public void handleGameLost(){
        countdownTimerController.stopTimer();       //Stop the timer
        promptGameLostWindow();
    }
}
