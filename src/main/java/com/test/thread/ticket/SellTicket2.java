package com.test.thread.ticket;

public class SellTicket2 {

	public static void main(String[] args) {
		
		for (int i = 0; i< 3; i++) {
			TicketWindow t = new TicketWindow();
			//Thread thread = new Thread(t, "窗口" + (i+1));
			t.start();
		}
		
	}
}

class TicketWindows extends Thread {
	
	
	

	private int tickets = 5;

	@Override
	public void run() {
		
		while(true) {
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
		}
		
	}
	
}
