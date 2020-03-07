package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	public static Object u = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		@override
		public void run() {
			synchronized(u) {
				System.out.println("in " + getName() + " park start.");
				LockSupport.park(this);
				if ( Thread.interrupted()) {
					System.out.println(getName() + " is interrupted.");
				}
				
			}
			System.out.println(getName() + " finished.");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		
		System.out.println(t2.getName() + " unpark.");
		LockSupport.unpark(t2);
		System.out.println(t1.getName() + " interrupted.");
		t1.interrupt();
		
		t1.join();
		t2.join();

	}

}
