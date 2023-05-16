package scene.components;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DecoratedText extends Text{
	public DecoratedText(String text, int fontSize) {
		super(text);
		Font font = Font.loadFont(getClass().getResource("../../assets/BreatheFire.ttf").toExternalForm(), fontSize);
		this.setFont(font);
		this.setFill(Color.WHITE);
	}
}
