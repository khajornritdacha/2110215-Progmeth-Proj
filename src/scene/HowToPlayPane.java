package scene;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import scene.components.BackButton;
import utility.GameState;

/**
 * How to play scene of this game
 */
public class HowToPlayPane extends BorderPane{
	/**
	 * Create new HowToPlay scene
	 */
	public HowToPlayPane() {
		Image img = new Image(getClass().getResource("../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));
		
		Button backBtn = new BackButton();
		backBtn.setOnAction((event) -> {
			Main.changeState(GameState.WELCOME_SCENE);
		});
		this.setTop(backBtn);
	}
}
