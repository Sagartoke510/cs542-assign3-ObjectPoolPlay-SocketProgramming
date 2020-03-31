package objectPoolPlay.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import objectPoolPlay.threadPool.CreateWorkers;
import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;
import objectPoolPlay.util.Results;

public class PrimeDetector {

	private String inputFile;
	private int numOfThreads;
	private String capacity;
	private String persisterServiceIp;
	private String persisterServicePort;
	private String debugValue;

	public PrimeDetector(String inputFileIn, String numOfThreadsIn, String capacityIn, String persisterServiceIpIn,
			String persisterServicePortIn, String debugValueIn) {
		inputFile = inputFileIn;
		numOfThreads = Integer.parseInt(numOfThreadsIn);
		capacity = capacityIn;
		persisterServiceIp = persisterServiceIpIn;
		persisterServicePort = persisterServicePortIn;
		debugValue = debugValueIn;
	}

	public void process(String ifilename) {
		
		try {
			
			FileProcessor fp = new FileProcessor(ifilename);
			ResultI result = new Results();
			IsPrime isPrime = new IsPrime();
			CreateWorkers workers = new CreateWorkers(fp, result, isPrime);
			workers.startWorkers(numOfThreads);
			
		} catch (InvalidPathException | SecurityException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
