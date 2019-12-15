package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//object representing each card in the card puzzle, which is a Pane
public class PlayerCard extends Pane {
    private ImageView imageViewer;          //ImageView object for displaying player card image
    private Image defaultImage;             //default image object for to hide the actual player object behind
    private Image playerImage;              //player image object storing the image of U22VN player
    private int playerCardID;               //Card id for comparing 2 cards if it is matched
    private boolean isFlippedOpen;          //Variable checking if the card object is being flipped open revealing the player image
    private boolean matchedStatus;          //Variable marking this card as has been matched

    public PlayerCard(int imageNo){
        playerCardID = imageNo;
        isFlippedOpen = false;              //player image is hide as default
        matchedStatus = false;              //this card is not matched at first
        this.getStyleClass().add("playerCard");     //Add CSS class name for css styling
        defaultImage = new Image("file:src/resources/images/soccerball.png");               //Load the default image from resources
        playerImage = new Image("file:src/resources/images/player"+ Integer.toString(imageNo) +".jpg");   //Load the player image from resource based on imageNo
        imageViewer = new ImageView(defaultImage);  //Create imageViewer object to display image
        setDefaultImage();                          //Set the default image for this card at first, hiding the player image behind
        getChildren().add(imageViewer);             //Add the image viewer object to the Pane
    }

    //Method for displaying default image for this card
    public void setDefaultImage(){
        imageViewer.setImage(defaultImage);
        imageViewer.setFitWidth(80);
        imageViewer.setFitHeight(65);
        imageViewer.setX(33);
        imageViewer.setY(38);
    }

    //Method for displaying player image for this card
    public void setPlayerImage(){
        imageViewer.setImage(playerImage);
        imageViewer.setFitWidth(135.9);
        imageViewer.setFitHeight(142);
        imageViewer.setX(5);
        imageViewer.setY(5);
    }

    public void setMatchedStatus(boolean matchedStatus){
        this.matchedStatus = matchedStatus;
    }

    public boolean getMatchedStatus(){
        return this.matchedStatus;
    }

    public int getPlayerCardID(){
        return playerCardID;
    }

    public boolean getIsFlippedOpen(){
        return isFlippedOpen;
    }

    public void setIsFlippedOpen(boolean isFlippedOpen){
        this.isFlippedOpen = isFlippedOpen;
    }

}
