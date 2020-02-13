package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatitleTest {
	public final static Logger logger = LoggerFactory.getLogger(VolatitleTest.class);
	public volatile static long sum = 0;
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] =  new Thread(new PlusTask());
			threads[i].start();
		}
		
		for (int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println(sum);
		logger.debug(Thread.currentThread().getName() + " end.");
	}
	
	public static class PlusTask implements Runnable{
		public void run() {
			for (int i = 0; i < 10000; i++) {
				sum++;
			}
			System.out.println(sum);
		}
	}
}
