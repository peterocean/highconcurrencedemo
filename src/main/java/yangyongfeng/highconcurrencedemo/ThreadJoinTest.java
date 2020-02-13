package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadJoinTest {
	public final static Logger logger = LoggerFactory.getLogger(ThreadJoinTest.class);
	
	public static void main(String[] args) {
		logger.debug(Thread.currentThread().getName() + " start.");
		Thread at = new Thread(new AddThread());
		at.start();
		try {
			at.join();
		} catch (InterruptedException e) {
			
		}
		
		logger.debug(Thread.currentThread().getName() + " end.");
	}
	
	public static class AddThread implements Runnable{
		public volatile static int i = 0;

		public void run() {
			logger.debug(Thread.currentThread().getName() + " start.");
			for (i = 0; i < 1000000; i++);
			logger.debug(Thread.currentThread().getName() + " end.");
		}
	}

}
