package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.GameLogic;
import scene.playingGame.PlayingGameRootPane;

public class LearningSwordFrame extends VBox {
	public LearningSwordFrame() {
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(300);
		this.setPrefWidth(200);
		this.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
		this.setPadding(new Insets(20, 20, 20, 20));
		
		TextStats title = new TextStats("Learn Sword");
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("OK");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameLogic.getInstance().handleChooseAction(1);
				PlayingGameRootPane.hideSelectableAction();
			}
		});
		
		this.getChildren().addAll(title, pic, selectBtn);
	}
}
