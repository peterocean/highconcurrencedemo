package yangyongfeng.highconcurrencedemo;

import java.util.concurrent.locks.LockSupport;

import yangyongfeng.highconcurrencedemo.LockSupportDemo.ChangeObjectThread;

public class LockSupportIntDemo {
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
				System.out.println("in " + getName() + " park. end");
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
