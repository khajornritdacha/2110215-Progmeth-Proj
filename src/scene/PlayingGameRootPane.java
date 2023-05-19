package scene;

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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import player.BasePlayer;
import scene.components.ActionFrame;
import scene.components.Deck;
import scene.components.DecoratedButton;
import scene.components.FightingMonsterFrame;
import scene.components.MonsterFrame;
import scene.components.PlayerFrame;
import scene.components.TeamFrame;
import scene.components.TextStats;
import utility.Utility;

//TODO: adjust the GUI to make it be more beautiful
/**
 * Gameplay scene
 */
public class PlayingGameRootPane extends BorderPane {
	private static GridPane playerContainer;
	private static Text currentTurn;
	private static Text explanation;
	private static Button getActionBtn;
	private static Deck selectableActions, showableActions;
	private static ArrayList<BasePlayer> tempPlayers;
	private static boolean isFightBoss, isWaiting, isShown;
	private String tempText = "";
	
		/**
	 * Create new gameplay scene
	 */
	public PlayingGameRootPane() {
		Image img = new Image(getClass().getResource("../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
//		Image img = new Image(getClass().getResource("../../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
//		new File(musicPath).toURI().toString()
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		
		this.setBackground(new Background(bg));
		tempPlayers = new ArrayList<BasePlayer>();
		setIsFightBoss(false);
		setIsWaiting(false);
		setIsShown(false);
		tempPlayers.add(GameLogic.getInstance().getCurrentPlayer());
		
		playerContainer = new GridPane();
		VBox container = new VBox(), upperContainer = new VBox();
		container.setAlignment(Pos.CENTER);
		
		currentTurn = new TextStats("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getNumberOfTurn(), 50);
		currentTurn.setFill(Color.WHITE);
		currentTurn.setTextAlignment(TextAlignment.CENTER);
		
		explanation = new Text("Welcome to the game!");
		explanation.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 25));
		explanation.setFill(Color.WHITE);
		explanation.setTextAlignment(TextAlignment.CENTER);
		explanation.setEffect(new DropShadow());
		
		getActionBtn = new DecoratedButton("Random action", 196, 36, 18);
		getActionBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (getActionBtn.getText().equals("Play again")) {
					GameLogic.getInstance().terminateGame();
				}
				else if (GameLogic.getInstance().getNumberAlivePlayers() <= 1) {
					GameLogic.getInstance().endGame();
					return;
				}
				else if (getActionBtn.getText().equals("(R.I.P.) Go to next player")) {
					getActionBtn.setText("Random action");
					GameLogic.getInstance().goToNextPlayer();
				}
				else if (!GameLogic.getInstance().getCurrentPlayer().isAlive()) {
					System.out.println(GameLogic.getInstance().getCurrentPlayer().getName() + " is dead...");
					setExplanation(GameLogic.getInstance().getCurrentPlayer().getName() + " is dead...");
					setActionBtn("(R.I.P.) Go to next player");
				}
				else if (getActionBtn.getText().equals("Random action")) {
					getActionBtn.setText("Choose 1st action");
					setIsWaiting(true);
					GameLogic.getInstance().handleRandomAction();
				}
				else if (getActionBtn.getText().equals("Choose 1st action")) {
					getActionBtn.setText("Choose 2nd action");
					setIsWaiting(true);
					showChooseActions(selectableActions);
				}
				else if (getActionBtn.getText().equals("Choose 2nd action")) {
					getActionBtn.setText("Go to next player");
					setIsWaiting(true);
					showChooseActions(selectableActions);
				}
				else if (getActionBtn.getText().equals("Go to next player")) {
					getActionBtn.setText("Random action");
					GameLogic.getInstance().goToNextPlayer();
				}
				if (!getActionBtn.getText().equals("Random action") && !getActionBtn.getText().equals("Play again")) {
					setExplanation("");
				}
			}
		});
		
		Button endGameBtn = new DecoratedButton("Exit game", 96, 32, 16);
		endGameBtn.setAlignment(Pos.CENTER);
		endGameBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameLogic.getInstance().terminateGame();
			}
		});
		
		Button showRandomActionsBtn = new DecoratedButton("Show random actions", 188, 32, 16);
		showRandomActionsBtn.setAlignment(Pos.CENTER);
		
		Button showChooseActionsBtn = new DecoratedButton("Show choose actions", 188, 32, 16);
		showChooseActionsBtn.setAlignment(Pos.CENTER);

		Button showMonstersBtn = new DecoratedButton("Show monsters", 128, 32, 16);
		showMonstersBtn.setAlignment(Pos.CENTER);
		
		showRandomActionsBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showRandomActionsBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					showChooseActionsBtn.setDisable(true);
					showMonstersBtn.setDisable(true);
					tempText = explanation.getText();
					setExplanation("");
					setIsShown(true);
					showRandomActionsBtn.setText("Hide random actions");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showRandomActions(showableActions);
				}
				else {
					showChooseActionsBtn.setDisable(false);
					showMonstersBtn.setDisable(false);
					setExplanation(tempText);
					setIsShown(false);
					showRandomActionsBtn.setText("Show random actions");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		
		showChooseActionsBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showChooseActionsBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					showRandomActionsBtn.setDisable(true);
					showMonstersBtn.setDisable(true);
					tempText = explanation.getText();
					setExplanation("");
					setIsShown(true);
					showChooseActionsBtn.setText("Hide choose actions");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showChooseActions(showableActions);
				}
				else {
					showRandomActionsBtn.setDisable(false);
					showMonstersBtn.setDisable(false);
					setExplanation(tempText);
					setIsShown(false);
					showChooseActionsBtn.setText("Show choose actions");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		
		showMonstersBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (showMonstersBtn.getText().substring(0, 4).equals("Show")) {
					if (isShown()) {
						return;
					}
					showRandomActionsBtn.setDisable(true);
					showChooseActionsBtn.setDisable(true);
					tempText = explanation.getText();
					setExplanation("");
					setIsShown(true);
					showMonstersBtn.setText("Hide monsters");
					container.getChildren().remove(selectableActions);
					container.getChildren().add(showableActions);
					showMonsters(showableActions);
				}
				else {
					showRandomActionsBtn.setDisable(false);
					showChooseActionsBtn.setDisable(false);
					setExplanation(tempText);
					setIsShown(false);
					showMonstersBtn.setText("Show monsters");
					hideActions(showableActions);
					container.getChildren().remove(showableActions);
					container.getChildren().add(selectableActions);
				}
			}
		});
		
		selectableActions = new Deck();
		showableActions = new Deck();
		
		scene.components.Menu menu = new scene.components.Menu();
		menu.getChildren().addAll(showRandomActionsBtn, showChooseActionsBtn, showMonstersBtn, endGameBtn);
		
		upperContainer.getChildren().addAll(menu, currentTurn, getActionBtn);
		container.getChildren().addAll(explanation, selectableActions);
		for (Node node : container.getChildren()) {
			container.setMargin(node, new Insets(0, 20, 10, 20));
		}
		updatePlayer();
		this.setTop(upperContainer);
		this.setCenter(container);
		this.setBottom(playerContainer);
		setMargin(playerContainer, new Insets(20, 20, 20, 20));
	}
	
	/**
	 * Show possible random actions
	 * @param deck deck containing actions
	 */
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
	
	/**
	 * Show possible choose actions
	 * @param deck deck containing actions
	 */
	public static void showChooseActions(Deck deck) {
		getActionBtn.setDisable(true);
		deck.getChildren().clear();
		deck.getChildren().add(new ActionFrame(new LearnSword(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new ActionFrame(new LearnMagic(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new ActionFrame(new ScrubFloor(GameLogic.getInstance().getCurrentPlayer()), false));
		deck.getChildren().add(new FightingMonsterFrame());
	}
	
	/**
	 * Show all monsters
	 * @param deck deck containing monsters
	 */
	public static void showMonsters(Deck deck) {
		getActionBtn.setDisable(true);
		deck.getChildren().clear();
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonGoblin()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonSkeleton()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonWizard()));
		deck.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonDragon()));
	}
	
	/**
	 * Show fight boss action
	 */
	public static void showFightBoss() {
		if (!isFightingBoss()) {
			return;
		}
		getActionBtn.setDisable(true);
		selectableActions.getChildren().clear();
		selectableActions.getChildren().add(new TeamFrame(getTempPlayers()));
		selectableActions.getChildren().add(new MonsterFrame(GameLogic.getInstance().summonDragon()));
	}
	
	/**
	 * Show actions that are executed
	 * @param action to execute action
	 */
	public static void showExecutingAction(VBox action) {
		selectableActions.getChildren().clear();
		selectableActions.getChildren().add(action);
	}
	
	/**
	 * Hide actions
	 * @param deck deck to hide actions
	 */
	public static void hideActions(Deck deck) {
		if (!isWaiting()) {
			getActionBtn.setDisable(false);
		}
		deck.getChildren().clear();
	}
	
	/**
	 * Send help in fighting boss
	 */
	public static void sendHelpFightingBoss() {
		setIsFightBoss(true);
		showFightBoss();
		updatePlayer();
	}
	
	/**
	 * Add temporary players (used in calculating stuffs when fighting boss)
	 * @param p1 player to add
	 */
	public static void addTempPlayer(BasePlayer p1) {
		tempPlayers.add(p1);
		showFightBoss();
	}
	
	/**
	 * Remove player from list of players to fight boss
	 * @param p1 player to remove
	 */
	public static void removeTempPlayer(BasePlayer p1) {
		tempPlayers.remove(p1);
		showFightBoss();
	}
	
	/**
	 * Get list of players to fight boss
	 * @return list of players to fight boss
	 */
	public static ArrayList<BasePlayer> getTempPlayers() {
		return tempPlayers;
	}
	
	/**
	 * Clear list of players to fight boss
	 */
	public static void clearTempPlayers() {
		tempPlayers.clear();
	}
	
	/**
	 * Set text of getAction button
	 * @param text new text
	 */
	public static void setActionBtn(String text) {
		getActionBtn.setText(text);
	}
	
	/**
	 * Set isFighting boss state. True if fighting boss, otherwise false
	 * @param state new state
	 */
	public static void setIsFightBoss(boolean state) {
		isFightBoss = state;
	}
	
	/**
	 * Set isFighting boss state. True if fighting boss, otherwise false
	 * @return isFightBoss boolean
	 */
	public static boolean isFightingBoss() {
		return isFightBoss;
	}
	
	/**
	 * Get getAction button
	 * @return getAction button
	 */
	public static Button getActionBtn() {
		return getActionBtn;
	}
	
	/**
	 * Get UI of actions players can choose
	 * @return UI of action's players can choose
	 */
	public static Deck getSelectableActions() {
		return selectableActions;
	}
	
	/**
	 * Get UI of other actions
	 * @return UI of other actions
	 */
	public static Deck getShowableActions() {
		return showableActions;
	}
	
	/**
	 * Set explanation text
	 * @param text new text
	 */
	public static void setExplanation(String text) {
		explanation.setText(text);
	}
	
	/**
	 * Get explanation text
	 * @return explanation text
	 */
	public static Text getExplanation() {
		return explanation;
	}
	
	/**
	 * Set isWaiting
	 * @param state new isWaiting state
	 */
	public static void setIsWaiting(boolean state) {
		isWaiting = state;
	}
	
	/**
	 * IsWaiting status
	 * @return isWaiting
	 */
	public static boolean isWaiting() {
		return isWaiting;
	}
	
	/**
	 * Set isShown boolean
	 * @param state new state
	 */
	public static void setIsShown(boolean state) {
		isShown = state;
	}
	
	/**
	 * True if any shown buttons are click, otherwise false
	 * @return isShown
	 */
	public static boolean isShown() {
		return isShown;
	}
	
	/**
	 * Update player status
	 */
	public static void updatePlayer() {
		playerContainer.getChildren().clear();
		ArrayList<BasePlayer> players = GameLogic.getInstance().getPlayersList();
		for (int i = 0; i < players.size(); i++) {
			playerContainer.add(new PlayerFrame(players.get(i)), i, 0);
		}
		playerContainer.setHgap(50);
		playerContainer.setAlignment(Pos.CENTER);
	}	
	
	/**
	 * Update current turn
	 */
	public static void updateCurrentTurn() {
		currentTurn.setText("Turn : " + GameLogic.getInstance().getCurrentTurn() + "/" + GameLogic.getNumberOfTurn());
	}
}
