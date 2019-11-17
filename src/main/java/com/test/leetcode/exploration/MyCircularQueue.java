package com.test.leetcode.exploration;
/**
 * 循环队列<br>
 * @author Administrator
 *
 */
public class MyCircularQueue {
	
	private int[] data;
	
	/**
	 * 头指针
	 */
	private int head;
	
	/**
	 * 尾指针
	 */
	private int tail;
	
	private int size;
	
	public MyCircularQueue(int k) {
		data = new int[k];
		head = -1;
		tail = -1;
		size = k;
	}
	
	/**
	 * 入队
	 * @param value
	 * @return
	 */
	public boolean enQueue(int value) {
		if(isFull() == true) {
			return false;
		}
		if (isEmpty() == true) {
			head = 0;
		}
		
		tail = (tail + 1) % size;
		data[tail] = value;
		return true;
		
	}
	
	/**
	 * 出队
	 * @return
	 */
	public boolean deQueue() {
		if(isEmpty() == true) {
			return false;
		}
		if (head == tail) {
			head = -1;
			tail = -1;
			return true;
		}
		head = (head + 1) % size;
		return true;
	}
	
	public int Front() {
		if (isEmpty() == true) {
			return -1;
		}
		return data[head];
	}
	
	public int Rear() {
		if (isEmpty() == true) {
			return -1;
		}
		return data[tail];
	}
	
	public boolean isEmpty() {
		return head == -1;
	}
	
	public boolean isFull() {
		return ((tail + 1) % size) == head;
	}
	
	public static void main(String[] args) {
		MyCircularQueue obj = new MyCircularQueue(5);
		  boolean param_1 = obj.enQueue(2);
		  boolean param_2 = obj.deQueue();
		  int param_3 = obj.Front();
		 int param_4 = obj.Rear();
		  boolean param_5 = obj.isEmpty();
		  boolean param_6 = obj.isFull();
	}

}
