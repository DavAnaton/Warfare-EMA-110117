import java.util.ArrayList;
import java.util.Iterator;

import models.Game;
import models.State;


public class Main {
	public static void main(String[] args) {
		char[][] map = {	{' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', 'S', ' '},
							{' ', ' ', ' ', ' ', ' '}};
		Game play = new Game(map);
		play.start();
		State init = new State(play);
		System.out.println(init);
		
	}
	

}
