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

// TODO: implement this GUI
public class MonsterFrame extends VBox {
	public MonsterFrame() {
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(300);
		this.setPrefWidth(200);
		this.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
		this.setPadding(new Insets(20, 20, 20, 20));
	
		TextStats title = new TextStats("Jomzilla");
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("fight");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// TODO: change the value pass through this function to the value of specific monster (3, 4, 5, or 6)
				GameLogic.getInstance().handleChooseAction(3);
				PlayingGameRootPane.hideSelectableAction();
			}
		});
		
		this.getChildren().addAll(title, pic, selectBtn);
	}
}
