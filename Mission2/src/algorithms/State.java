package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import models.Boat;

public class State {
	// Attributes
	private ArrayList<Boat> shore0;
	private ArrayList<Boat> shore1;
	private int securityTeam;

	// Attributes for AStar
	private int heuristicCost;
	private int finalCost;
	private State parent;
	private boolean closed;

	// Constructors
	public State() {
		this.shore0 = new ArrayList<Boat>();
		this.shore1 = new ArrayList<Boat>();
		this.securityTeam = 0;
		this.heuristicCost = 0;
		this.finalCost = 0;
		this.closed = false;
		this.parent = null;
	}
	public State(State initial) {
		this();
		this.shore0 = new ArrayList<Boat>(initial.shore0);
		this.shore1 = new ArrayList<Boat>(initial.shore1);
		this.securityTeam = initial.securityTeam;
		this.finalCost = initial.finalCost;
		this.closed = false;
		this.parent = initial;
	}
	
	// Getters & Setters
	public void pushShore0(Boat boat) {
		this.shore0.add(boat);
	}
	public void pushShore1(Boat boat) {
		this.shore1.add(boat);
	}
	public void close() {
		this.closed = true;
	}
	public State getParent() {
		return parent;
	}

	// Methods
	
	/**
	 * Generates the list of state children to this one
	 * @return The list of children
	 */
	public ArrayList<State> nextState() {
		ArrayList<State> nextStates = new ArrayList<State>();
		if (this.securityTeam == 0) {
			for (int i = 0; i < shore0.size(); i++) {
				for (int j = i + 1; j < shore0.size(); j++) {
					State newState = new State(this);
					newState.moveBoats(i, j);
					nextStates.add(newState);
				}
			}
		} else {
			for (int i = 0; i < shore1.size(); i++) {
				State newState = new State(this);
				newState.moveBoat(i);
				nextStates.add(newState);
			}

		}

		return nextStates;
	}
	/**
	 * Move 2 boats from shore 0 to shore 1
	 * @param i The index of the first boat
	 * @param j The index of the second boat
	 */
	private void moveBoats(int i, int j) {
		// Remove bigger index first so the second one doesn't change
		Boat first = this.shore0.remove(Integer.max(i, j));
		Boat second = this.shore0.remove(Integer.min(i, j));

		this.finalCost += Integer.max(first.getTime(), second.getTime());
		this.securityTeam = 1;
		this.pushShore1(first);
		this.pushShore1(second);

		this.calcHeuristic();
	}
	/**
	 * Move 1 boat from shore 1 to shore 0
	 * @param i The index of the boat
	 */
	private void moveBoat(int i) {
		Boat b = this.shore1.remove(i);

		this.finalCost += b.getTime();
		this.securityTeam = 0;

		this.pushShore0(b);
		this.calcHeuristic();
	}
	/**
	 * Calculate what the final state would look like
	 * @return The state we want to reach
	 */
	public State finalState() {
		State finalState = new State(this);
		finalState.shore1.addAll(finalState.shore0);
		finalState.shore0.clear();
		finalState.securityTeam = 1;
		return finalState;
	}
	/**
	 * Calculates the heuristic associated with the current state.
	 * In other terms, it is an idea of how far we are from the final state.
	 * It allows us to explore only the most promising states.
	 * 
	 * The formula we chose is the sum of the following:
	 * 
	 * - The sum of the time each couple on shore0 would take to cross the lake
	 * - The time the fastest boat would take to carry the security team each time a couple crosses the lake
	 * 
	 */
	public void calcHeuristic() {
		int heuristicCost = 0;
		if (this.shore0.size() != 0) {
			Collections.sort(this.shore0, new BoatCompare());

			int minBoat = this.shore0.get(0).getTime();
			for (Boat boat : shore1) {
				if (boat.getTime() < minBoat) {
					minBoat = boat.getTime();
				}
			}
			// Trip to bring back security team
			if (this.securityTeam == 1) {
				heuristicCost += minBoat;
			}
			// Couples traveling together
			for (int i = this.shore0.size() - 1; i >= 0; i = i - 2) {
				heuristicCost += this.shore0.get(i).getTime();
				// Trip to bring back security team
				heuristicCost += minBoat;
			}

			// Don(t bring back security team on last trip
			heuristicCost -= minBoat;

		}
		this.heuristicCost = heuristicCost;
	}

	// Display
	public String toString() {
		String display = "";
		String showStream = securityTeam == 1 ? "\t\t <-- " : " --> \t\t";
		int shoreMaxSize = shore0.size() > shore1.size() ? shore0.size() : shore1.size();
		for (int i = 0; i < shoreMaxSize; i++) {
			String boat0 = format8(i < shore0.size() ? shore0.get(i).getName() : "");
			String boat1 = i < shore1.size() ? shore1.get(i).getName() : "";
			display += boat0 + showStream + boat1 + "\n";
		}
		display += "F:" + this.finalCost + " H:" + this.heuristicCost;
		display += "\n";
		return display;
	}
	/**
	 * Display the name of the boat on 8 characters to display a nice table on the command line
	 * @param name The name of the boat
	 * @return The name with some added white spaces.
	 */
	private String format8(String name) {
		String formated = "";
		for (int i = 0; i < 8; i++) {
			if (i < name.length()) {
				formated += name.charAt(i);
			} else {
				formated += " ";
			}
		}
		return formated;
	}
	
	// Customs behaviors
	public boolean equals(State s) {
		return this.shore1.size() == s.shore1.size();
	}

	public boolean isInferior(State s) {
		boolean bool = ((this.finalCost < s.finalCost)
				|| (this.finalCost == s.finalCost) && (this.heuristicCost < s.heuristicCost));
		return bool;
	}

}

/**
 * This Class is used to compare boats and sort the shores. It is used in calcHeuristic()
 *
 */
class BoatCompare implements Comparator<Boat> {
	public int compare(Boat s1, Boat s2) {
		return s1.getTime() - s2.getTime();
	}
}
