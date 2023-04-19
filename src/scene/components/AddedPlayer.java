package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scene.startGame.StartGameRootPane;
import javafx.scene.text.Font;

public class AddedPlayer extends VBox {
	public AddedPlayer(String playerName) {
		this.setSpacing(10);
		
		Text text = new Text("(1) " + playerName);
		text.setFont(new Font(20));
		
		Button button = new Button("remove");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				StartGameRootPane.getDisplayPlayerPane().removePlayer(AddedPlayer.this);
			}
		});
		
		this.getChildren().addAll(text, button);
	}
}
