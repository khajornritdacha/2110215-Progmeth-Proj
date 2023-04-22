package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import logic.GameLogic;
import monster.BaseMonster;
import monster.Dragon;
import scene.playingGame.PlayingGameRootPane;

//TODO: adjust the GUI to make it be more beautiful
public class MonsterFrame extends Frame {
	public MonsterFrame(BaseMonster monster) {
		super(monster.getColor());
	
		TextStats title = new TextStats(monster.getName() + " #" + monster.getLevel());
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("Fight with");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (monster instanceof Dragon) {
					if (PlayingGameRootPane.isFightingBoss()) {
						GameLogic.getInstance().handleFightBoss(PlayingGameRootPane.getTempPlayers());
						PlayingGameRootPane.hideActions(PlayingGameRootPane.getSelectableActions());
					}
					else {
						PlayingGameRootPane.sendHelpFightingBoss();
					}
				}
				else {
					GameLogic.getInstance().handleFightMonster(monster);
					PlayingGameRootPane.hideActions(PlayingGameRootPane.getSelectableActions());
				}
			}
		});
		selectBtn.setDisable(PlayingGameRootPane.isShown());
		
		TextStats swordText = new TextStats("Sword Stats:");
		TextStats swordStats = new TextStats(Integer.toString(monster.getSwordStats()));
		TextStats magicText = new TextStats("Magic Stats:");
		TextStats magicStats = new TextStats(Integer.toString(monster.getMagicStats()));
		TextStats moneyText = new TextStats("Drop Money:");
		TextStats money = new TextStats(Integer.toString(monster.getDropMoney()));
		
		GridPane statsContainer = new GridPane();
		statsContainer.add(swordText, 0, 0);
		statsContainer.add(swordStats, 1, 0);
		statsContainer.add(magicText, 0, 1);
		statsContainer.add(magicStats, 1, 1);
		statsContainer.add(moneyText, 0, 2);
		statsContainer.add(money, 1, 2);
		
		statsContainer.setHgap(10);
		
//		TODO: fix this warning
		statsContainer.setHalignment(moneyText, HPos.RIGHT);
		statsContainer.setHalignment(swordText, HPos.RIGHT);
		statsContainer.setHalignment(magicText, HPos.RIGHT);
		
		this.getChildren().addAll(title, pic, statsContainer, selectBtn);
	}
}
