package objectPoolPlay.threadPool;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

public class WorkerThread implements Runnable {

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
		try {
			String num;
			while ((num = fp.getLine()) != null) {
				long number = (long) NumberFormat.getInstance().parse(num);
				System.out.println(Thread.currentThread().getName() + " have read number:" + num);

				if (isPrime.checkPrime(number)) {
					if(!result.addPrime(number)) {
						Thread.currentThread().wait();
					}
				}
			}
		} catch (IOException | ParseException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
