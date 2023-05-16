package scene.startGame;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;

// TODO: adjust the GUI to make it be more beautiful
public class StartGameRootPane extends BorderPane{
	private static DisplayPlayerPane displayPlayerPane;
	private static InputPlayerFrame inputPlayerFrame;
	private static Button startGameBtn;
	private static TextField numberOfTurn;
	
	public StartGameRootPane() {
		displayPlayerPane = new DisplayPlayerPane();
		inputPlayerFrame = new InputPlayerFrame();
		
		VBox container = new VBox();

		Image img = new Image(getClass().getResource("../../assets/startGameBg.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		container.setBackground(new Background(bg));
		
		Text numberPlayerText = new Text("Enter number of player (2-4 players) : ");
		numberPlayerText.setFont(new Font(20));
		numberPlayerText.setFill(Color.WHITE);
		numberPlayerText.setTextAlignment(TextAlignment.CENTER);
		
		Text inputTurn = new Text("Enter number of turn (5-30 turns) : ");
		inputTurn.setFont(new Font(20));
		inputTurn.setFill(Color.WHITE);
		
		numberOfTurn = new TextField();
		numberOfTurn.setMaxWidth(200);
		numberOfTurn.textProperty().addListener((observable, oldValue, newValue) -> {
			updateStartGameBtn();
		});		

		startGameBtn = new Button("");
		startGameBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ArrayList<String> playerNames = new ArrayList<String>();
				ArrayList<AddedPlayer> players = displayPlayerPane.getAddedPlayers();
				for (AddedPlayer playerName : players) {
					playerNames.add(playerName.getPlayerName());
				}
				
				GameLogic.getInstance(playerNames, Integer.valueOf(numberOfTurn.getText())).startGame();
				numberOfTurn.setText("");
			}
		});
		startGameBtn.setDisable(true);
		startGameBtn.setMinWidth(382);
		startGameBtn.setMinHeight(195);
		Image img2 = new Image(getClass().getResource("../../assets/startGameBtn.png").toExternalForm(), 382, 195, false, true);
		BackgroundImage bg2 = new BackgroundImage(img2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		startGameBtn.setBackground(new Background(bg2));
		
		HBox inputTurnField = new HBox();
		inputTurnField.getChildren().addAll(inputTurn, numberOfTurn);
		inputTurnField.setAlignment(Pos.CENTER);
		inputTurnField.setPadding(new Insets(20, 20, 20, 20));
		
		container.getChildren().addAll(numberPlayerText, displayPlayerPane, inputPlayerFrame, inputTurnField, startGameBtn);
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(100, 0, 0, 0));
		this.setCenter(container);
	}
	
	public static boolean isValidNumberOfTurn(String textNumber) {
		try {
			int number = Integer.parseInt(textNumber.strip());
			if (5 <= number && number <= 30) {
				return true;
			}
			return false;
		}
		catch (NumberFormatException err) {
			return false;
		}
	}
	
	public static void updateStartGameBtn() {
		if (numberOfTurn.getText().isEmpty() || !isValidNumberOfTurn(numberOfTurn.getText())) {
	    	startGameBtn.setDisable(true);
	    }
	    else if (displayPlayerPane.getNumberOfPlayer() < 2 || displayPlayerPane.getNumberOfPlayer() > 4) {
	    	startGameBtn.setDisable(true);
	    }
	    else {
	    	startGameBtn.setDisable(false);
	    }
	}
	
	public static void reset() {
		displayPlayerPane.reset();
	}
	
	public static DisplayPlayerPane getDisplayPlayerPane() {
		return displayPlayerPane;
	}
	
	public static InputPlayerFrame getInputPlayerFrame() {
		return inputPlayerFrame;
	}
	
	public static Button getStartGamebtn() {
		return startGameBtn;
	}
}
