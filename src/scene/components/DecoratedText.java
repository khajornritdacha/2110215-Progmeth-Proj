package scene.components;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Text, styled with font, and text color 
 */
public class DecoratedText extends Text{
	/**
	 * Create new decorated text 
	 * @param text text 
	 * @param fontSize font size
	 */
	public DecoratedText(String text, int fontSize) {
		super(text);
		Font font = Font.loadFont(ClassLoader.getSystemResource("BreatheFire.ttf").toString(), fontSize);
		this.setFont(font);
		this.setFill(Color.WHITE);
	}
}
