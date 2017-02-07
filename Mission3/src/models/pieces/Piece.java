package models.pieces;

import java.util.ArrayList;

public abstract class Piece {
	protected int x;
	protected int y;
	protected int player;

	protected boolean[][] movementMask = null;
	protected int[][] shootingMask = null;

	public final static int NORTH = 1;
	public final static int NORTHEAST = 2;
	public final static int EAST = 5;
	public final static int SOUTHEAST = 8;
	public final static int SOUTH = 7;
	public final static int SOUTHWEST = 6;
	public final static int WEST = 3;
	public final static int NORTHWEST = 0;

	protected Piece() {
		this.x = 0;
		this.y = 0;
		this.player = 0;
	}

	protected Piece(int x, int y, int player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}

	public abstract Piece copy();

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<ArrayList<int[]>> getMoves() {
		ArrayList<ArrayList<int[]>> moves = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i < movementMask.length; i++) {
			for (int j = 0; j < movementMask[0].length; j++) {
				if (movementMask[i][j]) {
					ArrayList<int[]> move = new ArrayList<int[]>(); // Holder
																	// for all
																	// the
																	// positions
																	// that
																	// compose
																	// the move
					move.add(new int[] { this.x, this.y }); // Initial position
					move.add(new int[] { j - 2 + this.x, i - 3 + this.y }); // final
																			// position
					this.interpolate(move);
					moves.add(move);
				}
			}
		}
		return moves;
	}

	public ArrayList<ArrayList<int[]>> getShots() {
		ArrayList<ArrayList<int[]>> shots = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i < this.shootingMask.length; i++) {
			for (int j = 0; j < this.shootingMask[0].length; j++) {
				
					ArrayList<int[]> shot = new ArrayList<int[]>(); // Holder for all the positions that are destroyed with this shot
					int direction = i * 3 + j, strength = this.shootingMask[i][j];
					shot.addAll(this.generateShotRange(direction, strength));
					shots.add(shot);
					
			}
		}
		return shots;
	}

	public void interpolate(ArrayList<int[]> positions) {

	}

	public ArrayList<int[]> generateShotRange(int direction, int strength) {
		ArrayList<int[]> shotRange = new ArrayList<int[]>();
		int dx = 0, dy = 0;
		switch (direction) {
		case Piece.NORTHWEST:
			dx = -1;
			dy = -1;
			break;
		case Piece.NORTH:
			dy = -1;
			break;
		case Piece.NORTHEAST:
			dx = 1;
			dy = -1;
			break;
		case Piece.EAST:
			dx = 1;
			break;
		case Piece.SOUTHEAST:
			dx = 1;
			dy = 1;
			break;
		case Piece.SOUTH:
			dy = 1;
			break;
		case Piece.SOUTHWEST:
			dx = -1;
			dy = 1;
			break;
		case Piece.WEST:
			dx = -1;
			break;
		}
		for (int i = 1; i <= strength; i++) {
			shotRange.add(new int[] { i * dx + this.x, i * dy + this.y });
		}
		return shotRange;
	}

	public abstract String toString();

}
