package objectPoolPlay.util;

public class IsPrime {
	
	public synchronized boolean checkPrime(long num) throws InterruptedException {
		boolean flag = true;
		for(int i =2; i<=num/2 ;i++) {
			if(num%i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
