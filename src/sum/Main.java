package sum;

import java.lang.reflect.Array;
import java.util.Random;

public class Main {
	static long sum(int[] array) {
		long sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		final int THREAD_NUMBER = 1;
		final int ELEMS_NUMBER = 40000000;
		int[] array = new int[ELEMS_NUMBER];
		long tmpSum;
		long tStart;
		long tEnd;

		Random rn = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rn.nextInt(10);
		}

		// Static
		tStart = System.currentTimeMillis();
		tmpSum = sum(array);
		tEnd = System.currentTimeMillis();
		//System.out.println(tmpSum);
		System.out.println("Static sum took " + (tEnd - tStart) + "ms");

		// One thread
		int[] array2 = array.clone();
		tStart = System.currentTimeMillis();
		SumThreadRunnable sumThreadRunnable = new SumThreadRunnable(array2);
		sumThreadRunnable.setThread(new Thread(sumThreadRunnable));
		sumThreadRunnable.start();

		try {
			sumThreadRunnable.getThread().join();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tEnd = System.currentTimeMillis();
		//System.out.println(sumThreadRunnable.getSum());
		System.out.println("Single sum took " + (tEnd - tStart) + "ms");

		// Multiple threads (on runable class)
		tStart = System.currentTimeMillis();
		int[] array3 = array.clone();
		SumThreadRunnable[] sumThreadArray = new SumThreadRunnable[THREAD_NUMBER];
		int singleArraySize = ELEMS_NUMBER / THREAD_NUMBER;
		int begin = 0;
		int end = singleArraySize-1;

		for (int i = 0; i < THREAD_NUMBER; i++) {
			sumThreadArray[i] = new SumThreadRunnable(array3, begin, end);
			sumThreadArray[i].setThread(new Thread(sumThreadArray[i]));
			sumThreadArray[i].start();
			begin = end + 1;
			if (i == (sumThreadArray.length-1)) {
				end = ELEMS_NUMBER;				
			} else {
				end = end + singleArraySize;				
			}
		}
		
		for (int i = 0; i < THREAD_NUMBER; i++) {
			try {
				sumThreadArray[i].getThread().join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		tEnd = System.currentTimeMillis();
		long sum=0;
		for (int i = 0; i < sumThreadArray.length; i++) {
			sum += sumThreadArray[i].getSum();
		}		
		//System.out.println(sum);
		System.out.println("Multithread (on runable class) took " + (tEnd - tStart) + "ms");
		
		// Multiple threads (on extends Thread)
		tStart = System.currentTimeMillis();
		int[] array4 = array.clone();
		SumThreadExtTh[] sumThreadExtTh = new SumThreadExtTh[THREAD_NUMBER];
		begin = 0;
		end = singleArraySize - 1;
		for (int i = 0; i < THREAD_NUMBER; i++) {
			sumThreadExtTh[i] = new SumThreadExtTh(array4, begin, end);
			sumThreadExtTh[i].start();
			begin = end + 1;
			if (i == (sumThreadExtTh.length - 1)) {
				end = ELEMS_NUMBER;
			} else {
				end = end + singleArraySize;
			}
		}

		for (int i = 0; i < THREAD_NUMBER; i++) {
			try {
				sumThreadExtTh[i].join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		tEnd = System.currentTimeMillis();
		sum = 0;
		for (int i = 0; i < sumThreadExtTh.length; i++) {
			sum += sumThreadExtTh[i].getSum();
		}
		
		//System.out.println(sum);
		System.out.println("Multithread (on extanded class) took " + (tEnd - tStart) + "ms");
	}

}
