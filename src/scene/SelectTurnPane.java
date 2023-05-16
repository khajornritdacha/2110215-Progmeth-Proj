package scene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.components.DecoratedButton;

public class SelectTurnPane extends BorderPane{
	private static TextField numberOfTurn;
	private static VBox container;
	private static Text heading;
	private static Button nextBtn;
	
	private static int MINIMUM_NUMBER_OF_TURN = 7;
	private static int MAXIMUM_NUMBER_OF_TURN = 30;
	
	public SelectTurnPane() {
		Image img = new Image(getClass().getResource("../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));

		Font font = Font.loadFont(getClass().getResource("../assets/BreatheFire.ttf").toExternalForm(), 40);
		heading = new Text("Enter Number of Turn (7-30 turns)");
		heading.setFont(font);
		heading.setFill(Color.WHITE);
		
		numberOfTurn = new TextField();
		numberOfTurn.setMaxWidth(200);
		numberOfTurn.textProperty().addListener((observable, oldValue, newValue) -> {
			updateNextBtn();
		});					
		
		nextBtn = new DecoratedButton("Next", 320, 80, 40);
		nextBtn.setDisable(true);
		
		container = new VBox();
		container.setPrefSize(1024, 720);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(30);
		container.getChildren().addAll(heading, numberOfTurn, nextBtn);
		this.setCenter(container);
	}
	
	public static boolean isValidNumberOfTurn(String textNumber) {
		try {
			int number = Integer.parseInt(textNumber.strip());
			if (MINIMUM_NUMBER_OF_TURN <= number && number <= MAXIMUM_NUMBER_OF_TURN) {
				return true;
			}
			return false;
		}
		catch (NumberFormatException err) {
			return false;
		}
	}
	
	public static void updateNextBtn() {
		if (numberOfTurn.getText().isEmpty() || !isValidNumberOfTurn(numberOfTurn.getText())) {
	    	nextBtn.setDisable(true);
	    }
	    else {
	    	nextBtn.setDisable(false);
	    }
	}
}
