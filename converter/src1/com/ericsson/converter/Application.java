package com.ericsson.converter;

public class Application {
		
	private static final double MAGIC_NUMBER = 42;
		
	public static void main(String[] args) {
		System.out.println("Application started...");
		
		ImperialToMetricCalculator calculator = new ImperialToMetricCalculator();
		System.out.println(MAGIC_NUMBER + " in --> " + calculator.inchToMeter(MAGIC_NUMBER) + " m");
		System.out.println(MAGIC_NUMBER + " ft --> " + calculator.footToMeter(MAGIC_NUMBER) + " m");
		System.out.println(MAGIC_NUMBER + " yd --> " + calculator.yardToMeter(MAGIC_NUMBER) + " m");
		System.out.println(MAGIC_NUMBER + " mi --> " + calculator.mileToMeter(MAGIC_NUMBER) + " m");
	}

}