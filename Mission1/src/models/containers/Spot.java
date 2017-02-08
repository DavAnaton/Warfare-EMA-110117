package models.containers;

import java.util.ArrayList;
import models.shipments.GenericShipment;

public class Spot {
	public static boolean TOP = true;
	public static boolean BOTTOM = false;
	
	// Attributes
	private ArrayList<GenericShipment> content;
	public  ArrayList<GenericShipment> getContent(){
		return this.content;
	}
	private int maxSize;
	public  int getMaxSize(){
		return this.maxSize;
	}

	// Constructors
	public Spot() {
		this.content = new ArrayList<GenericShipment>();
		this.maxSize = 0;
	}
	public Spot(int maxSize) {
		this();
		this.maxSize = maxSize;
	}
	public Spot(ArrayList<GenericShipment> content, int maxSize) {
		this();
		this.maxSize = maxSize;
		for (GenericShipment genericShipment : content) {
			if(this.content.indexOf(genericShipment) < this.maxSize){
				this.content.add(genericShipment);
			}
		}
	}

	// Methods
	public boolean placeShipment(GenericShipment s){
		return this.placeShipment(s, Spot.TOP);
	}
	public boolean placeShipment(GenericShipment s, boolean position){
		boolean successfulPlacement = true;
		
		// Already full
		if(this.content.size() == this.maxSize){
			successfulPlacement = false;
		}
		else if(position == Spot.TOP){
			successfulPlacement = s.validatePosition(this);
			if (successfulPlacement) {
				this.content.add(0, s);
			}
		}
		else{
			// Copy from deserialize => no check needed
			this.content.add(s);
		}
		
		return successfulPlacement;
	}
	
	// Override
	public String toString(){
		return this.toString("");
	}
	public String toString(String initialTabs){
		String display = "";
		
		for (GenericShipment genericShipment : content) {
			display += initialTabs + genericShipment + "\n";
		}
		
		return display;
	}

}
