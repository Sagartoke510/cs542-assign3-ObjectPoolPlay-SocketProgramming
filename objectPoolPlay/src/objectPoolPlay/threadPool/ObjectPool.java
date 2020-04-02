package objectPoolPlay.threadPool;

import objectPoolPlay.util.FileProcessor;
import objectPoolPlay.util.IsPrime;
import objectPoolPlay.util.ResultI;

/**
 * This interface is an ObjectPool
 */

public interface ObjectPool {
	
	/**
	 * Method to create a ThreadPool of WorkerThreads
	 * @param size of ThreadPool
	 * @param fp FileProcessor instance required by WorkerThreads
	 * @param result ResultI instance required by WorkerThreads
	 * @param isPrime IsPrime instance required by WorkerThreads
	 */
	public void newFixedPool(int size,FileProcessor fp, ResultI result, IsPrime isPrime);
	
	/**
	 * Method to borrow threads from ThreadPool
	 * @return Runnable instance
	 */
	public Runnable borrow();
	
	/**
	 *  Method to return borrowed threads back to the pool
	 *  @param r Runnable Thread returned to ThreadPool
	 */
	public void returnObject(Runnable r);

}
