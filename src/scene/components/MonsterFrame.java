package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import monster.BaseMonster;
import monster.Dragon;
import scene.playingGame.PlayingGameRootPane;
import utility.Utility;

//TODO: adjust the GUI to make it be more beautiful
public class MonsterFrame extends Frame {
	public MonsterFrame(BaseMonster monster) {
		super(monster.getColor());
	
		double winRate = 0.0;
		if (monster instanceof Dragon) {
			winRate = Utility.calculateWinRateBoss(PlayingGameRootPane.getTempPlayers(), (Dragon)monster);
		}
		else {
			winRate = Utility.calculateWinRate(GameLogic.getInstance().getCurrentPlayer(), monster);
		}
		TextStats title = new TextStats(monster.getName() + 
				String.format("%s #%d\n(win rate : %.2f%%)", monster.getName(), monster.getLevel(), winRate));
		title.setTextAlignment(TextAlignment.CENTER);
		
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
		selectBtn.setDisable(PlayingGameRootPane.isShown() && !PlayingGameRootPane.isFightingBoss());
		
		TextStats swordText = new TextStats("Sword stats:");
		TextStats swordStats = new TextStats(Integer.toString(monster.getSwordStats()));
		TextStats magicText = new TextStats("Magic stats:");
		TextStats magicStats = new TextStats(Integer.toString(monster.getMagicStats()));
		TextStats moneyText = new TextStats("Drop money:");
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
