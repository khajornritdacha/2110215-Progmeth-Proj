package scene.startGame;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

// TODO: adjust the GUI to make it be more beautiful
public class DisplayPlayerPane extends HBox {
	private ArrayList<AddedPlayer> players = new ArrayList<AddedPlayer>();
	
    public DisplayPlayerPane() {
    	this.setPrefHeight(50);
    	this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	this.setPadding(new Insets(20, 20, 20, 20));
    	this.setAlignment(Pos.CENTER);
    	this.setSpacing(10);
    }

    public void addPlayer(AddedPlayer player) {
    	players.add(player);
        getChildren().add(player);
        StartGameRootPane.updateStartGameBtn();
    }

    public void removePlayer(AddedPlayer player) {
    	players.remove(player);
        getChildren().remove(player);
        StartGameRootPane.updateStartGameBtn();
    }
    
    public void reset() {
    	players.clear();
    	this.getChildren().clear();
    }
    
    public int getNumberOfPlayer() {
    	return getChildren().size();
    }
    
    public ArrayList<AddedPlayer> getAddedPlayers() {
    	return this.players;
    }
}
