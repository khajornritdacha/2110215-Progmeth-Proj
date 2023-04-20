package application;

import java.io.IOException;

import customException.InvalidValueException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.playingGame.PlayingGameRootPane;
//import scene.chooseRole.ChooseRolePane;
import scene.startGame.StartGameRootPane;

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
    	changeState(true);
    	primaryStage.show();
    }
    
    public static void changeState(boolean isStartedScene) {
    	if (isStartedScene) {
    		primaryStage.setScene(new Scene(new StartGameRootPane(), 1024, 720));
    	}
    	else {
    		primaryStage.setScene(new Scene(new PlayingGameRootPane(), 1024, 720));
    	}
    }

    public static void main(String[] args) {
        launch();
    }
}
