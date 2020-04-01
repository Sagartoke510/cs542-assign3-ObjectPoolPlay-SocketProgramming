package objectPoolPlay.util;

import java.util.Vector;

import objectPoolPlay.impl.DataSender;

public class Results implements ResultI {
	private static Vector<String> resultQueue = new Vector<String>();
	private int capacity;
	private DataSender dataSender;
	private Thread dataSenderThread;
	static boolean flag = false;
	
	public Vector<String> getResultQueue() {
		return resultQueue;
	}

	public Results(int capacityIn, int portIn, String ipIn) {
		capacity = capacityIn;
		dataSender = new DataSender(ipIn, portIn);
		}

	@Override
	public synchronized boolean addPrime(String num) throws InterruptedException {
		if (resultQueue.size() < capacity) {
			resultQueue.add(num);
			if(flag) {
			notify();
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public synchronized void sendData() {
		//System.out.println("I am sending:" + resultQueue.get(0));
		try {
		if(dataSenderThread == null) {
		dataSenderThread = new Thread(dataSender);
		}
		if(resultQueue.isEmpty()) {
			flag = true;
			wait();
		}
		 while(!resultQueue.isEmpty()) {
			 System.out.println("I am running thread with value"+resultQueue.get(0));
			 if(!resultQueue.isEmpty() && resultQueue.get(0).equals("STOP")) {
				 System.out.println("I have received :"+resultQueue.get(0));
				 dataSender.setLine(resultQueue.get(0));
				 dataSender.setDataSendStop(true);
				 dataSenderThread.run();
				 resultQueue.removeElement(resultQueue.get(0));
				 break;
			 }else {
			 dataSender.setLine(resultQueue.get(0));
			 dataSenderThread.run();
			 resultQueue.removeElement(resultQueue.get(0));
			 }
			 notifyAll();
		 }
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		 
	}

}
