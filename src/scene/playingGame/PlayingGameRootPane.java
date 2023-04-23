package scene.playingGame;

import java.util.ArrayList;

import action.FightBoss;
import action.FightMonster;
import action.FindMageMaster;
import action.FindSwordMaster;
import action.IsRobbed;
import action.LearnMagic;
import action.LearnSword;
import action.ScrubFloor;
import action.WinLottery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import player.BasePlayer;
import scene.components.ActionFrame;
import scene.components.Deck;
import scene.components.FightingMonsterFrame;
import scene.components.MonsterFrame;
import scene.components.PlayerFrame;
import scene.components.TeamFrame;
import utility.Utility;

//TODO: adjust the GUI to make it be more beautiful
public class PlayingGameRootPane extends BorderPane {
	private static GridPane playerContainer;
	private static VBox container;
	private static Text currentTurn = new Text("0");
	private static Text explanation = new Text("Welcome to the game!!! ^_^");
	private static Button getActionBtn = new Button("start");
	private static Deck selectableActions = new Deck(), showableActions = new Deck();
	private static ArrayList<BasePlayer> tempPlayers = new ArrayList<BasePlayer>();
	private static boolean isFightBoss = false, isWaiting = false, isShown = false;
	
	// TODO: How about throwing exception?
	public PlayingGameRootPane() /*throws InvalidValueExecption*/{		
		container = new VBox();
		playerContainer = new GridPane();
		
		currentTurn = new Text("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getInstance().getNumberOfTurn());
		currentTurn.setFont(new Font(50));
		currentTurn.setTextAlignment(TextAlignment.CENTER);
		
		explanation = new Text("Welcome to the Game!!! ^_^");
		explanation.setFont(new Font(25));
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
				if (getActionBtn.getText().equals("Play Again")) {
					GameLogic.getInstance().terminateGame();
				}
				else if (getActionBtn.getText().equals("(R.I.P.) Go to Next Player")) {
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
					setIsWaiting(true);
					GameLogic.getInstance().handleRandomAction();
				}
				else if (getActionBtn.getText().equals("Choose 1st Action")) {
					getActionBtn.setText("Choose 2nd Action");
					setIsWaiting(true);
					showChooseActions(selectableActions);
				}
				else if (getActionBtn.getText().equals("Choose 2nd Action")) {
					getActionBtn.setText("Go to Next Player");
					setIsWaiting(true);
					showChooseActions(selectableActions);
				}
				else if (getActionBtn.getText().equals("Go to Next Player")) {
					getActionBtn.setText("Random Action");
					GameLogic.getInstance().goToNextPlayer();
				}
			}
		});
		
		Button showRandomActionsBtn = new Button("Show Random Actions");
		showRandomActionsBtn.setAlignment(Pos.CENTER);
		showRandomActionsBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showRandomActionsBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					
					setIsShown(true);
					showRandomActionsBtn.setText("Hide Random Actions");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showRandomActions(showableActions);
				}
				else {
					setIsShown(false);
					showRandomActionsBtn.setText("Show Random Actions");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		Button showChooseActionsBtn = new Button("Show Choose Actions");
		showChooseActionsBtn.setAlignment(Pos.CENTER);
		showChooseActionsBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showChooseActionsBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					
					setIsShown(true);
					showChooseActionsBtn.setText("Hide Choose Actions");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showChooseActions(showableActions);
				}
				else {
					setIsShown(false);
					showChooseActionsBtn.setText("Show Choose Actions");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		Button showMonstersBtn = new Button("Show Monsters");
		showMonstersBtn.setAlignment(Pos.CENTER);
		showMonstersBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showMonstersBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					
					setIsShown(true);
					showMonstersBtn.setText("Hide Monsters");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showMonsters(showableActions);
				}
				else {
					setIsShown(false);
					showMonstersBtn.setText("Show Monsters");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		
		selectableActions = new Deck();
		
		Deck showFrame = new Deck();
		showFrame.getChildren().addAll(getActionBtn, showRandomActionsBtn, showChooseActionsBtn, showMonstersBtn);
		
		container.getChildren().addAll(endGameBtn, currentTurn, explanation, showFrame, selectableActions);
		updatePlayer();
		this.setCenter(container);
		this.setBottom(playerContainer);
	}
	
	public static void showRandomActions(Deck deck) {
		getActionBtn.setDisable(true);
		deck.getChildren().clear();
		VBox deckContainer = new VBox();
		Deck upper = new Deck(), lower = new Deck();
		upper.getChildren().add(new ActionFrame(new FindSwordMaster(GameLogic.getInstance().getCurrentPlayer()), true));
		upper.getChildren().add(new ActionFrame(new FindMageMaster(GameLogic.getInstance().getCurrentPlayer()), true));
		upper.getChildren().add(new ActionFrame(new IsRobbed(GameLogic.getInstance().getCurrentPlayer()), true));
		lower.getChildren().add(new ActionFrame(new WinLottery(GameLogic.getInstance().getCurrentPlayer()), true));
		lower.getChildren().add(new ActionFrame(new FightMonster(GameLogic.getInstance().getCurrentPlayer(), Utility.genRandomMonster(), false), true));
		lower.getChildren().add(new ActionFrame(new FightBoss(GameLogic.getInstance().getCurrentPlayer(), GameLogic.getInstance().summonDragon()), true));
		deckContainer.getChildren().addAll(upper, lower);
		deck.getChildren().add(deckContainer);
	}
	
	public static void showChooseActions(Deck deck) {
		getActionBtn.setDisable(true);
		deck.getChildren().clear();
		deck.getChildren().add(new ActionFrame(new LearnSword(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new ActionFrame(new LearnMagic(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new ActionFrame(new ScrubFloor(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new FightingMonsterFrame());
	}
	
	public static void showMonsters(Deck deck) {
		getActionBtn.setDisable(true);
		deck.getChildren().clear();
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonGoblin()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonSkeleton()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonWizzard()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonDragon()));
	}
	
	public static void showFightBoss() {
		getActionBtn.setDisable(true);
		selectableActions.getChildren().clear();
		selectableActions.getChildren().add(new TeamFrame(getTempPlayers()));
		selectableActions.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonDragon()));
	}
	
	public static void showExecutingAction(VBox action) {
		selectableActions.getChildren().clear();
		selectableActions.getChildren().add(action);
		Utility.sleep(3000);
	}
	
	public static void hideActions(Deck deck) {
		if (!isWaiting()) {
			getActionBtn.setDisable(false);
		}
		deck.getChildren().clear();
	}
	
	public static void sendHelpFightingBoss() {
		setIsFightBoss(true);
		showFightBoss();
		updatePlayer();
	}
	
	public static void addTempPlayer(BasePlayer p1) {
		tempPlayers.add(p1);
		showFightBoss();
	}
	
	public static void removeTempPlayer(BasePlayer p1) {
		tempPlayers.remove(p1);
		showFightBoss();
	}
	
	public static ArrayList<BasePlayer> getTempPlayers() {
		return tempPlayers;
	}
	
	public static void clearTempPlayers() {
		tempPlayers.clear();
	}
	
	public static void setActionBtn(String text) {
		getActionBtn.setText(text);
	}
	
	public static void setIsFightBoss(boolean state) {
		isFightBoss = state;
	}
	
	public static boolean isFightingBoss() {
		return isFightBoss;
	}
	
	public static Button getActionBtn() {
		return getActionBtn;
	}
	
	public static Deck getSelectableActions() {
		return selectableActions;
	}
	
	public static Deck getShowableActions() {
		return showableActions;
	}
	
	public static void setExplanation(String text) {
		explanation.setText(text);
	}
	
	public static void setIsWaiting(boolean state) {
		isWaiting = state;
	}
	
	public static boolean isWaiting() {
		return isWaiting;
	}
	
	public static void setIsShown(boolean state) {
		isShown = state;
	}
	
	public static boolean isShown() {
		return isShown;
	}
	
	// TODO: change this function by using thread
	public static void updatePlayer() {
		playerContainer.getChildren().clear();
		ArrayList<BasePlayer> players = GameLogic.getInstance().getPlayersList();
		for (int i = 0; i < players.size(); i++) {
			playerContainer.add(new PlayerFrame(players.get(i)), i, 0);
		}
		playerContainer.setHgap(50);
		playerContainer.setAlignment(Pos.CENTER);
	}	
	
	public static void updateCurrentTurn() {
		currentTurn.setText("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getInstance().getNumberOfTurn());
	}
}
