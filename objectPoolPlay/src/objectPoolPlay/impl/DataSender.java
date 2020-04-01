package objectPoolPlay.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.MyLogger.DebugLevel;

public class DataSender implements Runnable{
	private Socket socket = null;
	private DataOutputStream out = null;
	private String line;
	private boolean dataSendStop = false;
	private static int sum =0;
	

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public boolean isDataSendStop() {
		return dataSendStop;
	}

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
			if(socket.isClosed()){
				out.close();
				socket.close();
				return;
			}
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



