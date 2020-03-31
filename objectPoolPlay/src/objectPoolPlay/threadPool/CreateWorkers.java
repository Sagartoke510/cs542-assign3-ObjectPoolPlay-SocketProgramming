package objectPoolPlay.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

public class CreateWorkers {

	private FileProcessor fp;
	private ResultI result;
	private IsPrime isPrime;

	public CreateWorkers(FileProcessor fpIn, ResultI resultIn, IsPrime isPrimeIn) {

		fp = fpIn;
		result = resultIn;
		isPrime = isPrimeIn;
	}
	
	public void startWorkers(int numOfThreads) throws InterruptedException {

		ThreadPool pool = new ThreadPool();
		pool.newFixedPool(numOfThreads, fp, result, isPrime);
		
		while(!pool.getThreadList().isEmpty()) {
			Thread t = new Thread(pool.borrow());
			t.start();

		}
		
		
	}

}
