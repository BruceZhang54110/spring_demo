package com.at.spring.beans.autowire;

public class Car {
	
	private String brand;
	private double price;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + "]";
	}


	public Car() {
		System.out.println("Car 无参构造方法");
	}

	public Car(String brand, double price) {
		System.out.println("Car 有参构造方法");
		this.brand = brand;
		this.price = price;
	}
}
