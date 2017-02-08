package models.shipments;

import models.containers.Spot;

public abstract class GenericShipment {

	public GenericShipment() {
	}

	public abstract String toString();
	
	public abstract boolean validatePosition(Spot s);
	
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
	
}
