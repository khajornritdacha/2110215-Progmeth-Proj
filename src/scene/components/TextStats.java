package scene.components;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextStats extends Text{
	public TextStats(String text) {
		this.setText(text);
		this.setFont(new Font(20));
	}
}
