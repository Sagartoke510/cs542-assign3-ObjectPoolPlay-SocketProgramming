package objectPoolPlay.util;

import java.util.Vector;

public interface ResultI {

	public boolean addPrime(String num) throws InterruptedException;
	public void sendData();
	public Vector<String> getResultQueue();

}
