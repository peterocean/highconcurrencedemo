package yangyongfeng.highconcurrencedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopThreadUnsafe {
	private static final Logger logger = LoggerFactory.getLogger(StopThreadUnsafe.class);
	
	public static User user = new User();
	public static class User{
		private int id;
		private String name;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public User() {
			this.id = 0;
			this.name = "0";
		}
		
		@Override
		public String toString() {
			return "User [id=" + id + ", name = " + name + "]";
		}
	}
	
	
	public static class ChangeObjectThread extends Thread {
		@Override
		public void run() {
			while(true) {
				synchronized (user) {
					int v = (int)(System.currentTimeMillis()/1000);
					user.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						logger.debug("ChangeObject thread is interrupted.");
						Thread.currentThread().interrupt();//重新置位中断标志位
					}
					user.setName(String.valueOf(v));
				}
				if (Thread.currentThread().isInterrupted()) { //线程终止
					logger.debug("ChangeObject thread is terminate.");
					break;
				}
				Thread.yield();
			}
		}
	}

	public static class ReadObjectThread extends Thread{
		@Override
		public void run() {
			while(true) {
				if (Thread.currentThread().isInterrupted()) {
					logger.debug("Read Thread is terminate.");
				}
				synchronized(user) {
					if (user.getId() != Integer.parseInt(user.getName())) {
						logger.debug(user.toString());
						System.out.println(user.toString());
					}
				}
				Thread.yield();
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		Thread readThread =  new ReadObjectThread();    //启动读线程
		Thread changeThread = new ChangeObjectThread();
		
		readThread.start();
		changeThread.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		logger.debug("Main thread is terminated.");
	}
}
