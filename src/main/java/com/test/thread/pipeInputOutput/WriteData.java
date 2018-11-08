package com.test.thread.pipeInputOutput;

import java.io.PipedOutputStream;

public class WriteData {

	public void writeMethod(PipedOutputStream out) {
		try {
			System.out.println("write : ");
			for (int i = 0;i < 300; i++) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
