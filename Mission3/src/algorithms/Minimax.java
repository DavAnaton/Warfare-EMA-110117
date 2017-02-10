package algorithms;

import java.util.ArrayList;

import models.Game;


/**
 * Class used to apply a Minimax on a graph and maximizing the points scored by an AI
 * while minimizing the points scored by an opponent
 *
 */
public class Minimax {
	private static ArrayList<ArrayList<State>> tree;
	private static ArrayList<Integer> childIndexes;
	private static ArrayList<Float> maxs;
	private static ArrayList<Float> mins;
	
	private static int level;
	private static boolean updating;

	/**
	 * Applies to algorithms to a specific game and compute a specific amount of moves to
	 * find out what's the best move to play.
	 * @param currentGame The game being played in its current state
	 * @param iteration The depth in number of move to compute
	 * @return The best move to play
	 */
	public static State Minimax(Game currentGame, int iteration) {
		State bestState = null;

		// Create initial state
		State init = new State(currentGame);
		ArrayList<State> firstLevel = new ArrayList<State>();
		firstLevel.add(init);

		// Add it to the tree
		tree = new ArrayList<ArrayList<State>>();
		tree.add(firstLevel);
		
		// Keep track on where we are on each level of the tree
		childIndexes = new ArrayList<Integer>();
		
		// Max score that can be achieved on a level
		maxs = new ArrayList<Float>();
		maxs.add(-Float.MAX_VALUE);
		// Min score that can be achieved on a level
		mins = new ArrayList<Float>();
		mins.add(Float.MAX_VALUE);

		// Begin at the index 0 of the level 0: Initial state
		level = 0;
		childIndexes.add(0);
		
		// No need to update the min and max values for now, we're just getting started
		// It's changed when we finish exploring a level
		updating = false;

		State nextToExplore;

		do {
			nextToExplore = tree.get(level).get(childIndexes.get(level));
			
			// If updating, we store the score of the current node
			if (updating) {
				if (level == 0) {
					// The initial states knows its best possible score and how to get it
					break;
					
				} else { 
					// If nodeToExplore has children we update it's score
					if(tree.size() > level+1){

						if(level%2 == 1){
							nextToExplore.setScore(maxs.get(level + 1));
							// TODO alpha;beta couple
						}else{
							nextToExplore.setScore(mins.get(level + 1));
						}
					}
					// Otherwise, its a wining/losing state and already has its score
					
					remove(level + 1);

					// Moving on to next node
					if (childIndexes.get(level) + 1 < tree.get(level).size()) {
						// Goto next sibling
						childIndexes.set(level, childIndexes.get(level) + 1);
						updating = false;
					}else{
						level--;
					}
				}

			} 
			
			// We are at the desired depth. We can evaluate the score of each 
			else if (level == iteration) {
				// We find were are the maximum and minimum scores
				float min = Float.MAX_VALUE, max = -Float.MAX_VALUE;
				for (int i = 0; i < tree.get(level).size(); i++) {
					childIndexes.set(level, i);
					float score = tree.get(level).get(i).calcScore();
					max = Float.max(max, score);
					min = Float.min(min, score);


				}
				
				// We set the maximum and minimum value for this level in the tree.
				maxs.set(level, max);
				mins.set(level, min);
				

				// DEBUG
				// if(childIndexes.get(1) == 3) pl (level + "-" + childIndexes.get(level) + " " + maxs.get(level) + " " + mins.get(level));
				// ENDOFDEBUG

				// We update the parent node
				updating = true;
				level--;
			} 
			
			// Not yet at desired depth; we keep digging.
			else {
				tree.add(nextToExplore.getNexts()); // add a level with the
													// childs of the node to
													// explore
				childIndexes.add(0);
				mins.add(Float.MAX_VALUE);
				maxs.add(-Float.MAX_VALUE);
				
				if (tree.get(level + 1).size() > 0) {
					level++;
				} else {
					nextToExplore.setScore(level%2==1? Float.MAX_VALUE: -Float.MAX_VALUE);
					updating = true;
				}
			}
			
			// DEBUG
			// if(!updating) pl("Level " + level + " Node: " + childIndexes.get(level) + "/ " + (tree.get(level).size()-1));
			// ENDOFDEBUG
			
		} while (true);

		float bestPossibleScore = -Float.MAX_VALUE;
		
		for (int i = 0; i < tree.get(1).size(); i++) {
			State state = tree.get(1).get(i);
			if(state.getScore() >= bestPossibleScore){
				bestPossibleScore = state.getScore();
				bestState = state;
			}
		}
		return bestState;
	}

	/**
	 * Internal Method
	 * Removes the current level in the DFS tree and everything related to it
	 * @param level The level you need to erase
	 */
	private static void remove(int level) {
		// Removes data related to that level
		tree.remove(level);
		childIndexes.remove(level);
		maxs.remove(level);
		mins.remove(level);

	}
	
	// DEBUG
	public static void pl(Object o){
		System.out.println(o);
	}
	
}
