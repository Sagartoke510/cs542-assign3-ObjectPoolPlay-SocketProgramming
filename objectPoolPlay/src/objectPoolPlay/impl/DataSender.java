package objectPoolPlay.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DataSender implements Runnable{
	private Socket socket = null;
	private DataOutputStream out = null;
	private String line;
	private boolean dataSendStop = false;

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
		try {
			socket = new Socket(ip, port);
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			if(socket.isClosed()){
				out.close();
				socket.close();
				return;
			}
			out.writeUTF(this.getLine());
			if (this.isDataSendStop()) {
				out.close();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}



