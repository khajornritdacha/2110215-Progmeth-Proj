package customException;

public class InvalidValueException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidValueException(String message) {
		super(message);
	}
	
	public InvalidValueException(String message, Throwable err) {
		super(message, err);
	}
}
