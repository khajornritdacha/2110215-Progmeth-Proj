package scene.startGame;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import scene.components.AddedPlayer;
import scene.components.InputPlayerFrame;

// TODO: adjust the GUI to make it be more beautiful
public class StartGameRootPane extends BorderPane{
	private static DisplayPlayerPane displayPlayerPane;
	private static InputPlayerFrame inputPlayerFrame;
	private static Button startGameBtn;
	
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
		
		TextField numberOfTurn = new TextField();
		numberOfTurn.setMaxWidth(200);
		numberOfTurn.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.isEmpty() || !isValidNumberOfTurn(newValue)) {
		    	startGameBtn.setDisable(true);
		    }
		    else {
		    	startGameBtn.setDisable(false);
		    }
		});

		startGameBtn = new Button("Start Game!!");
		startGameBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ArrayList<String> playerNames = new ArrayList<String>();
				ArrayList<AddedPlayer> players = displayPlayerPane.getAddedPlayers();
				for (AddedPlayer playerName : players) {
					playerNames.add(playerName.getPlayerName());
				}
				GameLogic gameInstance = new GameLogic(playerNames, Integer.valueOf(numberOfTurn.getText()));
				gameInstance.startGame();
			}
		});
		startGameBtn.setDisable(true);
		
		container.getChildren().addAll(gameTitle, startGameBtn, displayPlayerPane, inputPlayerFrame, numberOfTurn);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
	}
	
	public boolean isValidNumberOfTurn(String textNumber) {
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
