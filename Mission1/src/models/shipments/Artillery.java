package models.shipments;

import java.util.ArrayList;

import models.containers.Spot;

public class Artillery extends GenericShipment {

	// Constructors
	public Artillery() {
		super();
	}

	// Methods
	public boolean validatePosition(Spot s) {
		boolean valid = s.getContent().size() < s.getMaxSize();
		
		int artilleryAlreadyInSpot = 0;
		ArrayList<GenericShipment> content = s.getContent();
		
		for (GenericShipment shipment : content) {
			
			// Not placed over a different shipment
			if(shipment.getClass() != this.getClass()){
				valid = false;
			}
			
			// Or count how much Artilleries
			else{
				artilleryAlreadyInSpot++;
			}
		}
		
		// No more than 2 Artillery in the same spot
		if(artilleryAlreadyInSpot == 2){
			valid = false;
		}
		
		return valid;
	}
	
	// Overrides
	public String toString() {
		return "Artillery";
	}

}
