package com.test.thread.pipeInputOutput;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author Administrator
 *
 */
public class Run {

	
	public static void main(String[] args) {
		try {
			
			WriteData writeData = new WriteData();
			ReadData readDate = new ReadData();
			
			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream();
			
			// 建立通信连接
			pos.connect(pis);
			
			ThreadRead threadRead = new ThreadRead(pis, readDate);
			threadRead.start();
			
			Thread.sleep(2000);
			
			ThreadWrite threadWrite = new ThreadWrite(writeData, pos);
			threadWrite.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
