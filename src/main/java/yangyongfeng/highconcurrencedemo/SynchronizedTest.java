package yangyongfeng.highconcurrencedemo;

public class SynchronizedTest {
	private static int sum = 0;

	public static class AccountingSync implements Runnable {

	private final Object syncObject = new Object();

		public void run() {
			synchronized(this) {
				for (int i = 0; i < 1000000; i++) {
					sum++;
				}
			}
		}

	}

	public static void main(String[] args) {
		AccountingSync accountingSync = new AccountingSync();
		Thread t1 = new Thread(accountingSync);
		Thread t2 = new Thread(accountingSync);
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		System.out.println(sum);
	}

}
