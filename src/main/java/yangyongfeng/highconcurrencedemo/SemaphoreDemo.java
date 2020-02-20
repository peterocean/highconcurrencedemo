package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{
	private final Semaphore semp = new Semaphore(5);
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemaphoreDemo demo = new SemaphoreDemo();
		for (int i = 0; i < 20; i++) {
			exec.submit(demo);
		}

	}

	@Override
	public void run() {
		try {
			semp.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() +" is done");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			semp.release();
		}
	}
	
	
}
