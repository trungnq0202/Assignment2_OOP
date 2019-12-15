package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.CountDownTimer;
import models.Sound;

public class CountdownTimerController {

    @FXML private ProgressBar countdownProgressBar; //Progress bar displaying the time left to play
    @FXML private Button btnStartPauseGame;         //Start of pause game button
    @FXML private Button btnNewGame;       //Start a new game button
    @FXML private Label minutesLabel;      //Label displaying minutes
    @FXML private Label secondsLabel;      //Label displaying seconds
    @FXML private Label hundthsecsLabel;   //Label displaying hundredth of a second

    private MainController mainController;
    private Sound sound;                    //Sound object for making sound when pressing the button
    private Timeline timeline;              //Timeline object to make countdown timer animation
    private CountDownTimer timer;           //Countdowntimer object from model

    //Injecting maincontroller
    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public CountdownTimerController(){
        timer = new CountDownTimer(2,0,0);
        sound = new Sound("BUTTON_SOUND");
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    //make a countdown at each keyframe of the timeline animation
    private void updateTimer(){
        //If the time is over, call method for handling this lost game
        if (!timer.countDown()) { timeline.stop(); mainController.handleGameLost();}
        //Else update countdown timer view
        setTimerLabelAndProgressBar();
    }

    //Update the current countdown timer values to view labels and progress bar
    private void setTimerLabelAndProgressBar(){
        minutesLabel.setText(String.format("%02d :", timer.getMinutes()));
        secondsLabel.setText(String.format("%02d :", timer.getSeconds()));
        hundthsecsLabel.setText(String.format("%02d", timer.getHundthsecs()));
        countdownProgressBar.setProgress(getTimerProgressBarValue());
    }

    //Get the ratio between time left and the total time to display the time remaining on the progress bar
    private double getTimerProgressBarValue(){
        double timeRemain = timer.getMinutes() * 60 * 60 + timer.getSeconds() * 60 + timer.getHundthsecs();
        return timeRemain / (2 * 60 * 60);
    }

    //Method handling start or pause game button
    @FXML private void btnStartPauseGameHandler(MouseEvent mouseEvent) {
        if (mainController.getIsEnabledSound()) sound.makeSound(); //If the sound is currently enabled, make a "button clicked sound"
        if (!timer.isTimerRunning()){                   //If the timer is not running -> game is being paused
            timer.setTimerRunning(true);                //Mark the timer as now running
            timeline.play();                            //resume updating the timer view
            btnStartPauseGame.setText("Pause");         //Change the "start or pause game button" to "Pause" name
        } else {                //If the timer is currently running -> game is being played
            timer.setTimerRunning(false);   //Mark the timer as now paused
            timeline.pause();               //Pause updating the timer view
            btnStartPauseGame.setText("Start"); //Change the "start or pause game button" to "Start" name
        }
    }

    //Method handling newGame button
    @FXML private void btnNewGameHandler(MouseEvent mouseEvent) {
        if (mainController.getIsEnabledSound()) sound.makeSound(); //If the sound is currently enabled, make a "button clicked sound"
        timeline.stop();                    //stop updating the timer view
        mainController.startNewGame();      //Start a new game
    }

    //Check if the the timer is running or not ? -> game is running or not ?
    public boolean getIsGameRunning(){
        return timer.isTimerRunning();
    }

    //Get the total of seconds elapsed
    public int getTimeElapsed(){
        return 120 - (timer.getMinutes() * 60 + timer.getSeconds());
    }

    //Stop the countdown timer
    public void stopTimer(){
        timeline.stop();
    }

    public void resetTimer(){
        timeline.stop();                    //stop updating the timer view
        timer.setTimerRunning(false);
        timer.setMinutes(2); timer.setSeconds(0); timer.setHundthsecs(0);   //Reset the timer minutes, seconds, hundthsecs value to the beginning
        btnStartPauseGame.setText("Start"); //Change the "start or pause game button" to "Start" name
        setTimerLabelAndProgressBar();      //Update the timer view to be as in the beginning
    }
}
