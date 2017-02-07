import java.util.ArrayList;

import algorithms.BFS;
import algorithms.State;

public class Main {

	public static void main(String[] args) {
		char[][] map = {	{ ' ', ' ', ' ', ' '},
							{ ' ', ' ', ' ', ' '},
							{ ' ', ' ', 'S', ' '},
							{ ' ', ' ', ' ', 'S'}};
		State init = new State(map);
////		System.out.println(init);
//		ArrayList<State> nexts = new ArrayList<State>();
//		nexts.add(init);
//		nexts.addAll(init.getNexts());
//		for (int i = 0; i < nexts.size(); i++) {
//			System.out.println(nexts.get(i));
//		}
		BFS.BFS(init, 100);
	}

}
