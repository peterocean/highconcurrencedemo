package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadWaitTest {
	public static Object syncObject = new Object();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadWaitRunnable());
		Thread t2 = new Thread(new ThreadNotifyRunnalbe());
		t1.start();
		t2.start();

	}

	public static class ThreadWaitRunnable implements Runnable {

		public void run() {
			synchronized (syncObject) {		//线程等待前需获得对象锁
				System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " start.");
				try {
					System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " wait and blocked");
					syncObject.wait();

				} catch (InterruptedException e) {
					System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " is interrupted.");
				}
				System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " end.");
			}
		}

	}

	public static class ThreadNotifyRunnalbe implements Runnable {

		public void run() {

			synchronized (syncObject) { //线程notify()需获得对象同步锁，且需与被唤醒的线程使用同一对象
				System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " start and notify one thread.");
				syncObject.notify();
				
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
			System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " end.");
		}

	}
}
