package objectPoolPlay.threadPool;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

public class WorkerThread implements Runnable{
	
	private FileProcessor fp;
	private ResultI result;
	private IsPrime isPrime;
	
	public WorkerThread(FileProcessor fpIn, ResultI resultIn, IsPrime isPrimeIn) {
		fp = fpIn;
		result = resultIn;
		isPrime = isPrimeIn;
	}

	@Override
	public void run() {
		System.out.println("I am in run: "+Thread.currentThread().getName());
		while(fp.getLine() != null){
			try {			
				long num = (long)NumberFormat.getInstance().parse(fp.poll().trim());
				System.out.println("I have read number:"+num);
				if(isPrime.checkPrime(num)) {
					result.addPrime(num);
				}
			} catch (ParseException | IOException | InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
