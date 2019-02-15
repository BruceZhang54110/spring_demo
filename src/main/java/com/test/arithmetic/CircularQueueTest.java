package com.test.arithmetic;

public class CircularQueueTest {

	public static void main(String[] args) {
		MyCircularQueue circularQueue = new MyCircularQueue(3);
		System.out.println(circularQueue.enQueue(1));
		System.out.println(circularQueue.enQueue(2));
		System.out.println(circularQueue.enQueue(3));
		System.out.println(circularQueue.enQueue(4));
		System.out.println(circularQueue.Rear());
		
	}
}
