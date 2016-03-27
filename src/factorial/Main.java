package factorial;

public class Main {

	public static void main(String[] args) {
		long tStartBI = System.currentTimeMillis();
		Thread[] thArray = new Thread[100];
		for (int i = 0; i < 100; i++) {
			thArray[i] = new Thread(new FactorialThreadBI(i + 1), "thread " + i);
			if (i % 2 == 0) {// все потоки с парным номером - демоны
				thArray[i].setDaemon(true);
			}
			thArray[i].start();
		}
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				thArray[i].join();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		long tEndBi = System.currentTimeMillis();
		long timeBi = tEndBi - tStartBI;
		
		
		long tStartLong = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			thArray[i] = new Thread(new FactorialThreadDouble(i + 1), "thread " + i);
			if (i % 2 == 0) {// все потоки с парным номером - демоны
				thArray[i].setDaemon(true);
			}
			thArray[i].start();
		}
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				continue;
			}
			try {
				thArray[i].join();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		long tEndLong = System.currentTimeMillis();
		long timeLong = tEndLong - tStartLong;
		System.out.println("Double type factorial took time:" + timeLong);
		System.out.println("Biginteger type factorial took time:" + timeBi);
		
		System.out.println("Main thread finished");
	}
}
