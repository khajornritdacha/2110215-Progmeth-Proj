package scene.InputPlayerName;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scene.components.DecoratedText;

public class SinglePlayerName extends HBox {
	private String name;
	
	public SinglePlayerName(String name, int index) {
		this.name = name;

		this.setPrefHeight(40);
		Text playerTitle = new DecoratedText(String.format("Player %d: %s", index, name), 40);
		Button removeBtn = new Button("remove");
		removeBtn.setOnAction((event) -> {
			InputPlayerNamePane.getPlayerNameContainer().removePlayer(this);
		});
		StackPane btnContainer = new StackPane();
		btnContainer.getChildren().add(removeBtn);
		btnContainer.setAlignment(Pos.CENTER_LEFT);
		
		this.setSpacing(20);
		this.getChildren().addAll(btnContainer, playerTitle);
	}
	
	public String getName() {
		return name;
	}
}
