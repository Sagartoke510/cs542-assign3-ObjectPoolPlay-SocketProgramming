package objectPoolPlay.threadPool;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.MyLogger.DebugLevel;
import objectPoolPlay.util.ResultI;

public class WorkerThread implements Runnable {


	private FileProcessor fp;
	private ResultI result;
	private IsPrime isPrime;

	public WorkerThread(FileProcessor fpIn, ResultI resultIn, IsPrime isPrimeIn) {
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("WorkerThread Constructor is called", DebugLevel.CONSTRUCTOR);
		fp = fpIn;
		result = resultIn;
		isPrime = isPrimeIn;
	}

	@Override
	public void run() {
		
		if(MyLogger.debugLevel == DebugLevel.RUN)
			MyLogger.writeMessage("Executing run() of WorkerThread", DebugLevel.RUN);
		
		try {
			String num;
			while ((num = fp.getLine()) != null) {
				long number = (long) NumberFormat.getInstance().parse(num);
				//System.out.println(Thread.currentThread().getName() + " have read number:" + num);

				if (isPrime.checkPrime(number)) {
					if (!result.addPrime(num)) {
						//System.out.println(Thread.currentThread().getName());
						Thread.currentThread().wait();
					}
					result.sendData();

				}
			}

		} catch (IOException | ParseException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
