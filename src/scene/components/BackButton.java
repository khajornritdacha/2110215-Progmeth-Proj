package scene.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Go back button
 */
public class BackButton extends Button{
	private int BACK_BUTTON_SIZE = 81;
	
	/**
	 * Create new go back button
	 */
	public BackButton() {
		Image img2 = new Image(getClass().getResource("../../assets/backArrowScaled.png").toExternalForm(), BACK_BUTTON_SIZE, BACK_BUTTON_SIZE, false, true);
		BackgroundImage bg2 = new BackgroundImage(img2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg2));
		this.setPrefWidth(BACK_BUTTON_SIZE);
		this.setPrefHeight(BACK_BUTTON_SIZE);
		this.setCursor(Cursor.HAND);
	}
}
