package objectPoolPlay.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.MyLogger.DebugLevel;

/**
 * 
 * This class {@code DataSender} is a Client as well as thread implementation
 * which sends data to server using sockets
 * 
 */
public class DataSender implements Runnable{
	private Socket socket = null;
	private DataOutputStream out = null;
	private String line;
	private boolean dataSendStop = false;
	private static int sum =0;
	
	/**
     * Getter method for private data members
	 * @return String
     */
	public String getLine() {
		return line;
	}
	
	/**
     * Setter method for private data members
     * @param line line read from server
     */
	public void setLine(String line) {
		this.line = line;
	}

	/**
     * Boolean method for checking end of file
	 * @return dataSendStop returns flag
     */
	public boolean isDataSendStop() {
		return dataSendStop;
	}

	/**
     * Setter method for boolean value : sets flag for end of file
     * @param dataSendStop boolean value 
     */
	public void setDataSendStop(boolean dataSendStop) {
		this.dataSendStop = dataSendStop;
	}

	public DataSender(String ip, int port) {
		
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("DataSender Constructor is called", DebugLevel.CONSTRUCTOR);
		
		try {
			socket = new Socket(ip, port);
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		if(MyLogger.debugLevel == DebugLevel.RUN)
			MyLogger.writeMessage("Executing run() of DataSender", DebugLevel.RUN);
		
		try {
			
			out.writeUTF(this.getLine());
			if(!this.getLine().equals("STOP")) {
			sum = sum + Integer.parseInt(line);
			}
			if (this.isDataSendStop()) {
				
				 if(MyLogger.debugLevel == DebugLevel.RELEASE)
						MyLogger.writeMessage("The sum of all prime numbers is: "+ sum, DebugLevel.RELEASE);
				
				out.close();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}



