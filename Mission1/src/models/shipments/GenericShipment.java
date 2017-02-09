package models.shipments;

import models.containers.Spot;

/**
 * Abstract class used to define the generic behavior of a shipment.
 * Also used to create a specific shipment from its name.
 */
public abstract class GenericShipment {

	// Constructors
	public GenericShipment() {
	}
	/**
	 * Create a package based on its content.
	 * @param s The type of the box you need to create
	 * @return The shipment
	 */
	public static GenericShipment fromString(String s){
		GenericShipment shipment = null;
		
		switch(s){
		case "Ammo":
			shipment = new Ammo();
			break;
		case "Food":
			shipment = new Food();
			break;
		case "Artillery":
			shipment = new Artillery();
			break;
		case "LightFight":
			shipment = new LightFight();
			break;
		}
		
		return shipment;
	}
	
	// Methods
	/**
	 * Checks if every rule for this package is respected:
	 * 
	 * - *INSERT RULES HERE* 
	 * 
	 */
	public abstract boolean validatePosition(Spot s);
	
	// Display
	public abstract String toString();
	
}
