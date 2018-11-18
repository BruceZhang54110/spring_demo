package com.test.designPattern.factory;
/**
 * 模拟客户端实现
 * @author Administrator
 *
 */
public class TomTest {

	public static void main(String[] args) {
		/*HairInterface left = new Lefthair();
		left.draw();*/
		
		HairFactory factory = new HairFactory();
//		HairInterface leftHair = factory.getHair("leftss");
//		leftHair.draw();
		
//		HairInterface leftHair = factory.getHairByClass("com.test.designPattern.factory.Lefthair");
		HairInterface leftHair = factory.getHairByClassKey("in");
		leftHair.draw();
	}
}
