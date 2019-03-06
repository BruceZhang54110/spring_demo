package com.test.thread.imooc;

/**
 * 主线程等待法，来主线程来获取子线程返回结果
 * @author Administrator
 *
 */
public class CycleWait implements Runnable {
	
	private String value;

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		value = "we have data now";
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		CycleWait cw = new CycleWait();
		Thread t = new Thread(cw);
		t.start();
		/*while(cw.value == null) {
			Thread.currentThread();
			Thread.sleep(100);
		}*/
		t.join();
		System.out.println(cw.value);
	}

}
