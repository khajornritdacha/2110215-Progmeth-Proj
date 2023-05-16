package application;

import java.io.IOException;

import customException.InvalidValueException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.SelectTurnPane;
import scene.playingGame.PlayingGameRootPane;
//import scene.chooseRole.ChooseRolePane;
import scene.startGame.StartGameRootPane;
import utility.GameState;

public class Main extends Application {
	public static Stage primaryStage;
	
    @Override
    public void start(Stage stage) throws InvalidValueException, IOException, SecurityException{
//    	TODO: remove these lines
//    	String path = new File(".").getCanonicalPath();
//		System.out.println("Path in start game pane: " + path);
    	
    	primaryStage = stage;
    	primaryStage.setTitle("RPG Life");
    	primaryStage.setResizable(false);
    	changeState(GameState.WELCOME_SCENE);
    	primaryStage.show();
    }
    
    public static void changeState(GameState gameState) {
    	if (gameState.equals(GameState.WELCOME_SCENE)) {
    		primaryStage.setScene(new Scene(new StartGameRootPane(), 1024, 720));
    	} else if (gameState.equals(GameState.SELECT_TURN_SCENE)) {
    		primaryStage.setScene(new Scene(new SelectTurnPane(), 1024, 720));    		
    	}
    	else if (gameState.equals(GameState.PLAYING_SCENE)){
    		primaryStage.setScene(new Scene(new PlayingGameRootPane(), 1024, 720));
    	}
    }

    public static void main(String[] args) {
        launch();
    }
}
