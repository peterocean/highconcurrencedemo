package yangyongfeng.highconcurrencedemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{

	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		System.out.println("等待系统自检完成......");
		//等待检查
		end.await();
		
		//发射火箭
		System.out.println("Fire!");
		exec.shutdown();
	}

	@Override
	public void run() {
		try {
			//模拟检查任务
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("Task: " +Thread.currentThread().getName() +  " Check complete.\n");
			end.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
