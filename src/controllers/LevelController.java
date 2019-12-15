package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LevelController {
    @FXML private Label gameLevelView;
    private MainController mainController; //Communicate with others controller through maincontroller
    private int gameLevel;

    //Injecting maincontroller
    public void injectMainController(MainController mainController){this.mainController = mainController;}

    //Make a popup window to let the user choose the game level
    public void promptGetLevelWindow()  {
        Stage startWindow = new Stage();
        startWindow.initModality(Modality.APPLICATION_MODAL);
        startWindow.setTitle("U22 Vietnam The Matching");

        Label levelChoosingMessage = new Label("Please choose the game level");
        Label levelLabel = new Label("Level: ");
        HBox levelChoosingHbox = new HBox(10);
        levelChoosingHbox.setAlignment(Pos.CENTER);
        ComboBox<Integer> levelBox = new ComboBox<Integer>();

        levelBox.getItems().addAll(1,2,3);
        levelBox.setValue(1);
        levelChoosingHbox.getChildren().addAll(levelLabel, levelBox);
        Button finishBtn = new Button("Done!");

        finishBtn.setOnAction(e -> {
            gameLevel = levelBox.getValue();
            updateGameLevelView();
            startWindow.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(levelChoosingMessage ,levelChoosingHbox, finishBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        startWindow.setScene(scene1);
        startWindow.showAndWait();
    }

    //Get the current game level chosen by the user
    public int getGameLevel(){
        return gameLevel;
    }

    //Update the current game level being chosen to view
    public void updateGameLevelView(){
        gameLevelView.setText(Integer.toString(gameLevel));
    }
}
