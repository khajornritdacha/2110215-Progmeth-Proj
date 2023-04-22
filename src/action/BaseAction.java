package action;

import customException.InvalidValueException;
import javafx.scene.paint.Color;

public interface BaseAction {
	String executeAction() throws InvalidValueException;
	Color getColor();
	String toString();
	String getDescription();
}
