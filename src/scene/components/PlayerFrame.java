package scene.components;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import player.BasePlayer;

public class PlayerFrame extends VBox {
	private BasePlayer p1;

	public PlayerFrame(BasePlayer p1) {
		this.setAlignment(Pos.CENTER);
	
		this.p1 = p1;
//		TODO: get player name and role
		Text title = new Text(String.format("%s (%s)", p1.getName(), p1.getClass().getSimpleName()));
		
		Circle pic = new Circle();
		
		TextStats moneyText = new TextStats("Money:");
		TextStats money = new TextStats(Integer.toString(p1.getMoney()));
		
		TextStats swordText = new TextStats("Sword Stats:");
		TextStats swordStats = new TextStats(Integer.toString(p1.getSwordStats()));
		
		TextStats magicText = new TextStats("Magic Stats:");
		TextStats magicStats = new TextStats(Integer.toString(p1.getMagicStats()));
		
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
		
		this.getChildren().addAll(title, pic, statsContainer);
	}
}
