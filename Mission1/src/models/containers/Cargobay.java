package models.containers;

import java.util.ArrayList;

import models.shipments.Ammo;
import models.shipments.Artillery;
import models.shipments.GenericShipment;

public class Cargobay {
	// Attributes
	private ArrayList<Spot> cargoBay;
	private int spotMaxSize;
	
	// Constructors
	public Cargobay() {
		this.cargoBay = new ArrayList<Spot>();
		this.spotMaxSize = 0;
	}
	public Cargobay(int sizeCargo) {
		this();
		for (int i = 0; i < sizeCargo; i++) {
			this.cargoBay.add(new Spot());
		}
	}
	public Cargobay(int sizeCargo, int sizeSpot) {
		this();
		this.spotMaxSize = sizeSpot;
		for (int i = 0; i < sizeCargo; i++) {
			this.cargoBay.add(new Spot(sizeSpot));
		}
	}
	public Cargobay(ArrayList<Spot> cargo, int sizeSpot) {
		this();
		this.cargoBay = cargo;
		this.spotMaxSize = sizeSpot;
	}
	
	// Getters & Setters
	public ArrayList<Spot> getSpots(){
		return this.cargoBay;
	}
	public int getSpotMaxSize(){
		return this.spotMaxSize;
	}

	// Methods
	/**
	 * Transforms the cargo bay into a represatation based on Strings for storage in 
	 * algorithms.State
	 * @return The serialization
	 */
	public ArrayList<ArrayList<String>> serialize(){
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		for (Spot spot : cargoBay) {
			ArrayList<String> spotValues = new ArrayList<String>();
			for (GenericShipment shipment: spot.getContent()) {
				spotValues.add(shipment.toString());
			}
			values.add(spotValues);
		}
		return values;
	}
	/**
	 * Creates a cargo bay from its serialization.
	 * @param values The serialization
	 * @param spotMaxSize The max size of each spot in the cargo
	 * @return An object of type models.Cargobay corresponding to the given serialization
	 */
	public static Cargobay deserialize(ArrayList<ArrayList<String>> values, int spotMaxSize){
		ArrayList<Spot> allSpots = new ArrayList<Spot>();
		
		for (ArrayList<String> spot : values) {
			Spot s = new Spot(spotMaxSize);
			for (String shipment: spot) {
				s.placeShipment(GenericShipment.fromString(shipment), Spot.BOTTOM);
			}
			allSpots.add(s);
		}
		return new Cargobay(allSpots, allSpots.get(0).getMaxSize());
	}
	/**
	 * Checks if the cargo is already full
	 * TODO: this function
	 * @return True if the cargo bay is full, false otherwise.
	 */
	public boolean isFull(){
		boolean isFull = true;
		isFull = false;
		return isFull;
	}

	// Display
	public String toString(){
		String display = "";
		
		for (Spot spot : cargoBay) {
			display += "Emplacement " + this.cargoBay.indexOf(spot) + "\n";
			display += spot.toString("\t");
		}
		
		return display;
	}
	
}
