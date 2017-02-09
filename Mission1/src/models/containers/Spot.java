package models.containers;

import java.util.ArrayList;
import models.shipments.GenericShipment;

public class Spot {
	public static boolean TOP = true;
	public static boolean BOTTOM = false;
	
	// Attributes
	private ArrayList<GenericShipment> content;
	private int maxSize;

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

	// Getters & Setters
	public  ArrayList<GenericShipment> getContent(){
		return this.content;
	}
	public  int getMaxSize(){
		return this.maxSize;
	}
	
	// Methods
	/**
	 * See placeShipment(GenericShipment, boolean)
	 * @param s The shipment to place
	 * @return True if the shipment was placed successfully, false otherwise.
	 */
	public boolean placeShipment(GenericShipment s){
		return this.placeShipment(s, Spot.TOP);
	}
	/**
	 * Verifies if the shipment can go to this spot and then places it.
	 * 
	 * @param s The shipment to place
	 * @param position The place in the spot where you want to place it. Can be Spot.TOP or Spot.BOTTOM
	 * 					*WARNING*: Spot.BOTTOM is only used to copy a spot. It doesn't check any rule and just places the box
	 * @return True if the shipment was placed successfully, false otherwise.
	 */
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
	
	// Display
	public String toString(){
		return this.toString("");
	}
	/**
	 * If you want to add a specific number of tabs before each line of the content of this spot
	 * @param initialTabs
	 * @return
	 */
	public String toString(String initialTabs){
		String display = "";
		
		for (GenericShipment genericShipment : content) {
			display += initialTabs + genericShipment + "\n";
		}
		
		return display;
	}

}
