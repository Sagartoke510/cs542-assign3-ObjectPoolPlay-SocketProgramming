package objectPoolPlay.impl;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersisterService{
	
	private int port;
	
	private String ofileName;	
	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;
	private static  File ofile;
	private static FileWriter oWriter;
	private String line = "";
	
	public PersisterService(String portIn, String outputFileIn) {

		port = Integer.parseInt(portIn);
		ofileName = outputFileIn;
		try {
			if (!Files.exists(Paths.get(ofileName))) {
				System.out.println("File created successfully");
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
	
	public void startService() {
		
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			
			System.out.println("DataSender connected "+socket);
			
			dis = new DataInputStream(socket.getInputStream());
			
			
			
			while(!(line = dis.readUTF()).equals("STOP")) {	
				
				System.out.println("I am reading: "+line);
				writeToFile(line);
			}
			if (line.equals("STOP")) {
				System.out.println("I am reading:"+line);
				close();
				socket.close();
				dis.close();
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public void writeToFile(String line) {
		try {

			System.out.println("I am in write to file");

			System.out.println(line);
			oWriter.write(line + System.lineSeparator());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void close() {
		try {
			oWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public String toString() {
		return line+"\n";
	}
}
