package scene.startGame;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import scene.components.AddedPlayer;
import scene.components.InputPlayerFrame;

public class DisplayPlayerPane extends HBox {
    public DisplayPlayerPane() {
    	this.setPrefHeight(50);
    	this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	this.setPadding(new Insets(20, 20, 20, 20));
    	this.setSpacing(10);
    }

    public void addPlayer(AddedPlayer player) {
        getChildren().add(player);
        if (getNumberOfPlayer() >= 2) {
        	StartGameRootPane.getStartGamebtn().setDisable(true);
        }
        if (getNumberOfPlayer() >= 4) {
        	InputPlayerFrame.getAddNewPlayerBtn().setDisable(true);
        }
    }

    public void removePlayer(AddedPlayer player) {
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
}
