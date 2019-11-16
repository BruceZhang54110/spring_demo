package com.test.thread.ticket;

import java.util.concurrent.locks.ReentrantLock;

public class SellTicket {
	

	public static void main(String[] args) {
		
		TicketWindow t = new TicketWindow();
		for (int i = 0; i< 3; i++) {
			Thread thread = new Thread(t, "窗口" + (i+1));
			thread.start();
		}
		
	}
}

class TicketWindow extends Thread {
	
	private ReentrantLock lock = new ReentrantLock();
	
	
	public TicketWindow() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int tickets = 5;

	@Override
	public void run() {
		
			while(true) {
				lock.lock();
					if (tickets > 0) {
						System.out.println(Thread.currentThread().getName() + "  准备出票,剩余票数：" + tickets + "张");
						tickets --;
						System.out.println(Thread.currentThread().getName() + "   卖出1张,剩余" + tickets + "张");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("余票不足,停止售票！");
						break;
					} 
					lock.unlock();
				}
			
		
	}
	
}
