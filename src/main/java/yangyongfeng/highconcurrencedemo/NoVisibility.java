package yangyongfeng.highconcurrencedemo;

public class NoVisibility {
	private  static boolean ready;
	private static int number;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new ReaderThread()).start();
		Thread.sleep(1000);
		number = 42;
		ready = true;
		Thread.sleep(10000);
	}
	
	private static class ReaderThread implements Runnable{

		public void run() {
			while (!ready);
			System.out.println(number);
		}
		
	}
	
	

}
