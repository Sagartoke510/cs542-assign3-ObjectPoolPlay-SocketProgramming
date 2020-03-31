package objectPoolPlay.threadPool;

import java.util.ArrayList;
import java.util.List;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

public class ThreadPool implements ObjectPool {

	private List<Runnable> threadList = new ArrayList<Runnable>();
	private FileProcessor fp;
	private ResultI result;
	private IsPrime isPrime;
	private static int count =0;

	public List<Runnable> getThreadList() {
		return threadList;
	}

	@Override
	public Runnable borrow() {
		Runnable r = threadList.get(count);
		threadList.remove(count);
		return r;
	}

	@Override
	public void newFixedPool(int sizeOfPool, FileProcessor fp, ResultI result, IsPrime isPrime) {

		for (int i = 0; i < sizeOfPool; i++) {
			Runnable t = new WorkerThread(fp, result, isPrime);
			threadList.add(t);
		}

	}

}
