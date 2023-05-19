package scene.components;

import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import player.BasePlayer;

/**
 * UI for showing team details
 */
public class TeamFrame extends Frame {
	/**
	 * Create new team frames from array list of players
	 * @param players array list of players in this team
	 */
	public TeamFrame(ArrayList<BasePlayer> players) {
		super(Color.PINK);

		ArrayList<String> playersName = new ArrayList<String>();
		int playersSwordStats = 0, playersMagicStats = 0;
		for (BasePlayer player : players) {
			playersName.add(player.getName());
			playersSwordStats += player.getSwordStats();
			playersMagicStats += player.getMagicStats();
		}
		Text title = new Text("Team : " + String.join(", ", playersName));
		
		Circle pic = new Circle();
		
		TextStats swordText = new TextStats("Sword stats:");
		TextStats swordStats = new TextStats(Integer.toString(playersSwordStats));
		
		TextStats magicText = new TextStats("Magic stats:");
		TextStats magicStats = new TextStats(Integer.toString(playersMagicStats));
		
		GridPane statsContainer = new GridPane();
		statsContainer.add(swordText, 0, 0);
		statsContainer.add(swordStats, 1, 0);
		statsContainer.add(magicText, 0, 1);
		statsContainer.add(magicStats, 1, 1);
		
		statsContainer.setHgap(10);
		
//		TODO: fix this warning
		statsContainer.setHalignment(swordText, HPos.RIGHT);
		statsContainer.setHalignment(magicText, HPos.RIGHT);
		
		this.getChildren().addAll(title, pic, statsContainer);
	}
}
