package models.shipments;

import java.util.ArrayList;

import models.containers.Spot;

public class Food extends GenericShipment {

	// Constructors
	public Food() {
		super();
	}

	// Methods
	public boolean validatePosition(Spot s) {
		boolean valid = s.getContent().size() < s.getMaxSize();
		
		return valid;
	}
	
	// Overrides
	public String toString() {
		return "Food";
	}

}
