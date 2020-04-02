package objectPoolPlay.util;

import java.util.Vector;
/**
 * The interface {@code ResultI} stores result in data structure
 */
public interface ResultI {
	/**
	 * Method to add prime number in data structure
	 * @param num to be added to data structure
	 * @return boolean if integer is prime or  not
	 */
	public boolean addPrime(String num) throws InterruptedException;
	
	/**
	 * Method to send data from data structure to data sender thread
	 */
	public void sendData();
	
	/**
	 * Method to get result data structure
	 * @return Vector that contains all prime numbers
	 */
	public Vector<String> getResultQueue();

}
