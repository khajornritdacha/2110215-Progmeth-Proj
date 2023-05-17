package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import scene.playingGame.PlayingGameRootPane;

//TODO: adjust the GUI to make it be more beautiful
/**
 * Fighting monster frame
 */
public class FightingMonsterFrame extends Frame {
	/**
	 * Create new frame when fighting monster
	 */
	public FightingMonsterFrame() {
		super(Color.RED);
	
		Text title = new TextStats("Fight with Monsters", 20);
		Text description = new Text("Fight with monsters (or boss)");
		description.setFont(new Font(12));
		
		Circle pic = new Circle(48);
		pic.setFill(new ImagePattern(GameLogic.getInstance().summonGoblin().getPicture()));
		pic.setEffect(new DropShadow());
		
		Button selectBtn = new Button("Select");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				PlayingGameRootPane.showMonsters(PlayingGameRootPane.getSelectableActions());
			}
		});
		selectBtn.setDisable(PlayingGameRootPane.isShown());
		
		this.getChildren().addAll(pic, title, description, selectBtn);
	}
}
