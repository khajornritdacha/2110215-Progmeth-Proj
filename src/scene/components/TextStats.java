package scene.components;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Text for showing stats
 */
public class TextStats extends Text{
	/**
	 * Create new text stats
	 * @param text text to show
	 */
	public TextStats(String text) {
		this.setText(text);
		this.setFont(new Font(20));
	}
	
	/**
	 * Create new text stats with custom font size
	 * @param text text to show
	 * @param fontSize font size of text
	 */
	public TextStats(String text, int fontSize) {
		this.setText(text);
		this.setFont(new Font(fontSize));
	}
}
