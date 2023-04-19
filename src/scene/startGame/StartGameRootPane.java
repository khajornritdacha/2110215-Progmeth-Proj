package scene.startGame;

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
import scene.components.AddedPlayer;
import scene.components.InputPlayerFrame;

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
//		
//		Text numberPlayerText = new Text("Enter number of player (2-4) players");
//		numberPlayerText.setFont(new Font(20));
//		numberPlayerText.setTextAlignment(TextAlignment.CENTER);
//		
		startGameBtn = new Button("Start Game");
		startGameBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// TODO: add player's name to the list in GameLogic
			}
		});
//		
//		TextField numberOfPlayer = new TextField();
//		numberOfPlayer.textProperty().addListener((observable, oldValue, newValue) -> {
//		    if (isValidNumberOfPlayer(newValue)) {
//		    	startGameBtn.setDisable(false);
//		    } else {
//		    	startGameBtn.setDisable(true);
//		    }
//		});
//		numberOfPlayer.setMaxWidth(150);
//		numberOfPlayer.setAlignment(Pos.CENTER);
//		
		container.getChildren().addAll(gameTitle, startGameBtn, displayPlayerPane, inputPlayerFrame);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
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
