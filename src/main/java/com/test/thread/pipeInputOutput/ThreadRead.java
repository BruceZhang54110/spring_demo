package com.test.thread.pipeInputOutput;

import java.io.PipedInputStream;

public class ThreadRead extends Thread {

	private PipedInputStream input;
	
	private ReadData read;

	public ThreadRead(PipedInputStream input, ReadData read) {
		super();
		this.input = input;
		this.read = read;
	}
	
	@Override
	public void run() {
		read.readMethod(input);
	}
	
	
}
