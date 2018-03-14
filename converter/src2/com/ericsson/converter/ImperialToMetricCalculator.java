package com.ericsson.converter;

public class ImperialToMetricCalculator {
					
	private static final double FOOT_2_METER_UNIT = 0.30480;		
		
	public double inchToMeter( double inches ) {
		return this.footToMeter( inches / 12 );
	}
		
	public double footToMeter( double feet ) {
		return feet * FOOT_2_METER_UNIT;
	}
	
	public double yardToMeter( double yards ) {
		return this.footToMeter( yards * 3 );
	}
	
	public double mileToMeter( double miles ) {
		return this.yardToMeter( miles * 8 * 10 * 22 );
	}

}
