package models.shipments;

import java.util.ArrayList;

import models.containers.Spot;

public class Ammo extends GenericShipment {

	// Constructors
	public Ammo() {
		super();
	}

	// Methods
	public boolean validatePosition(Spot s) {
		boolean valid = s.getContent().size() < s.getMaxSize();

		ArrayList<GenericShipment> content = s.getContent();

		for (GenericShipment shipment : content) {

			// Not 2 Ammo in the same spot
			if (shipment.getClass() == this.getClass()) {
				valid = false;
			}

			// Not on a Food shipment
			if (shipment.getClass() == models.shipments.Food.class) {
				valid = false;
			}
		}

		return valid;
	}

	// Overrides
	public String toString() {
		return "Ammo";
	}

}
