package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreController {
    @FXML private Label gameScoreView;
    private MainController mainController; //Communicate with others controller through maincontroller

    //Injecting maincontroller
    public void injectMainController(MainController mainController){this.mainController = mainController;}

    //Update the total score
    public void updateGameScoreView(int lastGameScore){
        int newScore = Integer.parseInt(gameScoreView.getText()) + lastGameScore;
        gameScoreView.setText(Integer.toString(newScore));
    }


}
