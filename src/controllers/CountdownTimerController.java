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
    private Sound btnClickedSound;                    //Sound object for making sound when pressing the button
    private Timeline timeLine;              //Timeline object to make countdown timer animation
    private CountDownTimer countDownTimer;           //Countdowntimer object from model

    //Injecting maincontroller
    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public CountdownTimerController(){
        countDownTimer = new CountDownTimer(2,0,0);
        btnClickedSound = new Sound("BUTTON_SOUND");
        timeLine = new Timeline(new KeyFrame(Duration.millis(16), e -> updateTimer()));
        timeLine.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML private void initialize(){}

    //make a countdown at each keyframe of the timeline animation
    private void updateTimer(){
        //If the time is over, call method for handling this lost game
        if (!countDownTimer.countDown()) { timeLine.stop(); mainController.handleGameLost();}
        //Else update countdown timer view
        setTimerLabelAndProgressBar();
    }

    //Update the current countdown timer values to view labels and progress bar
    private void setTimerLabelAndProgressBar(){
        minutesLabel.setText(String.format("%02d :", countDownTimer.getMinutes()));
        secondsLabel.setText(String.format("%02d :", countDownTimer.getSeconds()));
        hundthsecsLabel.setText(String.format("%02d", countDownTimer.getHundthsecs()));
        countdownProgressBar.setProgress(calculateTimerProgressBarValue());
    }

    //Get the ratio between time left and the total time to display the time remaining on the progress bar
    private double calculateTimerProgressBarValue(){
        double timeRemain = countDownTimer.getMinutes() * 60 * 60 + countDownTimer.getSeconds() * 60 + countDownTimer.getHundthsecs();
        return timeRemain / (2 * 60 * 60);
    }

    //Method handling start or pause game button
    @FXML private void btnStartPauseGameHandler(MouseEvent mouseEvent) {
        if (mainController.checkIsEnabledSound()) btnClickedSound.makeSound(); //If the sound is currently enabled, make a "button clicked sound"
        if (!countDownTimer.isTimerRunning()){                   //If the timer is not running -> game is being paused
            countDownTimer.setTimerRunning(true);                //Mark the timer as now running
            timeLine.play();                            //resume updating the timer view
            btnStartPauseGame.setText("Pause");         //Change the "start or pause game button" to "Pause" name
        } else {                //If the timer is currently running -> game is being played
            countDownTimer.setTimerRunning(false);   //Mark the timer as now paused
            timeLine.pause();               //Pause updating the timer view
            btnStartPauseGame.setText("Start"); //Change the "start or pause game button" to "Start" name
        }
    }

    //Method handling newGame button
    @FXML private void btnNewGameHandler(MouseEvent mouseEvent) {
        if (mainController.checkIsEnabledSound()) btnClickedSound.makeSound(); //If the sound is currently enabled, make a "button clicked sound"
        timeLine.stop();                    //stop updating the timer view
        mainController.startNewGame();      //Start a new game
    }

    //Check if the the timer is running or not ? -> game is running or not ?
    public boolean checkIsGameRunning(){
        return countDownTimer.isTimerRunning();
    }

    //Get the total of seconds elapsed
    public int getTimeElapsed(){
        return 120 - (countDownTimer.getMinutes() * 60 + countDownTimer.getSeconds());
    }

    //Stop the countdown timer
    public void stopTimer(){
        timeLine.stop();
    }

    public void resetTimer(){
        timeLine.stop();                    //stop updating the timer view
        countDownTimer.setTimerRunning(false);
        countDownTimer.setMinutes(2); countDownTimer.setSeconds(0); countDownTimer.setHundthsecs(0);   //Reset the timer minutes, seconds, hundthsecs value to the beginning
        btnStartPauseGame.setText("Start"); //Change the "start or pause game button" to "Start" name
        setTimerLabelAndProgressBar();      //Update the timer view to be as in the beginning
    }
}
