package scene;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scene.components.BackButton;
import scene.components.DecoratedButton;
import scene.components.DecoratedText;
import utility.GameConfig;
import utility.GameState;

/**
 * How to play scene of this game
 */
public class HowToPlayPane extends BorderPane{
	/**
	 * Create new HowToPlay scene
	 */
	public HowToPlayPane() {
		Image img = new Image(getClass().getResource("../assets/bgPlain.png").toExternalForm(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));
		
		Button backBtn = new BackButton();
		backBtn.setOnAction((event) -> {
			Main.changeState(GameState.WELCOME_SCENE);
		});
		
		Button howToPlayTitle = new DecoratedButton("How to Play \"RPG Life\"", 496, 96, 40);
		howToPlayTitle.setCursor(Cursor.DEFAULT);
		
		Text basicHowToPlay = new DecoratedText("How to Play (Basic)", 32);
		
		Text basicDescription = new Text("""
				1. Press the "Start Game" button.\n
				2. Enter the number of turns (7-30 turns).\n
				3. Enter the Farmers's name according to the number of Farmers.\n
				4. Press the "Start Game" Button.\n
				5. The first Farmers from the list plays first. (highlighted with orange frame)\n
				6. Get one "Random Actions".\n
				7. Select two "Choose Actions".\n
				8. Switch to the next Farmer who has not been bankrupted yet.\n
				9. If Farmers's money become less than or equal to zero, they will be bankrupted.\n
				10. When all Farmers have played their turn, the current turn will be increased by one.\n
				11. The game ends when :\n
				    - All Farmers are bankrupted.\n
				    - There is only one Farmer left.\n
				    - The number of rounds equals the number of turns.\n
				12. If condition 3 is satisfied, the Farmer who has recieved the highest stats will win the game.\n
			""");
		basicDescription.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 18));
		basicDescription.setFill(Color.WHITE);
		basicDescription.setEffect(new DropShadow());
		basicDescription.setLineSpacing(-8);
		
		Text mechanicsTitle = new DecoratedText("Game Mechanics", 32);

		Text mechanicsDescription = new Text("""
				1. The amount of stats gained from actions will increase every turns.\n
				2. Monters' stats will increase every time:\n
				   - They are killed. (called "Respawn")\n
				   - They win against the players (called "Evolution"), except for Goblins.\n
				   - The turn increases.\n
				3. When fighting the boss, the player can "Send help" to other players,\n 
				   and the money gained or lost will be divided equally among all players in that fight.\n
				4. When the current turn is close to the number of turns,\n
				   the chance of selecting "Robbed Stats" and "Fight the Dragon" will increase.\n
			""");
		mechanicsDescription.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 18));
		mechanicsDescription.setFill(Color.WHITE);
		mechanicsDescription.setEffect(new DropShadow());
		mechanicsDescription.setLineSpacing(-8);
		
		Text roleTitle = new DecoratedText("Player Role", 32);

		Text roleDescription = new Text("""
				1. Farmer : A normal player.\n
				2. Sword Man : A player who is good with a sword. They can:\n
				   - Learn sword skills twice as fast as other roles.\n
				   - Have a higher probability of defeating monsters when they focus solely on sword stats.\n
				3. Wizard : A player who is good with a magic. They can:\n
				   - Learn magic skills twice as fast as other roles.\n
				   - Have a higher probability of defeating monsters when they focus solely on magic stats.\n
				4. The Rich : A player who is good at managing money. They can:\n
				   - Receive or lose money twice as fast as other roles.\n
			""");
		roleDescription.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 18));
		roleDescription.setFill(Color.WHITE);
		roleDescription.setEffect(new DropShadow());
		roleDescription.setLineSpacing(-8);
		
		Text otherDescription = new Text("Note : Additional descriptions about actions and monsters can be found in the gameplay menu.");
		otherDescription.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 18));
		otherDescription.setFill(Color.RED);
		otherDescription.setEffect(new DropShadow());
		otherDescription.setLineSpacing(-8);
		
		VBox container = new VBox();
		container.getChildren().addAll(basicHowToPlay, basicDescription, mechanicsTitle, mechanicsDescription, roleTitle, roleDescription, otherDescription);
		container.setMargin(basicHowToPlay, new Insets(0, 0, 15, 0));
		container.setMargin(mechanicsTitle, new Insets(0, 0, 15, 0));
		container.setMargin(roleTitle, new Insets(0, 0, 15, 0));
		
		ScrollPane scrollPane = new ScrollPane(container);
		scrollPane.setMaxSize(900, 520);
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; "); 
		
		BorderPane topbar = new BorderPane();
		topbar.setLeft(backBtn);
		topbar.setCenter(howToPlayTitle);
		setMargin(howToPlayTitle, new Insets(20, 10, 10, 10));
		
		this.setTop(topbar);
		this.setCenter(scrollPane);
	}
}
