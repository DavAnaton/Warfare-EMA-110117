package models.players;

import java.util.ArrayList;

import algorithms.Minimax;
import algorithms.State;
import models.Board;
import models.Game;
/**
 * A player that play automatically the best move it can find
 */
public class AI extends Player {
	// Attributes
	private int strength; // The Depth of the Minmax algorithm used to play

	// Constructors
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

	// Methods
	/**
	 * The AI uses a Minmax algorithms:
	 * He maximizes its score while reducing the possiblity for its opponent to score a lot.
	 */
	public void play() {
		State bestState = Minimax.Minimax(this.game, this.strength);
		ArrayList<int[]> shot = bestState.getLastMove();
		ArrayList<int[]> move = new ArrayList<int[]>();
		move.add(shot.remove(0));
		move.add(shot.remove(0));
		this.game.getBoard().move(move);
		this.game.getBoard().shoot(shot);
	}
}
