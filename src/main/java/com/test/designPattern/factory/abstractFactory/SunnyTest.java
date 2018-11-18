package com.test.designPattern.factory.abstractFactory;

public class SunnyTest {

	public static void main(String[] args) {
		PersonFactory personFactory = new MCFactory();
		Girl girl = personFactory.getGirl();
		girl.drawGirl();
	}
}
