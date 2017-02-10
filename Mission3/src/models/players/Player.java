package models.players;

import java.util.ArrayList;
import java.util.Iterator;

import models.Board;
import models.Game;
import models.pieces.Piece;
/**
 * Class used to define how a human would play the game
 * TODO: add user interaction. For now, this class could be abstract...
 */
public class Player {
	// Attributes
	protected Game game;
	protected ArrayList<Piece> pieces;

	// Constructors
	public Player() {
		this.game = null;
		this.pieces = new ArrayList<Piece>();
	}
	
	// Getters && Setters
	public int getNbPieces(){
		return this.pieces.size();
	}
	public void setGame(Game g){
		this.game = g;
	}
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
	
	// Methods
	/**
	 * Add a piece to the pieces the player owns
	 * @param p
	 */
	public void placePiece(Piece p){
		this.pieces.add(p);
	}
	
	/**
	 * The player is asked to play a move and a shot
	 */
	public void play(){
		for (Piece piece : this.pieces) {
			System.out.println(piece);
		}
	}
	
	/**
	 * The player looks at the board and sees if the opponent destroyed one of his pieces.
	 * @param b The board
	 */
	public void updatePiecesList(Board b){
		ArrayList<Piece> toRemove = new ArrayList<Piece>();
		for (Piece piece : this.pieces) {
			if(b.get(piece.getX(), piece.getY()) == null){
				toRemove.add(piece);
			}
		}
		for (Piece piece : toRemove) {
			this.pieces.remove(piece);
		}
	}
	
	
	// Display
	public String toString(){
		String display = "";
		for (Piece piece : this.pieces) {
			display += piece;
		}
		return display;
	}

}
