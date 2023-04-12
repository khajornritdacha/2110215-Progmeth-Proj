package scene.startGame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartGameRootPane extends BorderPane{
	
	public StartGameRootPane() {
		VBox container = new VBox();
		Text gameTitle = new Text("RPG Life");
		gameTitle.setFont(new Font(50));
		gameTitle.setTextAlignment(TextAlignment.CENTER);
		
		Text numberPlayerText = new Text("Enter number of player (2-4) players");
		numberPlayerText.setFont(new Font(20));
		numberPlayerText.setTextAlignment(TextAlignment.CENTER);
		
		Button startGameBtn = new Button("Start Game");
		startGameBtn.setDisable(true);
		
		TextField numberOfPlayer = new TextField();
		numberOfPlayer.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (isValidNumberOfPlayer(newValue)) {
		    	startGameBtn.setDisable(false);
		    } else {
		    	startGameBtn.setDisable(true);
		    }
		});
		numberOfPlayer.setMaxWidth(150);
		numberOfPlayer.setAlignment(Pos.CENTER);
		
		container.getChildren().addAll(gameTitle, numberPlayerText, numberOfPlayer, startGameBtn);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
		
	}
	
	public boolean isValidNumberOfPlayer(String textNumber) {
		try {
			int number = Integer.parseInt(textNumber.strip());
			if (2 <= number && number <= 4) return true;
			return false;
		} catch (NumberFormatException err) {
			return false;
		}
	}
}
