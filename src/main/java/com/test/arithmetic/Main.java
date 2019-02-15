package com.test.arithmetic;

import java.util.ArrayList;
import java.util.List;

class Myqueue {
	
	private List<Integer> data;
	// a pointer to indicate the start position
	private int p_start;
	
	public Myqueue() {
		data = new ArrayList<Integer>();
		p_start = 0;
	}
	
	/**
	 * 入队
	 * @param x
	 * @return
	 */
	public boolean enQueue(int x) {
		data.add(x);
		return true;
	}
	
	public int Front() {
        return data.get(p_start);
    }
	/** Delete an element from the queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }
	/** Checks whether the queue is empty or not. */
    public boolean isEmpty() {
        return p_start >= data.size();
    } 

};

public class Main {
    public static void main(String[] args) {
    	Myqueue q = new Myqueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println("aaaaaaaa" + q.Front());
        }
    }
}
