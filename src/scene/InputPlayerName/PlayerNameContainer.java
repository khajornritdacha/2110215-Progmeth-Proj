package scene.InputPlayerName;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import utility.GameConfig;

public class PlayerNameContainer extends VBox{
	ArrayList<SinglePlayerName> playerList;
	
	public PlayerNameContainer() {
		this.setPrefWidth(600);
		this.setPrefHeight(300);
		this.setSpacing(30);
		playerList = new ArrayList<SinglePlayerName> ();
		
		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(0, 0, 0, 50));
	}
	
	public void addPlayer(String name) {
		SinglePlayerName newPlayer = new SinglePlayerName(name, playerList.size()+1);
		playerList.add(newPlayer);
		this.getChildren().add(newPlayer);
		if (playerList.size() >= GameConfig.MINIMUM_PLAYER) {
			InputPlayerNamePane.getStartGameBtn().setDisable(false);
		}
	}
	
	public void removePlayer(SinglePlayerName playerName) {
		playerList.remove(playerName);
		this.getChildren().clear();
		ArrayList<SinglePlayerName> newArray = new ArrayList<SinglePlayerName>();
		for (int i = 0; i < playerList.size(); i++) {
			SinglePlayerName newPlayer = new SinglePlayerName(playerList.get(i).getName(), i+1); 
			newArray.add(newPlayer);
			this.getChildren().add(newPlayer);
		}
		this.playerList = newArray;
		
		InputPlayerNamePane.getAddPlayerContainer().refreshAddPlayerBtn();
		if (playerList.size() < GameConfig.MINIMUM_PLAYER) {
			InputPlayerNamePane.getStartGameBtn().setDisable(true);
		}
	}

	public ArrayList<SinglePlayerName> getPlayerList() {
		return playerList;
	}
}
