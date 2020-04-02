package objectPoolPlay.impl;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import objectPoolPlay.threadPool.CreateWorkers;
import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.ResultI;
import objectPoolPlay.util.Results;
import objectPoolPlay.util.MyLogger.DebugLevel;

/**
 * 
 * This class is {@code PrimeDetector} main class
 * 
 */
public class PrimeDetector {

	private String inputFile;
	private int numOfThreads;
	private int capacity;
	private String persisterServiceIp;
	private int persisterServicePort;
	private int debugValue;

	public PrimeDetector(String inputFileIn, String numOfThreadsIn, String capacityIn, String persisterServiceIpIn,
			String persisterServicePortIn, String debugValueIn) {
		
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("PrimeDetector Constructor is called", DebugLevel.CONSTRUCTOR);
		
		inputFile = inputFileIn;
		numOfThreads = Integer.parseInt(numOfThreadsIn);
		capacity = Integer.parseInt(capacityIn);
		persisterServiceIp = persisterServiceIpIn;
		persisterServicePort = Integer.parseInt(persisterServicePortIn);
		debugValue = Integer.parseInt(debugValueIn);
	}

	/**
     * Method to send FileProcessor, ResultI and IsPrime to CreateWorkers for further thread creation
     * @param ifilename where input file resides to pass to FileProcessor 
	 * @return None
     */
	public void process(String ifilename) {
		
		try {
			MyLogger.setDebugValue(debugValue);
			FileProcessor fp = new FileProcessor(ifilename);
			ResultI result = new Results(capacity, persisterServicePort, persisterServiceIp);
			IsPrime isPrime = new IsPrime();
			CreateWorkers workers = new CreateWorkers(fp, result, isPrime);
			workers.startWorkers(numOfThreads);
			
		} catch (InvalidPathException | SecurityException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
