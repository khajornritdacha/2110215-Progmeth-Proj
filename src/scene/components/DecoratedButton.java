package scene.components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DecoratedButton extends Button{
	public DecoratedButton(String text, int width, int height, int fontSize) {
		super(text);
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		
		Font buttonFont = Font.loadFont(getClass().getResource("../../assets/BreatheFire.ttf").toExternalForm(), fontSize);
		this.setFont(buttonFont);
		
		this.setTextFill(Color.WHITE);
		
		
		Image buttonBgImg = new Image(getClass().getResource("../../assets/buttonPlain.png").toExternalForm(), width, height, false, true);
		BackgroundImage buttonBg = new BackgroundImage(buttonBgImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(buttonBg));
	}
}
