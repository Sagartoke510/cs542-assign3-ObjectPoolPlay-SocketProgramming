package objectPoolPlay.impl;

/**
 * The interface {@code Server} is for socket
 */
public interface Server {
	/**
	 * Method for starting server
	 */
	public void startService();
	/**
	 * Method for writing result to output file
	 */
	public void writeToFile();
	/**
	 * Method close resources opened
	 */
	public void close();
}
