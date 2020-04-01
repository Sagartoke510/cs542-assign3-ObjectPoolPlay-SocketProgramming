package objectPoolPlay.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

import java.util.List;

import objectPoolPlay.util.MyLogger.DebugLevel;
/**
 * The class processes input file and reads a line
 * @author Abha Chaudhary
 *
 */
public final class FileProcessor {
	private BufferedReader reader;
	private String line;

	public synchronized String getLine() throws IOException {
		return reader.readLine();
	}

	public FileProcessor(String inputFilePath) 
		throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("FileProcessor Constructor is called", DebugLevel.CONSTRUCTOR);
		
		
		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
		//line = reader.readLine();
	}
	/**
	 * Method to read line of input file
	 * @return string value of line read from input file
	 */
	public synchronized String poll() throws IOException {
		//if (null == line) return null;
		String newValue = line.trim();
		line = reader.readLine();
		
		return newValue;
	}
	/**
	 * Method to close the resources opened to read a file
	 */
	public void close() throws IOException {
		try {
			reader.close();
			line = null;
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}
}
