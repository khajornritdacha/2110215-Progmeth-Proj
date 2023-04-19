package application;

import java.io.File;
import java.io.IOException;

import customExecption.InvalidValueExecption;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scene.chooseRole.ChooseRolePane;
import scene.startGame.StartGameRootPane;

public class Main extends Application {
	
    @Override
    public void start(Stage stage) throws InvalidValueExecption, IOException, SecurityException{
    	Pane root = new StartGameRootPane();
    	
//    	TODO: remove these lines
//    	String path = new File(".").getCanonicalPath();
//		System.out.println("Path in start game pane: " + path);
//    	Pane root = new ChooseRolePane();
    	Scene scene = new Scene(root, 800, 640);
    	stage.setTitle("RPG Life");
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void startGame(String name) {
    	System.out.println(name);
//    	new GameLogic(name[])
    }
}
