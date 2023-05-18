package scene;

import java.util.ArrayList;

import customException.InvalidValueException;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import player.BasePlayer;
import player.Farmer;
import player.Mage;
import player.SwordMan;
import scene.components.PlayerFrame;

public class ChooseRolePane extends BorderPane{
	public ChooseRolePane() throws InvalidValueException{
		GridPane playerContainer = new GridPane();
		
//		TODO: get players data
		ArrayList<BasePlayer> players = new ArrayList<>();
		players.add(new Farmer("Jom1"));
		players.add(new SwordMan("Jom2"));
		players.add(new Mage("Jom3"));
		for (int i = 0; i < players.size(); i++) {
			playerContainer.add(new PlayerFrame(players.get(i)), i, 0);
		}
		playerContainer.setHgap(50);
		playerContainer.setAlignment(Pos.CENTER);
		this.setBottom(playerContainer);
		
	}
}
