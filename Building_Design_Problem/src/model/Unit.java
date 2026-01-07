package model;

public class Unit {
	private String function;
	private int width;
	private int length;
	private String mode; // feet or meters
	private final String FEET_MODE = "feet";
	private final String METERS_MODE = "meters";
	private final double FEET_IN_METERS = 0.3048;

	public Unit(String function, int width, int length) {
		this.function = function;
		this.width = width;
		this.length = length;
		this.mode = FEET_MODE;
	}
	
	public Unit(Unit other) {
		this(other.function, other.width, other.length);
	}

	public String getFunction() {
		return this.function;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public String toString() {
		String result = null;
		if (this.mode.equals(FEET_MODE)) {
			result = String.format("A unit of %d square feet (%d' wide and %d' long) functioning as %s", 
					this.getAreaInSquareFeet(), this.getWidth(), this.getLength(), this.getFunction());
		}
		else {
			result = String.format("A unit of %.2f square meters (%.2f m wide and %.2f m long) functioning as %s", 
					this.getAreaInSquareMeters(), this.getWidth() * FEET_IN_METERS, this.getLength() * FEET_IN_METERS, this.getFunction());
		}
		return result;
	}
	
	public int getAreaInSquareFeet() {
		return this.width * this.length;
	}
	
	public double getAreaInSquareMeters() {
		return (this.width * FEET_IN_METERS) * (this.length * FEET_IN_METERS);
	}

	public void toggleMeasurement() {
		/* 
		 * Change the measurement from feet to meters.
		 * Notes:
		 * 	- Use this conversion formula: One foot is equal to 0.3048 meter.
		 * 	- Each value of meters and square meters should be displayed with 2 digits after the decimal point.
		 */
		if (this.mode.equals(FEET_MODE)) {
			this.mode = METERS_MODE;
		}
		else {
			this.mode = FEET_MODE;
		}
	}
	
	public boolean equals(Object obj) {
		/*
		 * Two units are considered equal if their intended functions are the same (case-sensitive)
		 * 	and the areas (in square feet) are the same (even if the dimensions may be different). 
		 */
		if (this == obj) {
			return true;
		}
		else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Unit other = (Unit) obj;
		return (this.getFunction().equals(other.getFunction())) && (this.getAreaInSquareFeet() == other.getAreaInSquareFeet());
	}
	
	
}
