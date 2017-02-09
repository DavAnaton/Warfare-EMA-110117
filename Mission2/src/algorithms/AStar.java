package algorithms;
import java.util.*;

/**
 * Class used to apply an A* on a graph and find shortest path to the final state
 *
 */
public class AStar {
	
	/**
	 * Applies the A* algorithm to the state and finds the shortest path to the final state
	 * @param init The initial state
	 * @return An array of all the states between the initial and final states (included)
	 */
	public static ArrayList<State> apply(State init){
		State finalState = init.finalState();

		ArrayList<State> openStates = new ArrayList<State>();
		openStates.add(init);
		
		int currentIndex = 0;
		State current;
		
		while(true){
			current = pop(openStates);
			
			if(finalState.equals(current)){
				break;
			}
			
			ArrayList<State> next = current.nextState();
			openStates.addAll(next);
			
		}
		
		return constructPath(current);
	}
	
	/**
	 * Gives the next state to explore. It searches for the lowest heuristic cost.
	 * In other terms, it looks for the most promising state.
	 * @param open The list of possible states
	 * @return The best state to explore
	 */
	private static State pop(ArrayList<State> open){
		State current;
		int currentIndex = 0;
		
		int length = open.size();
		for (int i = 0; i < length; i++) {
			if(open.get(i).isInferior(open.get(currentIndex))){
				currentIndex = i;
			}
		}

		current = open.get(currentIndex);
		open.remove(current);
		current.close();
		return current;
	}
	
	/**
	 * Start from the ending state and goes all the way back to the initial state to create the list
	 * of state in between.
	 * @param finalState The end node
	 * @return The list of state to use to get to the end state
	 */
	private static ArrayList<State> constructPath(State finalState){
		ArrayList<State> path = new ArrayList<State>();
		State step = finalState;
		while(step != null){
			path.add(0, step);;
			step = step.getParent();
		}
		return path;
	}
	
	// Display
	/**
	 * Used to display a path: a list of states
	 * @param open The path to print
	 */
	public static void display(ArrayList<State> open){
		for (State state : open) {
			System.out.println(state);
		}
		System.out.println("__________________________________________");
	}
}
