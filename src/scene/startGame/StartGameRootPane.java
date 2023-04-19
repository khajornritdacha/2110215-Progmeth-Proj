package scene.startGame;

import java.io.File;
import java.io.IOException;

import application.Main;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartGameRootPane extends BorderPane{
	
	public StartGameRootPane() throws IOException, SecurityException{
		VBox container = new VBox();
		
//		String path = new File(".").getCanonicalPath();
//		System.out.println("Path in start game pane: " + path);
//		BackgroundImage bg = new BackgroundImage(new Image("assets/testBg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//		container.setBackground(new Background(bg));
		
		TextField p1Name = new TextField();
		
		Text gameTitle = new Text("RPG Life");
		gameTitle.setFont(new Font(50));
		gameTitle.setTextAlignment(TextAlignment.CENTER);
		
		Text numberPlayerText = new Text("Enter number of player (2-4) players");
		numberPlayerText.setFont(new Font(20));
		numberPlayerText.setTextAlignment(TextAlignment.CENTER);
		
		Button startGameBtn = new Button("Start Game");
		startGameBtn.setDisable(true);
		
		startGameBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e) {
				System.out.println(p1Name.getText());
				
				Main.startGame(p1Name.getText());
			}
		});
		
		TextField numberOfPlayer = new TextField();
		numberOfPlayer.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (isValidNumberOfPlayer(newValue)) {
		    	startGameBtn.setDisable(false);
		    } else {
		    	startGameBtn.setDisable(true);
		    }
		});
		numberOfPlayer.setMaxWidth(150);
		numberOfPlayer.setAlignment(Pos.CENTER);
		
		container.getChildren().addAll(gameTitle, numberPlayerText, numberOfPlayer, startGameBtn, p1Name);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
		
	}
	
	public boolean isValidNumberOfPlayer(String textNumber) {
		try {
			int number = Integer.parseInt(textNumber.strip());
			if (2 <= number && number <= 4) return true;
			return false;
		} catch (NumberFormatException err) {
			return false;
		}
	}
}
