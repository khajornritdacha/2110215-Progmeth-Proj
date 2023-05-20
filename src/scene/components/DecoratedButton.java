package scene.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Button with medival-styled background image
 */
public class DecoratedButton extends Button{
	/**
	 * Create new decorated button
	 * @param text text inside button
	 * @param width width of this button
	 * @param height height of this button
	 * @param fontSize font size of this button
	 */
	public DecoratedButton(String text, int width, int height, int fontSize) {
		super(text);
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		
		Font buttonFont = Font.loadFont(ClassLoader.getSystemResource("BreatheFire.ttf").toString(), fontSize);
		this.setFont(buttonFont);
		
		this.setTextFill(Color.WHITE);
		this.setCursor(Cursor.HAND);
		
		Image buttonBgImg = new Image(ClassLoader.getSystemResource("buttonPlain.png").toString(), width, height, false, true);
		BackgroundImage buttonBg = new BackgroundImage(buttonBgImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(buttonBg));
	}
}
