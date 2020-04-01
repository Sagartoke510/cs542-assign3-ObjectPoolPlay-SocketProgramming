package objectPoolPlay.threadPool;

import java.io.IOException;
import java.util.ArrayList;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.MyLogger;
import objectPoolPlay.util.ResultI;
import objectPoolPlay.util.MyLogger.DebugLevel;

public class CreateWorkers {

	private FileProcessor fp;
	private ResultI result;
	private IsPrime isPrime;

	public CreateWorkers(FileProcessor fpIn, ResultI resultIn, IsPrime isPrimeIn) {
		
		if(MyLogger.debugLevel == DebugLevel.CONSTRUCTOR)
			MyLogger.writeMessage("CreateWorkers Constructor is called", DebugLevel.CONSTRUCTOR);

		fp = fpIn;
		result = resultIn;
		isPrime = isPrimeIn;
	}
	
	public void startWorkers(int numOfThreads) throws InterruptedException, IOException {

		ThreadPool pool = new ThreadPool();
		pool.newFixedPool(numOfThreads, fp, result, isPrime);
		ArrayList<Thread> tList= new ArrayList<Thread>();
		while(!pool.getThreadList().isEmpty()) {
			Thread t = new Thread(pool.borrow());
			tList.add(t);
			t.start();
		}
		
		for(Thread t: tList) {
		t.join();
		}
		
		if (null == fp.getLine() ) {
			stop();
		}

	}
	
	public void stop() throws InterruptedException {
		result.addPrime("STOP");
		result.sendData();
	}

}
