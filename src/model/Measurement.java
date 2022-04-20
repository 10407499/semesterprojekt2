package model;

public class Measurement {
	
	private MeasurementUnit unit;
	
	public Measurement(MeasurementUnit unit) {
		this.unit = unit;
	}
	
	public MeasurementUnit getMeasurementUnit() {
		return unit;
	}
	
}
