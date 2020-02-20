package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockCondition implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();
	
	public static void main(String[] args) {
		RentrantLockCondition t1Runanble = new RentrantLockCondition();
		Thread t1 = new Thread(t1Runanble);
		t1.start();
		try {
			System.out.println(Thread.currentThread().getName() + " enter sleep.");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " sends signal.");
		condition.signal();
		lock.unlock();

	}

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " is waiting signal.");
			condition.await();
			System.out.println(Thread.currentThread().getName() + " is going on.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}

}
