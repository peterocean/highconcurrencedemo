package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockTest {
	public static ReentrantLock lock = new ReentrantLock();
	public static int sum = 0;
	public static void main(String[] args) {
		
		Thread t1 = new Thread (new AccountThread());
		Thread t2 = new Thread (new AccountThread());
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Main Thread end.");
	}
	
	public  static class AccountThread implements Runnable{
		
		@Override
		public void run() {
			lock.lock();
			try {
				for (int j = 0; j < 1000000; j++) {
					sum++;
				}
			} finally {
				System.out.println("sum = " + sum);
				lock.unlock();
			}
		}
	}
	

}
