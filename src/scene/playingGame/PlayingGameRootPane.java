package scene.playingGame;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import player.BasePlayer;
import scene.components.EarningMoneyFrame;
import scene.components.FightingMonsterFrame;
import scene.components.LearningMagicFrame;
import scene.components.LearningSwordFrame;
import scene.components.MonsterFrame;
import scene.components.PlayerFrame;

//TODO: adjust the GUI to make it be more beautiful
public class PlayingGameRootPane extends BorderPane {
	private static GridPane playerContainer;
	private static VBox container;
	private static Text currentTurn = new Text("0");
	private static Text explanation = new Text("Welcome to the game!!! ^_^");
	private static Button getActionBtn = new Button("start");
	private static HBox selectableAction = new HBox();
	
	// TODO: How about throwing exception?
	public PlayingGameRootPane() /*throws InvalidValueExecption*/{		
		container = new VBox();
		playerContainer = new GridPane();
		
		currentTurn = new Text("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getInstance().getNumberOfTurn());
		currentTurn.setFont(new Font(50));
		currentTurn.setTextAlignment(TextAlignment.CENTER);
		
		explanation = new Text("Welcome to the Game!!! ^_^");
		explanation.setFont(new Font(50));
		explanation.setTextAlignment(TextAlignment.CENTER);
		
		Button endGameBtn = new Button("Exit Game");
		endGameBtn.setAlignment(Pos.TOP_RIGHT);
		endGameBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameLogic.getInstance().terminateGame();
			}
		});
		
		getActionBtn = new Button("Random Action");
		getActionBtn.setAlignment(Pos.CENTER);
		getActionBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (getActionBtn.getText().equals("(R.I.P.) Go to Next Player")) {
					getActionBtn.setText("Random Action");
					GameLogic.getInstance().goToNextPlayer();
				}
				else if (!GameLogic.getInstance().getCurrentPlayer().isAlive()) {
					System.out.println(GameLogic.getInstance().getCurrentPlayer().getName() + " has died...");
					setExplanation(GameLogic.getInstance().getCurrentPlayer().getName() + " has died...");
					setActionBtn("(R.I.P.) Go to Next Player");
				}
				else if (getActionBtn.getText().equals("Random Action")) {
					getActionBtn.setText("Choose 1st Action");
					GameLogic.getInstance().handleRandomAction();
				}
				else if (getActionBtn.getText().equals("Choose 1st Action")) {
					getActionBtn.setText("Choose 2nd Action");
					showSelectableAction(false);
				}
				else if (getActionBtn.getText().equals("Choose 2nd Action")) {
					getActionBtn.setText("Go to Next Player");
					showSelectableAction(false);
				}
				else if (getActionBtn.getText().equals("Go to Next Player")) {
					getActionBtn.setText("Random Action");
					GameLogic.getInstance().goToNextPlayer();
				}
				else if (getActionBtn.getText().equals("Play Again")) {
					GameLogic.getInstance().terminateGame();
				}
			}
		});
		
		selectableAction = new HBox();
		selectableAction.setPadding(new Insets(20, 20, 20, 20));
		selectableAction.setSpacing(20);
		selectableAction.setAlignment(Pos.CENTER);
		
		container.getChildren().addAll(currentTurn, endGameBtn, explanation, getActionBtn, selectableAction, playerContainer);
		updatePlayer();
		this.setCenter(container);
	}
	
	public static void showSelectableAction(boolean isFightingMonster) {
		getActionBtn.setDisable(true);
		if (!isFightingMonster) {
			selectableAction.getChildren().add(new LearningMagicFrame());
			selectableAction.getChildren().add(new LearningSwordFrame());
			selectableAction.getChildren().add(new EarningMoneyFrame());
			selectableAction.getChildren().add(new FightingMonsterFrame());
		}
		else {
			// TODO: design for random monster system for each types of monster
			selectableAction.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonGoblin()));
			selectableAction.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonSkeleton()));
			selectableAction.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonWizzard()));
			selectableAction.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonDragon()));
		}
	}
	
	public static void hideSelectableAction() {
		getActionBtn.setDisable(false);
		selectableAction.getChildren().clear();
	}
	
	public static void setActionBtn(String text) {
		getActionBtn.setText(text);
	}
	
	public static void setExplanation(String text) {
		explanation.setText(text);
	}
	
	// TODO: change this function by using thread
	public static void updatePlayer() {
		container.getChildren().remove(playerContainer);
		
		playerContainer = new GridPane();
		ArrayList<BasePlayer> players = GameLogic.getInstance().getPlayersList();
		for (int i = 0; i < players.size(); i++) {
			playerContainer.add(new PlayerFrame(players.get(i)), i, 0);
		}
		playerContainer.setHgap(50);
		playerContainer.setAlignment(Pos.CENTER);
		
		container.getChildren().add(playerContainer);
	}	
	
	public static void updateCurrentTurn() {
		currentTurn.setText("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getInstance().getNumberOfTurn());
	}
}
