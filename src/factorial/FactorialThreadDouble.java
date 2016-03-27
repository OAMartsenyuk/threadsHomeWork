package factorial;

public class FactorialThreadDouble implements Runnable {
	private int numberOfThread;

	public FactorialThreadDouble(int numberOfThread) {
		super();
		this.numberOfThread = numberOfThread;
	}

	@Override
	public void run() {
		double factorial = 1;
		for (int i = 1; i <= this.numberOfThread; i++) {
			factorial = factorial*i;
		}
		System.out.println("The factorial of " + this.numberOfThread + " is " + factorial);
	}

}
