package com.test.thread.pipeInputOutput;

import java.io.PipedOutputStream;
import java.lang.Thread;

public class ThreadWrite extends Thread {

	private WriteData write;
	private PipedOutputStream out;
	public ThreadWrite(WriteData write, PipedOutputStream out) {
		super();
		this.write = write;
		this.out = out;
	}
	
	@Override
	public void run() {
		write.writeMethod(out);
	}
}
