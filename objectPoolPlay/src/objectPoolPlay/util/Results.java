package objectPoolPlay.util;

import java.util.Vector;

public class Results implements ResultI {
	
	private Vector resultQueue = new Vector();
	private int capacity;
	
	public Results(int capacityIn) {
		capacity = capacityIn;
	}

	@Override
	public synchronized boolean addPrime(long num) throws InterruptedException {
		if (resultQueue.size() <= capacity) {
			resultQueue.add(num);
			return true;
		} else {
			return false;
		}
	}

}
