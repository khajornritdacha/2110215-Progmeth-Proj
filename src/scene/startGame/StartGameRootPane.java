package scene.startGame;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		Text gameTitle = new Text("RPG Life");
		gameTitle.setFont(new Font(50));
		gameTitle.setTextAlignment(TextAlignment.CENTER);

//		String path = new File(".").getCanonicalPath();
//		System.out.println("Path in start game pane: " + path);
//		BackgroundImage bg = new BackgroundImage(new Image("assets/testBg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//		container.setBackground(new Background(bg));
		
		Text numberPlayerText = new Text("Enter number of player (2-4) players");
		numberPlayerText.setFont(new Font(20));
		numberPlayerText.setTextAlignment(TextAlignment.CENTER);
		
		Text inputTurn = new Text("Number of Turn (5-30 turns) : ");
		inputTurn.setFont(new Font(20));
		
		numberOfTurn = new TextField();
		numberOfTurn.setMaxWidth(200);
		numberOfTurn.textProperty().addListener((observable, oldValue, newValue) -> {
			updateStartGameBtn();
		});

		startGameBtn = new Button("Start Game!!");
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
		
		HBox inputTurnField = new HBox();
		inputTurnField.getChildren().addAll(inputTurn, numberOfTurn);
		inputTurnField.setAlignment(Pos.CENTER);
		inputTurnField.setPadding(new Insets(20, 20, 20, 20));
		
		container.getChildren().addAll(gameTitle, numberPlayerText, startGameBtn, displayPlayerPane, inputPlayerFrame, inputTurnField);
		container.setAlignment(Pos.CENTER);
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
