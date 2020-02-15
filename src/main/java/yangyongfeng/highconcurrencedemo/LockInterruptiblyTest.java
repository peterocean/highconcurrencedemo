package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new LockInterruptibleRunnable(1));
		Thread t2 = new Thread(new LockInterruptibleRunnable(1));
		System.out.println(Thread.currentThread().getName() + "start.");
		t1.start();
		System.out.println(Thread.currentThread().getName() + "start.");
		t2.start();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		t2.interrupt();//中断线程2
	}
	
	public static class LockInterruptibleRunnable implements Runnable{
		
		private static ReentrantLock lock1 = new ReentrantLock();
		private static ReentrantLock lock2 = new ReentrantLock();
		private int lockNum;
		
		public LockInterruptibleRunnable(int num) {
			this.lockNum = num;
		}
		
		@Override
		public void run() {
			try {
				if (lockNum == 1) {
					lock1.lockInterruptibly();
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					lock2.lockInterruptibly();
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if (lock1.isHeldByCurrentThread()) {
					lock1.unlock();
				}
				if (lock2.isHeldByCurrentThread()) {
					lock2.unlock();
				}
				System.out.println(Thread.currentThread().getName() + ":线程退出。");
			}
		}
		
	}
}
