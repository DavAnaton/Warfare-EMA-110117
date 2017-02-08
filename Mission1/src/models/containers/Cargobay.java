package models.containers;

import java.util.ArrayList;

import models.shipments.Ammo;
import models.shipments.Artillery;
import models.shipments.GenericShipment;

public class Cargobay {
	// Attributes
	private ArrayList<Spot> cargoBay;
	public ArrayList<Spot> getSpots(){
		return this.cargoBay;
	}

	private int spotMaxSize;	
	public int getSpotMaxSize(){
		return this.spotMaxSize;
	}

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

	// Methods
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
	
	public boolean isFull(){
		boolean isFull = true;
		isFull = false; /// TODO here
		return isFull;
	}

	// Overrides
	public String toString(){
		String display = "";
		
		for (Spot spot : cargoBay) {
			display += "Emplacement " + this.cargoBay.indexOf(spot) + "\n";
			display += spot.toString("\t");
		}
		
		return display;
	}
	
}
