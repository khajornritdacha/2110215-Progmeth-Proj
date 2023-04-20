package action;

import customException.InvalidValueException;

public interface BaseAction {
	String executeAction() throws InvalidValueException;
}
