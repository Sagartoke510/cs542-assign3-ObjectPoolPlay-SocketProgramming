package objectPoolPlay.threadPool;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

public interface ObjectPool {
	
	public void newFixedPool(int size,FileProcessor fp, ResultI result, IsPrime isPrime);
	public Runnable borrow();
	public void returnObject(Runnable r);

}
