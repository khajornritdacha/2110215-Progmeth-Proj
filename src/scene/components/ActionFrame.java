package scene.components;

import action.BaseAction;
import action.FightBoss;
import action.IsRobbed;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import logic.GameLogic;
import scene.PlayingGameRootPane;
import utility.Utility;

/**
 * UI show action details
 */
public class ActionFrame extends Frame {
	/**
	 * Create new action frame
	 * @param action action to show inside frame
	 * @param showProb probability 
	 */
	public ActionFrame(BaseAction action, boolean showProb) {
		super(action.getColor());
		
		double prob = Utility.calculateProbDragon();
		if (!(action instanceof FightBoss || action instanceof IsRobbed)) {
			prob = (100 - 2 * prob) / 4;
		}
		Text title = new Text(String.format("%s%s", action.toString(), ((PlayingGameRootPane.isShown() && showProb) ? "(" + prob + "%)" : "")));
		Text description = new Text(action.getDescription());
		
		Circle pic = new Circle();
		
		Button selectBtn = new Button("Select");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameLogic.getInstance().handleAction(action);
				PlayingGameRootPane.hideActions(PlayingGameRootPane.getSelectableActions());
			}
		});
		selectBtn.setDisable(PlayingGameRootPane.isShown());
		
		this.getChildren().addAll(title, pic, description, selectBtn);
	}
}
