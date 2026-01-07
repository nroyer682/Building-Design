package model;

public class Blueprint {
	private int maxFloors;
	private Floor[] floors;
	private int nof;

	public Blueprint(int maxFloors) {
		this.maxFloors = maxFloors;
		this.floors = new Floor[maxFloors];
		this.nof = 0;
	}

	public Blueprint(Blueprint other) {
		this(other.maxFloors);
		for (int i = 0; i < other.nof; i++) {
			this.floors[i] = new Floor(other.floors[i]);
		}
		this.nof = other.nof;
	}

	public void addFloorPlan(Floor f) {
		this.floors[this.nof] = f;
		this.nof++;
	}

	public Floor[] getFloors() {
		/*
		 * The accessor `getFloors` should preserve composition:
		 *  the array you return does not create any aliases or allow any sharing
		 * 	the returned floor plans are not to be shared with the blueprint `bp`.
		 */
		Floor[] result = new Floor[this.nof];
		
		for (int i = 0; i < this.nof; i++) {
			result[i] = new Floor(this.floors[i]);
		}
		return result;
	}
	
	public String toString() {
		double percentage = (this.nof / (double) this.maxFloors) * 100.0;
		
		return String.format("%.1f percents of building blueprint completed (%d out of %d floors)", percentage, this.nof, this.maxFloors);
		
	}

}
