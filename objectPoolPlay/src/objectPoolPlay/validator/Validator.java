package objectPoolPlay.validator;
/**
 * Validator defines an interface to be implemented by 
 * classes that intend to validate input given from command line.
 * @author Abha Chaudhary
 */
public interface Validator {
	/**
	 * Method to run appropriate validator
	 * @throws Exception
	 */
	void run() throws Exception;

}
