package models.players;

import java.util.ArrayList;

import algorithms.Minimax;
import algorithms.State;
import models.Board;
import models.Game;

public class AI extends Player {
	private int strength;

	public AI() {
		super();
		this.game = null;
		this.strength = 3;
	}

	public AI(int s) {
		this();
		this.strength = s;
	}

	public AI(Game g) {
		this();
		this.game = g;
	}

	public void play() {
		State bestState = Minimax.Minimax(this.game, this.strength);
//		System.out.println(bestState);
		ArrayList<int[]> shot = bestState.getLastMove();
		ArrayList<int[]> move = new ArrayList<int[]>();
		move.add(shot.remove(0));
		move.add(shot.remove(0));
		this.game.getBoard().move(move);
		this.game.getBoard().shoot(shot);
	}
}
