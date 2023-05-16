package scene.InputPlayerName;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import scene.components.BackButton;
import scene.components.DecoratedButton;
import scene.components.DecoratedText;
import utility.GameConfig;
import utility.GameState;

public class InputPlayerNamePane extends BorderPane{
	private static PlayerNameContainer playerNameContainer;
	private static AddPlayerContainer addPlayerContainer;
	private static Button startGameBtn;
	
	public InputPlayerNamePane() {		
		Image img = new Image(getClass().getResource("../../assets/bgPlain.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));
		
		BorderPane topbar = new BorderPane();
		
		Button backBtn = new BackButton();
		backBtn.setOnAction((event) -> {
			Main.changeState(GameState.SELECT_TURN_SCENE);
		});
		
		Text heading = new DecoratedText(String.format("Enter Player Names (%d-%d Players)", GameConfig.MINIMUM_PLAYER, GameConfig.MAXIMUM_PLAYER), 60);
		
		topbar.setLeft(backBtn);
		topbar.setCenter(heading);
		HBox centerContainer = new HBox();
		playerNameContainer = new PlayerNameContainer();
		addPlayerContainer = new AddPlayerContainer();
	
		centerContainer.getChildren().addAll(playerNameContainer, addPlayerContainer);
		
		HBox bottomContainer = new HBox();
		startGameBtn = new DecoratedButton("Start Game", 320, 80, 40);
		startGameBtn.setDisable(true);
		startGameBtn.setOnAction((event) -> {
//			TODO: start game
		});
		bottomContainer.getChildren().add(startGameBtn);
		bottomContainer.setAlignment(Pos.TOP_CENTER);
		bottomContainer.setPrefHeight(125);
		
		this.setBottom(bottomContainer);
		this.setCenter(centerContainer);
		this.setTop(topbar);
	}
	
	public static PlayerNameContainer getPlayerNameContainer() {
		return playerNameContainer;
	}

	public static AddPlayerContainer getAddPlayerContainer() {
		return addPlayerContainer;
	}
	
	public static Button getStartGameBtn() {
		return startGameBtn;
	}
}
