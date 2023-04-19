package application;

import customExecption.InvalidValueExecption;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scene.chooseRole.ChooseRolePane;
import scene.startGame.StartGameRootPane;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws InvalidValueExecption{
    	Pane root = new StartGameRootPane();
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
}
