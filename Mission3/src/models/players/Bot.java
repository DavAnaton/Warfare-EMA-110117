package models.players;

import java.util.ArrayList;

import models.Board;
import models.pieces.Piece;
/**
 * A player that plays automatically a random move
 */
public class Bot extends Player {
	// Constructors
	public Bot() {
		// TODO Auto-generated constructor stub
	}

	// Methods
	/**
	 * Plays a random move and a random shot among all the possibilities
	 */
	public void play() {
		Board b = this.game.getBoard();
		updatePiecesList(b);

		int indexOfPiece = (int) Math.floor(Math.random() * this.pieces.size());
		Piece p = this.pieces.get(indexOfPiece);

		ArrayList<ArrayList<int[]>> possibleMoves = p.getMoves();
		boolean moveIsValid = false;
		ArrayList<int[]> move;
		do {
			int indexOfMove = (int) Math.floor(Math.random() * possibleMoves.size());
			move = possibleMoves.get(indexOfMove);
			moveIsValid = b.validateMove(move);
		} while (!moveIsValid);
		b.move(move);

		boolean shotIsValid;
		ArrayList<ArrayList<int[]>> possibleShots = p.getShots();
		ArrayList<int[]> shot;
		do {
			int indexOfShot = (int) Math.floor(Math.random() * possibleShots.size());
			shot = possibleShots.get(indexOfShot);
			shotIsValid = b.validateShot(shot);
		} while (!shotIsValid);
		b.shoot(shot);

	}

}
