package customExecption;

public class InvalidValueExecption extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidValueExecption(String message) {
		super(message);
	}
	
	public InvalidValueExecption(String message, Throwable err) {
		super(message, err);
	}
}
