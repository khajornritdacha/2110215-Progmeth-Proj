package scene.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import scene.PlayingGameRootPane;

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
	
		TextStats title = new TextStats("Fight with Monsters");
		TextStats description = new TextStats("Fight with monsters (or boss)", 12);
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("Select");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				PlayingGameRootPane.showMonsters(PlayingGameRootPane.getSelectableActions());
			}
		});
		selectBtn.setDisable(PlayingGameRootPane.isShown());
		
		this.getChildren().addAll(title, pic, description, selectBtn);
	}
}
