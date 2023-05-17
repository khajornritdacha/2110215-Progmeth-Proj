package application;

import java.io.IOException;

import customException.InvalidValueException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.HowToPlayPane;
import scene.PlayingGameRootPane;
import scene.SelectTurnPane;
import scene.StartGameRootPane;
import scene.InputPlayerName.InputPlayerNamePane;
import scene.playingGame.PlayingGameRootPane;
//import scene.chooseRole.ChooseRolePane;
import scene.startGame.StartGameRootPane;
import utility.GameConfig;
import utility.GameState;

/**
 * Main class of this game
 */
public class Main extends Application {
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
    	else if (gameState.equals(GameState.PLAYING_SCENE)){
    		primaryStage.setScene(new Scene(new PlayingGameRootPane(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    	}
    }

    /**
     * Main Entry Point
     * @param args args string
     */
    public static void main(String[] args) {
        launch();
    }
}
