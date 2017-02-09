package algorithms;

import java.util.ArrayList;

import models.shipments.*;
import models.containers.*;

/**
 * Represents a state that holds a cargo bay and every remaing item to store in it.
 */
public class State {

	// Attributes
	private ArrayList<ArrayList<String>> cargo;
	private ArrayList<String> loadingDock;
	
	private int score;
	private int spotMaxSize;

	// Constructors
	public State() {
		this.cargo = new ArrayList<ArrayList<String>>();
		this.loadingDock = new ArrayList<String>();
		this.score = Integer.MAX_VALUE;
		this.spotMaxSize = 0;
	}
	public State(Cargobay c, ArrayList<GenericShipment> s) {
		this();
		this.cargo = c.serialize();
		this.spotMaxSize = c.getSpotMaxSize();
		for (GenericShipment genericShipment : s) {
			this.loadingDock.add(genericShipment.toString());
		}
		this.calcScore();
	}
	public State(State s) {
		this();
		for (ArrayList<String> arrayList : s.cargo) {
			ArrayList<String> copy = new ArrayList<String>();
			for (String string : arrayList) {
				copy.add(string);
			}
			this.cargo.add(copy);
		}
		for (String value : s.loadingDock) {
			this.loadingDock.add(value);
		}
		this.score = s.score;
		this.spotMaxSize = s.spotMaxSize;
	}

	// Getters & Setters
	public int getScore() {
		return this.score;
	}

	// Methods
	/**
	 * Generates the list of state children to this one
	 * @return The list of children
	 */
	public ArrayList<State> getNexts() {
		ArrayList<State> nexts = new ArrayList<State>();
		Cargobay cargobay = Cargobay.deserialize(this.cargo, this.spotMaxSize);

		if (this.loadingDock.size() == 0 || cargobay.isFull()) {
			nexts = null;
		}

		else {
			ArrayList<Spot> spotsInCargobay = cargobay.getSpots();
			int sizeOfLoadingDock = this.loadingDock.size();

			for (int i = 0; i < sizeOfLoadingDock; i++) {
				// Removing item from the list to place it
				String typeOfShipment = this.loadingDock.remove(i);
				GenericShipment shipment = GenericShipment.fromString(typeOfShipment);

				for (int j = 0; j < spotsInCargobay.size(); j++) {
					// Verification for all the rules given in the shipment itself
					// (For example, "DO NOT PLACE THIS OVER A FOOD BOX")
					if (shipment.validatePosition(spotsInCargobay.get(j))) {
						State newState = this.generateNextState(j, typeOfShipment);
						boolean isNotDuplicated = true;

						for (State state : nexts) {
							if (state.equals(newState)) {
								isNotDuplicated = false;
							}
						}

						if (isNotDuplicated) {
							newState.calcScore();
							nexts.add(newState);
						}
					}
				}

				this.loadingDock.add(i, typeOfShipment);
			}
		}
		return nexts;
	}
	
	/**
	 * Calculate the score given to this state.
	 * 
	 * The score we chose is the following:
	 * - 1 pt per box that needs to be placed
	 * - 5 pt more for each box that still needs to be placed near to the cockpit
	 * 
	 * It allows to explore those configurations only.
	 * TODO: 	If it is not possible to solve the problem with those boxes near to the 
	 * 			cockpit, we'll have an error (because the score will never reach 0).
	 * 			It would be great to handle this case.
	 */
	private void calcScore() {
		int calc = this.loadingDock.size();

		// Ammo and LightFight near the cockpit
		//
		// If they're part of the loading list
		boolean lightFightSmwhr = false, ammoSmwhr = false;
		for (ArrayList<String> arrayList : cargo) {
			for (String shipment : arrayList) {
				if ("Ammo".equals(shipment)) {
					ammoSmwhr = true;
				} else if ("LightFight".equals(shipment)) {
					lightFightSmwhr = true;
				}
				if (lightFightSmwhr && ammoSmwhr) {
					break;
				}
			}
			if (lightFightSmwhr && ammoSmwhr) {
				break;
			}
		}
		// They need to be in the first spot
		boolean lightFightInFirst = false, ammoInFirst = false;
		for (String shipment : cargo.get(0)) {
			if ("Ammo".equals(shipment)) {
				ammoInFirst = true;
			} else if ("LightFight".equals(shipment)) {
				lightFightInFirst = true;
			}
		}
		if (!lightFightInFirst && lightFightSmwhr) {
			calc += 5;
		}
		if (!ammoInFirst && ammoSmwhr) {
			calc += 5;
		}

		this.score = calc;
	}

	/**
	 * Generates a new state from the current where the box is place
	 * @param position The spot where you want to place the box
	 * @param type The type of the box to place
	 * @return The state generated
	 */
	private State generateNextState(int position, String type) {
		State generated = new State(this);
		generated.cargo.get(position).add(0, type);
		return generated;
	}

	// Display
	public String toString() {
		String display = "";

		display += "__________________________\nCARGO\n";
		for (int i = 0; i < this.cargo.size(); i++) {
			display += "\tEmplacement " + (i + 1) + "\n";
			for (String shipment : this.cargo.get(i)) {
				display += "\t\t" + shipment + "\n";
			}
		}

		display += "REMAINING " + this.score + "\n";
		for (String shipment : this.loadingDock) {
			display += "\t" + shipment + "\n";
		}

		return display;
	}
	
	// Custom behavior
	public boolean equals(State s) {
		boolean isEquals = true;

		if ((s.cargo.size() != 0 && this.cargo.size() != 0) && (s.cargo.size() != this.cargo.size())) {
			isEquals = false;
		} else {
			for (int i = 0; i < this.cargo.size(); i++) {
				if (this.cargo.get(i).size() == 0 && s.cargo.get(i).size() == 0) {
					continue;
				}
				if (this.cargo.get(i).size() != s.cargo.get(i).size()) {
					isEquals = false;
				} else {
					for (int j = 0; j < this.cargo.get(i).size(); j++) {
						if (this.cargo.get(i).get(j) != s.cargo.get(i).get(j)) {
							isEquals = false;
						}
					}
				}
			}
		}

		return isEquals;
	}

}
