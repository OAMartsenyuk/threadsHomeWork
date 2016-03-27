package factorial;

import java.math.*;

public class FactorialThreadBI implements Runnable {
	private int numberOfThread;

	public FactorialThreadBI(int numberOfThread) {
		super();
		this.numberOfThread = numberOfThread;
	}

	@Override
	public void run() {
		BigInteger factorial = BigInteger.valueOf(1);
		for (int i = 1; i <= this.numberOfThread; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}
		System.out.println("The factorial of " + this.numberOfThread + " is " + factorial);
	}

}
