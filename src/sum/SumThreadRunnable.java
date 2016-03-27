package sum;

public class SumThreadRunnable implements Runnable{
	private int[] array;
	private long sum;
	private int begin;
	private int end;
	private Thread thread;

	public SumThreadRunnable(int[] array, int begin, int end) {
		super();
		this.array = array;
		this.begin = begin;
		this.end = end;
	}

	public SumThreadRunnable(int[] array) {
		super();
		this.array = array;
		begin = 0;
		end = array.length -1;
	}

	public void start(){
		thread.start();
	}
	
	
	public Thread getThread() {
		return thread;
	}

	
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public long getSum() {
		return sum;
	}

	@Override
	public void run() {
		for (int i = begin; i <= end; i++) {
			this.sum+=array[i];
		}
		//System.out.println(this.array.length);
	}
		
}
