package action;

import customExecption.InvalidValueExecption;

public interface BaseAction {
	String executeAction() throws InvalidValueExecption;
	
	String toString();
}
