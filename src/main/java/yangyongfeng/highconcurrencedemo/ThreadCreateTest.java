package yangyongfeng.highconcurrencedemo;

public class ThreadCreateTest {

	public static void main(String[] args) {

		ThreadTest1 threads[] = new ThreadTest1[3];
		for (int i = 0; i < 3; i++) {
			threads[i] = new ThreadTest1();
			threads[i].start();
		}

	}
	
	public static class ThreadTest1 extends Thread{
		
		public void run(){
			try {
				System.out.println(Thread.currentThread().getName() + " is running.");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end.");
		}
	}

}
