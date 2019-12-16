
# Assignment2_OOP



RMIT University Vietnam Course: INTE2512 Object-Oriented Programming </br>

Semester: 2019C </br>

Assessment: Assignment 2 </br>

Student Name: Ngo Quang Trung </br>

Student ID: 3742774 </br>

  

## Introduction

This software is a card matching memory game, in which you will have to flip open and match every 2 cards that have the same image of a U22 Vietnam football team. There are a total of 20 cards each time play (10 pairs of cards having the same photo), arranged randomly into a puzzle of 4 rows and 5 columns. There are 3 levels available, level 1 allows the card to be flipped open and reveal the player image for 3 seconds, similarly, 2 seconds for level 2 and 1 second only for level 1. If you successfully match all 10 pairs of cards before the time limited (2 minutes) has elapsed, the score for that game will be announced and added to the total score, or else no score will be given.

  
## Features

* **Display photos**: There are 10 pairs of photos of your 10 U22 Vietnam football players listed randomly in a 4 rows x 5 columns puzzle. By default, the backs of the photos are shown.

* **Cick and turn**: When the user clicks at a player card, it turns and shows the U22 Vietnam football player photo for N seconds (N = 1 for level 1, N= 2 for level 2 and N = 3 for level 3). Maximum 2 photos are clicked and shown at a time. If 2 photos are matched, they stay. Otherwise, each photo turns back after its N seconds elapses.

* **Level**: The user can set the game difficulty level, at the beginning of each game, which restricts the photo showing time. There are 3 levels: (1) N = 3 seconds, (2) N = 2 seconds, (3) N = 1 second. The chosen level for each game is displayed on the top right of the screen

* **Time**: There is a countdown timer at the bottom of the screen, its format is **minutes :** **seconds :** **hundredths of a second** which shows how much time left to finish the game. There is also a progress bar next to it which visualizes the countdown timer.

* **Start/Pause Game or New Game** (**Creative function**) : There are 2 buttons, "**Start**" and "**New Game**", next to the countdown timer. At the start of each game, the user will have to press "**Start**" to be able to play the game, which starts the countdown timer. The "**Start**" then turns into a "**Pause**" button, which can be pressed if you want to pause the game. When the game is paused, no cards is allowed to be flipped. Press "**Start**" button again to resume playing. While playing, if you want to immediately start a new game, please press the "**New Game**" button. 

* **Sound**: There is a sound button at the bottom right of the screen which allows the user to enable or mute the game sound. Initially, the sound is enabled when the game window is open. There are 3 types of sounds in this program, card flipping sound, background music and button clicking sound.

* **Score**: If the user finishes a game in x seconds, he/she receives (120 – x) points. When the user plays several games, the total score is added up and displayed on the top right of the screen.

* **Play again or Quit**: After a game finishes, if the user won that game, the score is announced, or else no score is given. After that, there are 2 button "**Play again**" and "**Quit**" being displayed. Choose "**Play again**" if you want to play another game, or "**Quit**" to exit the software.



  

## Installation

* Open the project using **Intellij IDEA 2019**.

* Setup the JDK to version 11.

### Add the JavaFX library
* From the main menu, select **```File | Project Structure```**  or   **``` Ctrl+Shift+Alt+S```**  on the toolbar.
* Specify the path to the lib folder in the JavaFX SDK package, for example: **``` C:\javafx-sdk-11.0.2\lib ```**
* In the **Choose Modules** dialog, select the **Assignment2_OOP** module  and click **OK**.

### Add VM options
* From the main menu, select **``` Run | Edit Configurations ```**.
* Select **``` Application | Main  ```** from the list on the left.
*In the **VM options** field, specify: 
`--module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.fxml,javafx.media`

Instead of `%PATH_TO_FX%`, specify the path to the JavaFX SDK lib directory, for example: 
**```C:/javafx-sdk-11.0.2/lib```**.

### Compile and Run
* Open class Main.java.

* Press **Shift + F10** or go to **Run** tools and hit **Run 'Main'**.

  

## Known bugs

All exceptions have been handled by printing error message to the console during running the software.

  

## Acknowledgement

* Mr Quang's lecturer slides.

*  Package **javafx.scene.media** : [https://docs.oracle.com/javafx/2/api/javafx/scene/media/package-summary.html](https://docs.oracle.com/javafx/2/api/javafx/scene/media/package-summary.html)
* Class **MediaPlayer** : [https://docs.oracle.com/javafx/2/api/javafx/scene/media/MediaPlayer.html](https://docs.oracle.com/javafx/2/api/javafx/scene/media/MediaPlayer.html)
* Class **AudioInputStream**: [https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioInputStream.html](https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioInputStream.html)

* Class **AudioSystem**: [https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/AudioSystem.html](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/AudioSystem.html)
* Interface **Clip**: [https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)
*  Class **URL**: [https://docs.oracle.com/javase/8/docs/api/java/net/URL.html](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html)
