package customException;

/**
 * Exception raised when value is not valid(outside of valid range)
 */
public class InvalidValueException extends Exception{
	/**
	 * Id of exception
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create new InvalidValueException with error message
	 * @param message error message 
	 */
	public InvalidValueException(String message) {
		super(message);
	}
	
	/**
	 * Create new InvalidValueException with error message and error object
	 * @param message error message
	 * @param err error object
	 */
	public InvalidValueException(String message, Throwable err) {
		super(message, err);
	}
}
