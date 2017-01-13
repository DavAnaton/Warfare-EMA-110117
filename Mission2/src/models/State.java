package models;

import java.util.ArrayList;

public class State {
	// Attributes
	private ArrayList<Boat> shore0;
	private ArrayList<Boat> shore1;
	private int securityTeam;

	// Attributes for AStar
	private int heuristicCost;
	private int finalCost;
	private boolean closed;
	private State parent;

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
		this.setShore0(new ArrayList<Boat>(initial.getShore0())); // Sets shore
																	// and calc
																	// heuristic
		this.shore1 = new ArrayList<Boat>(initial.getShore1());
		this.securityTeam = initial.getSecurityTeam() == 1 ? 0 : 1;
		this.finalCost = initial.finalCost;
		this.closed = false;
		this.parent = initial;
	}

	// Methods
	public ArrayList<State> nextState() {
		ArrayList<State> nextStates = new ArrayList<State>();
		if (this.securityTeam == 0) {
			for (int i = 0; i < shore0.size(); i++) {
				for (int j = i + 1; j < shore0.size(); j++) {
					State newState = new State(this);
					Boat b1 = newState.popShore0(j);
					Boat b2 = newState.popShore0(i);
					newState.pushShore1(b1);
					newState.pushShore1(b2);
					int max = b1.getTime()>b2.getTime() ? b1.getTime() : b2.getTime();
					newState.addToFinalCost(max);
					nextStates.add(newState);
				}
			}
		}else{
			for (int i = 0; i < shore1.size(); i++) {
				State newState = new State(this);
				Boat b = newState.popShore1(i);
				newState.pushShore0(b);
				newState.addToFinalCost(b.getTime());
				nextStates.add(newState);
			}			
		}
		return nextStates;
	}
	public State finalState(){
		State finalState = new State(this);
		for (Boat boat : shore0) {
			finalState.pushShore1(finalState.popShore0(0));
		}
		finalState.setSecurityTeam(1);
		finalState.heuristicCost = 100000; // get final at last iteration DEBUG
		return finalState;
	}
	public boolean equals(State s){
		return this.getShore1().size() == s.getShore1().size();
	}
	public boolean isInferior(State s){
		boolean bool = (
				(this.finalCost < s.finalCost)
				||
				(this.finalCost == s.finalCost) && (this.heuristicCost < s.heuristicCost)
				);
		return bool;
	}
	public void calcHeuristic() {
		int heuristicCost = 0;
		for (int i = 0; i < shore0.size(); i++) {
			heuristicCost += shore0.get(i).getTime();
		}
		this.heuristicCost = heuristicCost;
	}

	// Display
	public String toString() {
		String display = "";
		String showStream = securityTeam == 1 ? "\t\t <-- " : " --> \t\t";
		int shoreMaxSize = shore0.size() > shore1.size() ? shore0.size() : shore1.size();
		for (int i = 0; i < shoreMaxSize; i++) {
			String boat0 = format8(i < shore0.size() ? shore0.get(i).getTime() + "" : "");
			String boat1 = i < shore1.size() ? shore1.get(i).getTime() + "" : "";
			display += boat0 + showStream + boat1 + "\n";
		}
		display += "F:" + this.finalCost + " H:" + this.heuristicCost;
		display += "\n";
		return display;
	}

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

	// Getters & Setters
	public void close() {
		this.closed = true;
	}
	public boolean isClosed(){
		return this.closed;
	}
	public ArrayList<Boat> getShore0() {
		return shore0;
	}

	public void setShore0(ArrayList<Boat> shore0) {
		this.shore0 = shore0;
		this.calcHeuristic();
	}

	public void pushShore0(Boat boat) {
		this.shore0.add(boat);
		this.heuristicCost += boat.getTime();
	}

	public Boat popShore0(int i) {
		Boat b = this.shore0.remove(i);
		this.heuristicCost -= b.getTime();
		return b;
	}

	public ArrayList<Boat> getShore1() {
		return shore1;
	}

	public void setShore1(ArrayList<Boat> shore1) {
		this.shore1 = shore1;
	}

	public void pushShore1(Boat boat) {
		this.shore1.add(boat);
	}

	public Boat popShore1(int i) {
		return this.shore1.remove(i);
	}

	public int getSecurityTeam() {
		return securityTeam;
	}

	public void setSecurityTeam(int securityTeam) {
		this.securityTeam = securityTeam;
	}

	public int getFinalCost() {
		return finalCost;
	}
	public void addToFinalCost(int value) {
		this.finalCost += value;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

}
