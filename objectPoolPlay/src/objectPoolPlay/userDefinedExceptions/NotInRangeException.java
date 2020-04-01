package objectPoolPlay.userDefinedExceptions;

public class NotInRangeException extends Exception {
	/**
	* Constructs a new exception with specified detail message
	* @param message the detail message
	*/
	public NotInRangeException(String message) {
		super(message);
	}
}
