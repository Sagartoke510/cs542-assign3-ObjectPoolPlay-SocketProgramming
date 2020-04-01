package objectPoolPlay.impl;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.MyLogger.DebugLevel;

public class PersisterService implements Server{
	
	private int port;
	
	private String ofileName;	
	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;
	private static  File ofile;
	private static FileWriter oWriter;
	private String line = "";
	private static Set<String>  result = new HashSet<String>();
	
	public PersisterService(String portIn, String outputFileIn) {
		
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("PersisterService Constructor is called", DebugLevel.CONSTRUCTOR);


		port = Integer.parseInt(portIn);
		ofileName = outputFileIn;
		try {
			if (!Files.exists(Paths.get(ofileName))) {
				ofile = new File(ofileName);

				ofile.createNewFile();
				oWriter = new FileWriter(ofileName, true);

			} else {
				oWriter = new FileWriter(ofileName, true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void startService() {
		
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			
			System.out.println("DataSender connected "+socket);
			
			dis = new DataInputStream(socket.getInputStream());
			
			
			
			while(!(line = dis.readUTF()).equals("STOP")) {	
				
				System.out.println("I am reading: "+line);
				result.add(line);
				
			}
			if (line.equals("STOP")) {
				System.out.println("I am reading:"+line);
				writeToFile();
				close();
				socket.close();
				dis.close();
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void writeToFile() {
		try {
			Iterator<String> i = result.iterator();
			while(i.hasNext()) {
			oWriter.write(i.next() + System.lineSeparator());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void close() {
		try {
			oWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
