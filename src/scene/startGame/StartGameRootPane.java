package scene.startGame;

import java.util.ArrayList;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import scene.components.DecoratedButton;
import utility.GameState;

// TODO: adjust the GUI to make it be more beautiful
//	TODO: remove unused codes
public class StartGameRootPane extends BorderPane{
//	private static DisplayPlayerPane displayPlayerPane;
//	private static InputPlayerFrame inputPlayerFrame;
	private static Button startGameBtn;
	private static Button howToPlayBtn;
//	private static TextField numberOfTurn;
	
	public StartGameRootPane() {
		VBox container = new VBox();

		Image img = new Image(getClass().getResource("../../assets/startGameBg.png").toExternalForm(), 1024, 720, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		container.setBackground(new Background(bg));

		startGameBtn = new DecoratedButton("Start Game", 320, 80, 40);
		startGameBtn.setOnAction((event) -> {
//			Main.changeState(GameState.SELECT_TURN_SCENE);
			final int NUMBER_TURN = 10; 
			ArrayList<String> playerNames = new ArrayList<String>();
			playerNames.add("JomnoiZ");
			playerNames.add("JO");
			playerNames.add("Puun");
			playerNames.add("Bow");
			GameLogic.getInstance(playerNames, NUMBER_TURN).startGame();
		});
		
		howToPlayBtn = new DecoratedButton("How To Play", 320, 80, 40);
		howToPlayBtn.setOnAction((event) -> {
			Main.changeState(GameState.HOW_TO_PLAY_SCENE);
		});
		
		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(startGameBtn, howToPlayBtn);
		buttonContainer.setSpacing(80);
		buttonContainer.setAlignment(Pos.CENTER);
    
		container.getChildren().addAll(buttonContainer);
		container.setAlignment(Pos.CENTER);
		container.setPadding(new Insets(100, 0, 0, 0));
		this.setCenter(container);
	}
	
//	public static boolean isValidNumberOfTurn(String textNumber) {
//		try {
//			int number = Integer.parseInt(textNumber.strip());
//			if (5 <= number && number <= 30) {
//				return true;
//			}
//			return false;
//		}
//		catch (NumberFormatException err) {
//			return false;
//		}
//	}
	
//	public static void updateStartGameBtn() {
//		if (numberOfTurn.getText().isEmpty() || !isValidNumberOfTurn(numberOfTurn.getText())) {
//	    	startGameBtn.setDisable(true);
//	    }
//	    else if (displayPlayerPane.getNumberOfPlayer() < 2 || displayPlayerPane.getNumberOfPlayer() > 4) {
//	    	startGameBtn.setDisable(true);
//	    }
//	    else {
//	    	startGameBtn.setDisable(false);
//	    }
//	}
//	
//	public static void reset() {
//		displayPlayerPane.reset();
//	}
	
//	public static DisplayPlayerPane getDisplayPlayerPane() {
//		return displayPlayerPane;
//	}
//	
//	public static InputPlayerFrame getInputPlayerFrame() {
//		return inputPlayerFrame;
//	}
//	
	public static Button getStartGamebtn() {
		return startGameBtn;
	}
}
