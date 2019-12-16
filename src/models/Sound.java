package models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private static final String BUTTON_SOUND_URL = "../resources/sound/button_sound.wav";
    private static final String CARD_FLIPPING_SOUND_URL = "../resources/sound/cardflip_sound.wav";
    private static final String BACKGROUND_MUSIC_URL = "../resources/sound/backgroundsong.mp3";

    private static final String BUTTON_SOUND_TYPE = "BUTTON_SOUND";
    private static final String BACKGROUND_MUSIC_TPYE = "BACKGROUND_MUSIC";
    private static final String CARD_FLIPPING_SOUND_TYPE = "CARD_FLIPPING_SOUND";

    private Clip sound;
    private MediaPlayer backgroundMusic ;

    public Sound(String soundType){
        //Creating an "button clicking sound" object
        if (soundType.equals(BUTTON_SOUND_TYPE)){
            try {
                AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(BUTTON_SOUND_URL));
                sound = AudioSystem.getClip();
                sound.open(soundInputStream);
            }catch(Exception e){
                System.out.println("Cannot load button sound");
            }

            //Creating a "background music" object
        } else if (soundType.equals(BACKGROUND_MUSIC_TPYE)) {
            try {
                URL resource = getClass().getResource(BACKGROUND_MUSIC_URL);
                backgroundMusic = new MediaPlayer(new Media(resource.toString()));
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.setVolume(0.1);
                backgroundMusic.play(); //play the music immediately after being loaded

            }catch (Exception e){
                System.out.println("Cannot load background music");
            }

            //Creating a "card flipping sound" object
        } else if (soundType.equals(CARD_FLIPPING_SOUND_TYPE)){
            try {
                AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(CARD_FLIPPING_SOUND_URL));
                sound = AudioSystem.getClip();
                sound.open(soundInputStream);
            }catch(Exception e){
                System.out.println("Cannot load card flip sound");
            }
        }
    }

    //Method for this sound object to make button pressed sound
    public void makeSound(){
        sound.setFramePosition(0);
        sound.start();
    }

    //Method for calling background music to stop playing
    public void pauseBackgroundMusic(){
        backgroundMusic.pause();
    }

    //Method for calling background music to resume playing
    public void resumeBackgroundMusic(){
        backgroundMusic.play();
    }
}
