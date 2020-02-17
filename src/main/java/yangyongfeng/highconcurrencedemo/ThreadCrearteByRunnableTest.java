package yangyongfeng.highconcurrencedemo;

import yangyongfeng.highconcurrencedemo.ThreadCreateTest.ThreadTest1;

public class ThreadCrearteByRunnableTest {

	public static void main(String[] args) {
		Thread threads[] = new Thread[3];
		
		for (int i = 0; i < 3; i++) {
			threads[i] = new Thread(new ThreadTest());
			threads[i].start();
		}

	}
	
	public static class ThreadTest implements Runnable{

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " is running.");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end.");
			
		}
		
	}

}
