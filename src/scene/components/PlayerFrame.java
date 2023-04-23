package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import logic.GameLogic;
import player.BasePlayer;
import scene.playingGame.PlayingGameRootPane;

//TODO: adjust the GUI to make it be more beautiful
public class PlayerFrame extends VBox {
	public PlayerFrame(BasePlayer p1) {
		this.setAlignment(Pos.CENTER);
		if (!p1.isAlive()) {
			this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		}
		else if (GameLogic.getInstance().getCurrentPlayer() == p1) {
			this.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
		}
		else {
			this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		}
		this.setPadding(new Insets(20, 20, 20, 20));

//		TODO: get player name and role
		Text title = new Text(String.format("%s (%s) %s", p1.getName(), p1.getClass().getSimpleName(), (p1.isAlive() ? "" : "[Dead]")));
		
		Circle pic = new Circle();
		
		TextStats moneyText = new TextStats("Money:");
		TextStats money = new TextStats(Integer.toString(p1.getMoney()));
		
		TextStats swordText = new TextStats("Sword stats:");
		TextStats swordStats = new TextStats(Integer.toString(p1.getSwordStats()));
		
		TextStats magicText = new TextStats("Magic stats:");
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
		
		Button sendHelpBtn = new Button("Send help");
		sendHelpBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (sendHelpBtn.getText().equals("Send help")) {
					PlayingGameRootPane.addTempPlayer(p1);
					sendHelpBtn.setText("Cancel");
				}
				else if (sendHelpBtn.getText().equals("Cancel")) {
					PlayingGameRootPane.removeTempPlayer(p1);
					sendHelpBtn.setText("Send help");
				}
			}
		});
		
		this.getChildren().addAll(title, pic, statsContainer);
		if (PlayingGameRootPane.isFightingBoss() && p1.isAlive()) {
			if (GameLogic.getInstance().getCurrentPlayer() == p1 && sendHelpBtn.getText().equals("Send help")) {
				PlayingGameRootPane.addTempPlayer(p1);
				sendHelpBtn.setDisable(true);
				sendHelpBtn.setText("Sent");
			}
			this.getChildren().add(sendHelpBtn);
		}
	}
}
