package models.players;

import java.util.ArrayList;
import java.util.Iterator;

import models.Board;
import models.Game;
import models.pieces.Piece;

public class Player {
	protected Game game;
	protected ArrayList<Piece> pieces;

	public Player() {
		this.game = null;
		this.pieces = new ArrayList<Piece>();
	}
	
	public void placePiece(Piece p){
		this.pieces.add(p);
	}
	
	public String toString(){
		String display = "";
		for (Piece piece : this.pieces) {
			display += piece;
		}
		return display;
	}

	public void play(){
		for (Piece piece : this.pieces) {
			System.out.println(piece);
		}
	}
	
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
	
	public int getNbPieces(){
		return this.pieces.size();
	}
	public void setGame(Game g){
		this.game = g;
	}
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
}
