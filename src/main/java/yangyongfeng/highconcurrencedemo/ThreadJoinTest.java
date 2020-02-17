package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadJoinTest {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " start.") ;
		Thread at = new Thread(new AddThread());
		at.start();
		try {
			at.join();
		} catch (InterruptedException e) {
			
		}
		System.out.println(Thread.currentThread().getName() + " end.") ;
	}
	
	public static class AddThread implements Runnable{
		public volatile static int i = 0;

		public void run() {
			System.out.println(Thread.currentThread().getName() + " start.") ;
			for (i = 0; i < 1000000; i++);
			System.out.println(Thread.currentThread().getName() + " end.") ;
		}
	}

}
