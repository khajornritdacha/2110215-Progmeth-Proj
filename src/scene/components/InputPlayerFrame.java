package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import scene.startGame.StartGameRootPane;

public class InputPlayerFrame extends HBox {
	private static Button addNewPlayerBtn;
	
	public InputPlayerFrame() {
		this.setPrefHeight(50);
		this.setPrefWidth(300);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		
		TextField textField = new TextField();
		textField.setPrefWidth(200);
		
		Button button = new Button("Add New Player");
		addNewPlayerBtn = button;
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (!textField.getText().isEmpty()) {
					AddedPlayer player = new AddedPlayer(textField.getText());
					StartGameRootPane.getDisplayPlayerPane().addPlayer(player);
					textField.clear();
				}
			}
		});
		
		this.getChildren().addAll(textField, button);
	}
	
	public static Button getAddNewPlayerBtn() {
		return addNewPlayerBtn;
	}
}