import java.util.ArrayList;
import java.util.Iterator;

import algorithms.AStar;
import algorithms.State;
import models.Boat;

public class Main {
	public static void main(String[] args) {
		
		State init = generateInitialState();
		
		ArrayList<State> path = AStar.apply(init);
		AStar.display(path);
	}
	
	/**
	 * Generates the initial state we're asked to solve
	 * @return The state itself?
	 */
	public static State generateInitialState(){
		State init = new State();
		init.pushShore0(new Boat("XC21", 45));
		init.pushShore0(new Boat("XC56", 90));
		init.pushShore0(new Boat("XC100", 225));
		init.pushShore0(new Boat("XC800", 360));
		return init;
	}

}
