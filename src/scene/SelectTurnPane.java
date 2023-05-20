package scene;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import scene.components.BackButton;
import scene.components.DecoratedButton;
import scene.components.DecoratedText;
import utility.GameConfig;
import utility.GameState;

/**
 * Choosing turn pane, shown after start game button in welcome scene is clicked
 */
public class SelectTurnPane extends BorderPane{
	private static TextField numberOfTurn;
	private static VBox container;
	private static Text heading;
	private static Button nextBtn;
	private static Button backBtn;
	
	/**
	 * Create new select turn pane
	 */
	public SelectTurnPane() {
		backBtn = new BackButton();
		backBtn.setOnAction((event) -> {
			Main.changeState(GameState.WELCOME_SCENE);
		});
		this.setTop(backBtn);
		Image img = new Image(ClassLoader.getSystemResource("startGameBg.png").toString(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));

		heading = new DecoratedText("Enter Number of Turn (7-30 turns)", 40);
		
		numberOfTurn = new TextField();
		numberOfTurn.setPrefWidth(80);
		numberOfTurn.setMaxWidth(80);
		numberOfTurn.setPrefHeight(60);
		numberOfTurn.setFont(new Font(25));
		numberOfTurn.textProperty().addListener((observable, oldValue, newValue) -> {
			updateNextBtn();
		});					
		
		nextBtn = new DecoratedButton("Next", 320, 80, 40);
		nextBtn.setDisable(true);
		nextBtn.setOnAction((event) -> {
			GameLogic.setNumberOfTurn(Integer.parseInt(numberOfTurn.getText()));
			Main.changeState(GameState.INPUT_PLAYER_SCENE);
		});
		
		container = new VBox();
		container.setPrefSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(30);
		container.getChildren().addAll(heading, numberOfTurn, nextBtn);
		this.setCenter(container);
	}
	
	/**
	 * Check if the entered number is within valid range of number of turns
	 * @param textNumber string of number to be checked
	 * @return True if the number is valid 
	 */
	public static boolean isValidNumberOfTurn(String textNumber) {
		try {
			int number = Integer.parseInt(textNumber.strip());
			if (GameConfig.MINIMUM_TURN <= number && number <= GameConfig.MAXIMUM_TURN) {
				return true;
			}
			return false;
		}
		catch (NumberFormatException err) {
			return false;
		}
	}
	
	/**
	 * Update go to next page button 
	 */
	public static void updateNextBtn() {
		if (numberOfTurn.getText().isEmpty() || !isValidNumberOfTurn(numberOfTurn.getText())) {
	    	nextBtn.setDisable(true);
	    }
	    else {
	    	nextBtn.setDisable(false);
	    }
	}
}
