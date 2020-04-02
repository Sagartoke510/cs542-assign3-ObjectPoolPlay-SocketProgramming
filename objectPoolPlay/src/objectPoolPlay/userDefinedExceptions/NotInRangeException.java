package objectPoolPlay.userDefinedExceptions;

/**
* The class {@code NotInRangeException} is a form of exception
*/
public class NotInRangeException extends Exception {
	/**
	* Constructs a new exception with specified detail message
	* @param message the detail message
	*/
	public NotInRangeException(String message) {
		super(message);
	}
}
