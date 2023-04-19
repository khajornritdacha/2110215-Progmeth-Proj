package scene.startGame;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import scene.components.AddedPlayer;
import scene.components.InputPlayerFrame;

// TODO: adjust the GUI to make it be more beautiful
public class DisplayPlayerPane extends HBox {
	private ArrayList<AddedPlayer> players = new ArrayList<AddedPlayer>();
	
    public DisplayPlayerPane() {
    	this.setPrefHeight(50);
    	this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	this.setPadding(new Insets(20, 20, 20, 20));
    	this.setSpacing(10);
    }

    public void addPlayer(AddedPlayer player) {
    	players.add(player);
        getChildren().add(player);
        if (getNumberOfPlayer() >= 2) {
        	StartGameRootPane.getStartGamebtn().setDisable(true);
        }
        if (getNumberOfPlayer() >= 4) {
        	InputPlayerFrame.getAddNewPlayerBtn().setDisable(true);
        }
    }

    public void removePlayer(AddedPlayer player) {
    	players.remove(player);
        getChildren().remove(player);
        if (this.getNumberOfPlayer() < 2) {
        	StartGameRootPane.getStartGamebtn().setDisable(false);
        }
        if (this.getNumberOfPlayer() < 4) {
        	InputPlayerFrame.getAddNewPlayerBtn().setDisable(false);
        }
    }
    
    public int getNumberOfPlayer() {
    	return getChildren().size();
    }
    
    public ArrayList<AddedPlayer> getAddedPlayers() {
    	return this.players;
    }
}
