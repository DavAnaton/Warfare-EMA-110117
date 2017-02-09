package models.shipments;

import java.util.ArrayList;

import models.containers.Spot;

public class Food extends GenericShipment {

	// Constructors
	public Food() {
		super();
	}

	// Methods

	/**
	 * Checks if every rule for this package is respected:
	 * 
	 * - No rule for this package
	 * 
	 */
	public boolean validatePosition(Spot s) {
		boolean valid = s.getContent().size() < s.getMaxSize();
		
		return valid;
	}
	
	// Display
	public String toString() {
		return "Food";
	}

}
