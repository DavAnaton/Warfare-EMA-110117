package algorithms;
import java.util.*;

import models.State;

public class AStar {
	
	public static ArrayList<State> apply(State init){
		State finalState = init.finalState();

		ArrayList<State> openStates = new ArrayList<State>();
		openStates.add(init);
		
		int currentIndex = 0;
		State current;
		
		while(true){
//			display(openStates);
			current = pop(openStates);
			
			if(finalState.equals(current)){
				break;
			}
			
			ArrayList<State> next = current.nextState();
			openStates.addAll(next);
			
		}
		
		return constructPath(current);
	}
	
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
//		System.out.println("####################");
//		System.out.println(current);
//		System.out.println("####################");
		return current;
	}
	
	private static ArrayList<State> constructPath(State finalState){
		ArrayList<State> path = new ArrayList<State>();
		State step = finalState;
		while(step != null){
			path.add(0, step);;
			step = step.getParent();
		}
		return path;
	}
	
	public static void display(ArrayList<State> open){
		for (State state : open) {
			System.out.println(state);
		}
		System.out.println("__________________________________________");
	}
}
