package com.test.designPattern.factory;
/**
 * 左偏分发型
 * @author Administrator
 *
 */
public class Lefthair implements HairInterface {

	@Override
	public void draw() {
		System.out.println("------------左偏分发型---------------");
	}

}
