package algorithms;

import java.util.ArrayList;

public class BFS {
	public static State BFS(State init, int iteration) {
		
		ArrayList<State> stack = new ArrayList<State>();
		ArrayList<State> visited = new ArrayList<State>();
		stack.add(init);
		
		int i = 0;
		
		do{
			State current = stack.remove(0);
			stack.addAll(current.getNexts());
			visited.add(current);
			i++;
		}while(stack.size()!=0 && i < iteration);

		ArrayList<State> moves = new ArrayList<State>();
		for (int j = 0; j < stack.size(); j++) {
			if(stack.get(i).equals(null)){// HEEEEERRRE
				
			}
		}
		for (int j = visited.size() - 1; j >= 0; j--) {
			visited.get(j).updateScore();
			System.out.println(visited.get(j));
		}
		
		return init;
	}
}
