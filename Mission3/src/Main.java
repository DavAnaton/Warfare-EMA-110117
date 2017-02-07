import java.util.ArrayList;
import java.util.Iterator;

import algorithms.State;
import models.Game;
import models.players.AI;
import models.players.Player;


public class Main {
	public static void main(String[] args) {
		String[][] map = {	{null, null, null, null, null},
							{null, null, null, null, "L0"},
							{null, "S1", null, null, null}};
		
		Game game = new Game(new AI(), new Player(), map);
		
		game.test();
	}
	

}
