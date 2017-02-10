import java.util.ArrayList;
import java.util.Iterator;

import algorithms.State;
import models.Game;
import models.players.AI;
import models.players.Bot;
import models.players.Player;


public class Main {
	public static void main(String[] args) {
		String[][] map = {	{null, null, null, null, null},
							{null, null, null, null, "L0"},
							{"S1", null, null, null, null}};
		
		Game game = new Game(new AI(3), new Bot(), map);
		
		game.play();
		
//		System.out.println(game.getBoard());
	}
	
	/**
	 * Function that creates the map we are supposed to work with
	 * @return The map
	 */
	public static String[][] getRealMap(){
		return new String[][]{	
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, "S1", null, null, "S1", null, null, null, "S1", null, null, null, "S1", null, null, "S1", null, null, null, null, null},
			{null, null, null, "R1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "R1", null, null, null},
			{null, null, null, null, null, "L1", null, "L1", null, "L1", null, "L1", null, "L1", null, "L1", null, "L1", null, "L1", null, null, null, null, null},
			{null, null, "R1", null, null, null, null, null, null, null, "L1", null, "L1", null, "L1", null, null, null, null, null, null, null, "R1", null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, "RO", null, null, null, null, null, null, null, "LO", null, "LO", null, "LO", null, null, null, null, null, null, null, "RO", null, null},
			{null, null, null, null, null, "LO", null, "LO", null, "LO", null, "LO", null, "LO", null, "LO", null, "LO", null, "LO", null, null, null, null, null},
			{null, null, null, "RO", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "RO", null, null, null},
			{null, null, null, null, null, "SO", null, null, "SO", null, null, null, "SO", null, null, null, "SO", null, null, "SO", null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
		};
	}

}
