package algorithms;

import java.util.ArrayList;

import models.containers.Cargobay;
import models.shipments.GenericShipment;

public class DFS {

	private static ArrayList<ArrayList<State>> tree;
	private static ArrayList<Integer> childIndexes;

	private static int level;

	public static State DFS(Cargobay c, ArrayList<GenericShipment> l) {
		State init = new State(c, l);

		// Create first level
		ArrayList<State> firstLevel = new ArrayList<State>();
		firstLevel.add(init);

		// Create Tree
		tree = new ArrayList<ArrayList<State>>();
		tree.add(firstLevel);

		// Create childIndexes to browse states on the same level
		childIndexes = new ArrayList<Integer>();

		// Begining on first level, first node (initial state)
		level = 0;
		childIndexes.add(0);

		State currentNode;

		do {
			// When there's no node available on this level
			if (childIndexes.get(level) == tree.get(level).size()) {
				level = remove(level);
			} else {

				// Read Node
				currentNode = tree.get(level).get(childIndexes.get(level));

				// we found the last node
				if (currentNode.getScore() == 0) {
					break;
				} else {
					// Display node

					ArrayList<State> childs = currentNode.getNexts();
					if (childs != null && childs.size() > 0) {
						// Add a new level
						tree.add(childs);
						// And read the first node in it
						childIndexes.add(0);
						level++;
					} else {
						level = remove(level);
					}
				}
			}
		} while (true);

		return currentNode;
	}


	private static int remove(int level) {
		// Removes data related to that level
		tree.remove(level);
		childIndexes.remove(level);

		// Go to next node on the previous level
		childIndexes.set(level - 1, childIndexes.get(level - 1) + 1);
		// And move to that level
		level--;

		return level;
	}

	private static void displayCurrentNode() {
		System.out.println("LEVEL:" + level + " POSSIBILITY:" + (childIndexes.get(level)+1) + "/" + tree.get(level).size());
	}

}
