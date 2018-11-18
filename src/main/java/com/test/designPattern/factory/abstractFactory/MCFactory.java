package com.test.designPattern.factory.abstractFactory;

/**
 * 圣诞系列加工厂
 * @author Administrator
 *
 */
public class MCFactory implements PersonFactory {

	@Override
	public Boy getBoy() {
		// TODO Auto-generated method stub
		return new MCBoy();
	}

	@Override
	public Girl getGirl() {
		// TODO Auto-generated method stub
		return new MCGirl();
	}

}
