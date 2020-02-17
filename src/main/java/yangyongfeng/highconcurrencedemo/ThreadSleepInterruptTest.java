package yangyongfeng.highconcurrencedemo;

public class ThreadSleepInterruptTest {

	public static void main(String[] args) {
		Thread test = new Thread(new ThreadSleep());
		test.start();
		test.interrupt();	//线程休眠打断
	}

	public static class ThreadSleep implements Runnable{

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " enter sleep.");
				Thread.sleep(100000);		//进入长时间休眠
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName() + " sleep is interrupt");
			}
			System.out.println(Thread.currentThread().getName() + " end.");
		}
	}
}
