package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scene.startGame.StartGameRootPane;

public class Main extends Application {
	
    @Override
    public void start(Stage stage) {
    	Pane root = new StartGameRootPane();
    	Scene scene = new Scene(root, 800, 640);
    	stage.setTitle("RPG Life");
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
