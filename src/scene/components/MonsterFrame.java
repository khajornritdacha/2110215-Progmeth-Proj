package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import logic.GameLogic;
import monster.BaseMonster;
import scene.playingGame.PlayingGameRootPane;

//TODO: adjust the GUI to make it be more beautiful
public class MonsterFrame extends VBox {
	
	
	public MonsterFrame(BaseMonster monster) {
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(300);
		this.setPrefWidth(200);
		this.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
		this.setPadding(new Insets(20, 20, 20, 20));
	
		TextStats title = new TextStats(monster.getName());
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("Fight");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameLogic.getInstance().handleFightMonster(monster);
				PlayingGameRootPane.hideSelectableAction();
			}
		});
		
		TextStats moneyText = new TextStats("Money:");
		TextStats money = new TextStats(Integer.toString(monster.getDropMoney()));
		TextStats swordText = new TextStats("Sword Stats:");
		TextStats swordStats = new TextStats(Integer.toString(monster.getSwordStats()));
		TextStats magicText = new TextStats("Magic Stats:");
		TextStats magicStats = new TextStats(Integer.toString(monster.getMagicStats()));
		
		GridPane statsContainer = new GridPane();
		statsContainer.add(moneyText, 0, 0);
		statsContainer.add(money, 1, 0);
		statsContainer.add(swordText, 0, 1);
		statsContainer.add(swordStats, 1, 1);
		statsContainer.add(magicText, 0, 2);
		statsContainer.add(magicStats, 1, 2);
		
		statsContainer.setHgap(10);
		
//		TODO: fix this warning
		statsContainer.setHalignment(moneyText, HPos.RIGHT);
		statsContainer.setHalignment(swordText, HPos.RIGHT);
		statsContainer.setHalignment(magicText, HPos.RIGHT);
		
		this.getChildren().addAll(title, pic, statsContainer, selectBtn);
	}
}
