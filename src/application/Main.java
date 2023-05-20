package application;

import java.io.File;
import java.io.IOException;

import customException.InvalidValueException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import scene.HowToPlayPane;
import scene.LoadingPane;
import scene.PlayingGameRootPane;
import scene.SelectTurnPane;
import scene.StartGameRootPane;
import scene.InputPlayerName.InputPlayerNamePane;
import utility.GameConfig;
import utility.GameState;

/**
 * Main class of this game
 */
public class Main extends Application {
	private static boolean isPlayedMusic = false;
	private static MediaPlayer mediaPlayer;
		
	/**
	 * Main Stage of this game
	 */
	public static Stage primaryStage;
	
	/**
	 * JavaFX start Function
	 */
    @Override
    public void start(Stage stage) throws InvalidValueException, IOException, SecurityException{    	
    	primaryStage = stage;
    	primaryStage.setTitle("RPG Life");
    	primaryStage.setResizable(false);
    	changeState(GameState.WELCOME_SCENE);
    	primaryStage.show();
    }
    
    /**
     * Change state(scene) of the game by passing GameState
     * @param gameState new GameState
     * @see GameState
     */
    public static void changeState(GameState gameState) {
    	if (gameState.equals(GameState.WELCOME_SCENE)) {
    		if (!isPlayedMusic()) {
    			playMusic(GameConfig.HOMEPAGE_MUSIC);
    			isPlayedMusic = true;
    		}
    		primaryStage.setScene(new Scene(new StartGameRootPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    	else if (gameState.equals(GameState.SELECT_TURN_SCENE)) {
    		primaryStage.setScene(new Scene(new SelectTurnPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));    		
    	}
    	else if (gameState.equals(GameState.HOW_TO_PLAY_SCENE)) {
    		primaryStage.setScene(new Scene(new HowToPlayPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    	else if (gameState.equals(GameState.INPUT_PLAYER_SCENE)){
    		primaryStage.setScene(new Scene(new InputPlayerNamePane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    	else if (gameState.equals(GameState.LOADING_SCENE)) {
    		stopMusic();
    		primaryStage.setScene(new Scene(new LoadingPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    	else if (gameState.equals(GameState.PLAYING_SCENE)) {
    		stopMusic();
    		playMusic(GameConfig.INGAME_MUSIC);
    		isPlayedMusic = false;
    		primaryStage.setScene(new Scene(new PlayingGameRootPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    }
    
    /**
     * Set effect path and play it
     * @param effectPath Path of the effect
     */
    public static void playEffect(String effectPath) {
    	Media media = new Media(ClassLoader.getSystemResource(effectPath).toString());
    	MediaPlayer effectPlayer = new MediaPlayer(media);
    	effectPlayer.setVolume(0.15);
    	effectPlayer.play(); 
    }
    
    /**
     * Set music path and play it repeatly
     * @param musicPath Path of the music
     */
    public static void playMusic(String musicPath) {
    	Media media = new Media(ClassLoader.getSystemResource(musicPath).toString());
    	mediaPlayer = new MediaPlayer(media);
    	mediaPlayer.setVolume(0.3);
    	mediaPlayer.setOnEndOfMedia(new Runnable() {
    		public void run() {
    			mediaPlayer.seek(Duration.ZERO);
    			mediaPlayer.play();
    		}
    	});
    	mediaPlayer.play();
    }
    
    /**
     * stop playing the current music
     */
    public static void stopMusic() {
    	if (mediaPlayer != null) {
    		mediaPlayer.stop();
    	}
    }
    
    /**
     * Get the status of playing music
     * @return status of playing music
     */
    public static boolean isPlayedMusic() {
    	return isPlayedMusic;
    }

    /**
     * Main Entry Point
     * @param args args string
     */
    public static void main(String[] args) {
        launch();
    }
}
