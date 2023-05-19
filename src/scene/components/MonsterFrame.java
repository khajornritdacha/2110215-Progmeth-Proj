package scene.components;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import monster.BaseMonster;
import monster.Dragon;
import player.Rich;
import scene.playingGame.PlayingGameRootPane;
import utility.GameConfig;
import utility.Utility;

//TODO: adjust the GUI to make it be more beautiful
/**
 * UI for showing monster details
 */
public class MonsterFrame extends Frame {
	/**
	 * Create new monster frame
	 * @param monster monster in this frame
	 */
	public MonsterFrame(BaseMonster monster) {
		super(monster.getColor());
	
		double winRate = 0.0;
		if (monster instanceof Dragon) {
			winRate = Utility.calculateWinRateBoss(PlayingGameRootPane.getTempPlayers(), (Dragon)monster);
		}
		else {
			winRate = Utility.calculateWinRate(GameLogic.getInstance().getCurrentPlayer(), monster);
		}
		
		Text title = new TextStats(String.format("%s #%d", monster.getName(), monster.getLevel()), 20);
		title.setTextAlignment(TextAlignment.CENTER);
		
		Circle pic = new Circle(48);
		pic.setFill(new ImagePattern(monster.getPicture()));
		pic.setEffect(new DropShadow());
		
		Button selectBtn = new Button("Fight with");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (monster instanceof Dragon) {
					if (PlayingGameRootPane.isFightingBoss()) {
						Main.playEffect(GameConfig.TAKE_ACTION);
						GameLogic.getInstance().handleFightBoss(PlayingGameRootPane.getTempPlayers());
						PlayingGameRootPane.hideActions(PlayingGameRootPane.getSelectableActions());
					}
					else {
						PlayingGameRootPane.sendHelpFightingBoss();
					}
				}
				else {
					Main.playEffect(GameConfig.TAKE_ACTION);
					GameLogic.getInstance().handleFightMonster(monster);
					PlayingGameRootPane.hideActions(PlayingGameRootPane.getSelectableActions());
				}
			}
		});
		selectBtn.setDisable(PlayingGameRootPane.isShown() && !PlayingGameRootPane.isFightingBoss());
		
		int dropMoney = monster.getDropMoney();
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Rich) {
			dropMoney *= Rich.moneyMultiplier;
		}
		dropMoney = Utility.calculateExtraBuff(dropMoney);
		
		TextStats swordText = new TextStats("Sword stats:");
		TextStats swordStats = new TextStats(Integer.toString(monster.getSwordStats()));
		TextStats magicText = new TextStats("Magic stats:");
		TextStats magicStats = new TextStats(Integer.toString(monster.getMagicStats()));
		TextStats moneyText = new TextStats("Drop money:");
		TextStats money = new TextStats(Integer.toString(dropMoney));
		TextStats winRateText = new TextStats("Win rate:");
		TextStats winRateStats = new TextStats(String.format("%.2f%%", winRate));
		
		GridPane statsContainer = new GridPane();
		statsContainer.add(swordText, 0, 0);
		statsContainer.add(swordStats, 1, 0);
		statsContainer.add(magicText, 0, 1);
		statsContainer.add(magicStats, 1, 1);
		statsContainer.add(moneyText, 0, 2);
		statsContainer.add(money, 1, 2);
		statsContainer.add(winRateText, 0, 3);
		statsContainer.add(winRateStats, 1, 3);
		
		statsContainer.setHgap(10);
		
//		TODO: fix this warning
		statsContainer.setHalignment(moneyText, HPos.RIGHT);
		statsContainer.setHalignment(swordText, HPos.RIGHT);
		statsContainer.setHalignment(magicText, HPos.RIGHT);
		statsContainer.setHalignment(winRateText, HPos.RIGHT);
		
		this.getChildren().addAll(pic, title, statsContainer, selectBtn);
	}
}
