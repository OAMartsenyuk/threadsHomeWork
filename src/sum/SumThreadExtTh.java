package sum;

public class SumThreadExtTh extends Thread{
	private int[] array;
	private long sum;
	private int begin;
	private int end;

	public SumThreadExtTh(int[] array, int begin, int end) {
		super();
		this.array = array;
		this.begin = begin;
		this.end = end;
	}

	public SumThreadExtTh(int[] array) {
		super();
		this.array = array;
		begin = 0;
		end = array.length -1;
	}

	public long getSum() {
		return sum;
	}

	@Override
	public void run() {
		for (int i = begin; i <= end; i++) {
			this.sum+=array[i];
		}
	}
		
}
