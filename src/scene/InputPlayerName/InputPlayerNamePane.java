package scene.InputPlayerName;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.components.BackButton;
import scene.components.DecoratedText;

public class InputPlayerNamePane extends BorderPane{
	public InputPlayerNamePane() {
		Image img = new Image(getClass().getResource("../../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));
		
		BorderPane topbar = new BorderPane();
		
		Button backBtn = new BackButton();
		
		Text heading = new DecoratedText("Enter Player Names", 60);
		
		topbar.setLeft(backBtn);
		topbar.setCenter(heading);
		
		this.setTop(topbar);
	}
}
