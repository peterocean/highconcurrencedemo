package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadWaitTest {
	public static final Logger logger = LoggerFactory.getLogger(ThreadWaitTest.class);

	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadWaitRunnable());
		Thread t2 = new Thread(new ThreadNotifyRunnalbe());
		t1.start();
		t2.start();

	}

	public static class ThreadWaitRunnable implements Runnable {

		public void run() {
			synchronized (this) {
				logger.debug(System.currentTimeMillis() + ": " + "Thread(" + Thread.currentThread().getId() + ") "
						+ "start.");
				try {
					logger.debug(System.currentTimeMillis() + ": " + "Thread(" + Thread.currentThread().getId() + ") "
							+ "is waiting.");
					this.wait();

				} catch (InterruptedException e) {
					logger.debug(System.currentTimeMillis() + ": " + "Thread(" + Thread.currentThread().getId() + ")"
							+ "is interrupted.");
				}
				logger.debug(
						System.currentTimeMillis() + ": " + "Thread(" + Thread.currentThread().getId() + ")" + " end.");
			}
		}

	}

	public static class ThreadNotifyRunnalbe implements Runnable {

		public void run() {

			synchronized (this) {

				logger.debug(System.currentTimeMillis() + ": " + "Thread:(" + Thread.currentThread().getId() + ")"
						+ "start and notify one thread.");
				this.notify();
				logger.debug(
						System.currentTimeMillis() + ": " + "Thread(" + Thread.currentThread().getId() + ")" + "end.");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {

				}
			}
		}

	}
}
