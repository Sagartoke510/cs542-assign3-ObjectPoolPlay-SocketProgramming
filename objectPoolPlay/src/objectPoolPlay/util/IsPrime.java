package objectPoolPlay.util;
/**
 * The class {@code IsPrime}checks if number is prime or not
 */
public class IsPrime {
	
	/**
	 * Method to check if number is prime or not
	 * @param num is integer to be checked for prime
	 * @return string value of line read from input file
	 * @throws InterruptedException
	 */
	public synchronized boolean checkPrime(long num) throws InterruptedException {
		boolean flag = true;
		if(num == 1) {
			return false;
		}
		for(int i =2; i<=num/2 ;i++) {
			if(num%i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
