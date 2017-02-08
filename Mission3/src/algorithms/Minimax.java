package algorithms;

import java.util.ArrayList;

import models.Game;

public class Minimax {
	private static ArrayList<ArrayList<State>> tree;
	private static ArrayList<Integer> childIndexes;
	private static ArrayList<Float> maxs;
	private static ArrayList<Float> mins;
	
	private static int level;
	private static boolean updating;

	public static State Minimax(Game currentGame, int iteration) {
		State bestState = null;

		State init = new State(currentGame);
		ArrayList<State> firstLevel = new ArrayList<State>();
		firstLevel.add(init);

		tree = new ArrayList<ArrayList<State>>();
		tree.add(firstLevel);

		childIndexes = new ArrayList<Integer>();
		childIndexes.add(0); // begin at index 0 (init) on first level

		maxs = new ArrayList<Float>();
		maxs.add(-Float.MAX_VALUE);
		mins = new ArrayList<Float>();
		mins.add(Float.MAX_VALUE);

		level = 0; // begin at level 0 (init)
		updating = false;

		State nextToExplore;

		do {
			nextToExplore = tree.get(level).get(childIndexes.get(level));
			displayNode(null, nextToExplore);
			
			if (updating) {
				if (level == 0) {
					break;
					
				} else {
					if(nextToExplore.getScore() == 0){
						if(level%2 == 1){
							nextToExplore.setScore(maxs.get(level + 1));
						}else{
							nextToExplore.setScore(mins.get(level + 1));
						}

					}
					pl("MAX:" + maxs.get(level+1) + " MIN:" + mins.get(level+1));
					pl(nextToExplore);
					tree.remove(level + 1);
					childIndexes.remove(level + 1);
					maxs.remove(level + 1);
					mins.remove(level + 1);

					if (childIndexes.get(level) + 1 < tree.get(level).size()) {
						childIndexes.set(level, childIndexes.get(level) + 1);
						updating = false;
						continue;
					}
					level--;
				}

			} else if (level == iteration) {
				// we calc the min and max
				float min = Float.MAX_VALUE, max = -Float.MAX_VALUE;
				int minIndex = 0, maxIndex = 0;

				for (int i = 0; i < tree.get(level).size(); i++) {
					childIndexes.set(level, i);
					float score = tree.get(level).get(i).calcScore();

					displayNode(null, nextToExplore);
					if (score > max) {
						max = score;
					}
					if (score < min) {
						min = score;
					}

				}

				maxs.set(level, max);
				mins.set(level, min);

				updating = true;

				level--;
			} else {
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
//					System.out.println(nextToExplore);
					updating = true;
				}
			}
		} while (true);

		float bestPossibleScore = -Float.MAX_VALUE;
		
		for (int i = 0; i < tree.get(1).size(); i++) {
			State state = tree.get(1).get(i);
			if(state.getScore() >= bestPossibleScore){
				bestPossibleScore = state.getScore();
				bestState = state;
			}
		}
		pl(bestState);
		return bestState;
	}
	
	private static void displayCurrentNode(){
		String display = "";
		for (int i = 0; i <= level; i++) {
			display += childIndexes.get(i);
			if(i<level) display += "-";
		}
		System.out.println("LEVEL "+level+" GOTO " + display);
	}
	
	private static void displayNode(int[] address, State node){
		if(address != null){
		boolean display = childIndexes.size() >= address.length;
		for (int i = 0; i < address.length; i++) {
			display = display && childIndexes.get(i) == address[i];
		}
		if(display){
			displayCurrentNode();
			pl(node);
		}
		}else{
			displayCurrentNode();
			pl(node);
		}
	}
	private static void pl(Object o){
		System.out.println(o);
	}
	
}
