package scene;

import application.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utility.GameConfig;
import utility.GameState;

/**
 * Loading scene before the game actually starts
 * @author JomnoiZ
 */
public class LoadingPane extends BorderPane{
	/**
	 * Create Loading pane
	 */
	public LoadingPane() {
		Image img = new Image(ClassLoader.getSystemResource("startGameBg.png").toString(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, false, true);
//		Image img = new Image(getClass().getResource("../assets/bgPlain.png").toExternalForm(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, false, true);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		this.setBackground(new Background(bg));
			
		ImageView loading0 = new ImageView();
		loading0.setImage(new Image(ClassLoader.getSystemResource("loading1.png").toString(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, false, true));
		loading0.setFitWidth(600);
		loading0.setFitHeight(80);
		 
		VBox container = new VBox();
		container.getChildren().add(loading0);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
		
		Main.playEffect(GameConfig.TAKE_ACTION);
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);					
					Main.changeState(GameState.PLAYING_SCENE);
				}
				catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		});
	}
}
