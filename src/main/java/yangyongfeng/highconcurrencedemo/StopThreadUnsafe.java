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
						//logger.debug(e.getMessage(),e);
						e.printStackTrace();
					}
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}

	public static class ReadObjectThread extends Thread{
		@Override
		public void run() {
			while(true) {
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
		new ReadObjectThread().start();    //启动读线程
		while(true) {
			Thread t = new ChangeObjectThread();
			t.start();
			Thread.sleep(150);
			t.stop();
		}
	}
}
