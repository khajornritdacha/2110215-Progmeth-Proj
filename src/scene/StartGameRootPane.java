package scene;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scene.components.DecoratedButton;
import utility.GameState;


public class StartGameRootPane extends BorderPane{
	private static Button startGameBtn;
	private static Button howToPlayBtn;
	
	public StartGameRootPane() {
		VBox container = new VBox();

		Image img = new Image(getClass().getResource("../assets/startGameBg.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		container.setBackground(new Background(bg));

    startGameBtn = new DecoratedButton("Start Game", 320, 80, 40);
		startGameBtn.setOnAction((event) -> {
			Main.changeState(GameState.SELECT_TURN_SCENE);
		});
		
		howToPlayBtn = new DecoratedButton("How To Play", 320, 80, 40);
		howToPlayBtn.setOnAction((event) -> {
			Main.changeState(GameState.HOW_TO_PLAY_SCENE);
		});
		
		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(startGameBtn, howToPlayBtn);
		buttonContainer.setSpacing(80);
		buttonContainer.setAlignment(Pos.CENTER);
    
		container.getChildren().addAll(buttonContainer);
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(100, 0, 0, 0));
		this.setCenter(container);
	}

	public static Button getStartGamebtn() {
		return startGameBtn;
	}
}
