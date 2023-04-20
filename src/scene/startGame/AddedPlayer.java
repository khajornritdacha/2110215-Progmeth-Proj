package scene.startGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

//TODO: adjust the GUI to make it be more beautiful
public class AddedPlayer extends VBox {
	private String playerName;

	public AddedPlayer(String playerName) {
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		
		Text text = new Text(playerName);
		text.setFont(new Font(20));
		this.setPlayerName(playerName);
		
		Button button = new Button("remove");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				StartGameRootPane.getDisplayPlayerPane().removePlayer(AddedPlayer.this);
			}
		});
		
		this.getChildren().addAll(text, button);
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
