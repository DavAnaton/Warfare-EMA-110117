package models.pieces;

import java.util.ArrayList;
/**
 * Abstract class to state the default behavior of each piece.
 * Each piece can, then, define its own movement and shooting masks.
 */
public abstract class Piece {
	// Static variable to define shooting directions
	public final static int NORTH = 1;
	public final static int NORTHEAST = 2;
	public final static int EAST = 5;
	public final static int SOUTHEAST = 8;
	public final static int SOUTH = 7;
	public final static int SOUTHWEST = 6;
	public final static int WEST = 3;
	public final static int NORTHWEST = 0;
	
	// Attributes
	protected int x;
	protected int y;
	protected int player;

	protected boolean[][] movementMask = null;
	protected int[][] shootingMask = null;

	// Constructors
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
	
	// Getters && Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Methods
	/**
	 * @return A new Piece of the same type as the orginal
	 */
	public abstract Piece copy();

	/**
	 * Updates the coordinates of this piece
	 * @param x The new x coordinate
	 * @param y The new y coordinate
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Generates a list of possible moves based on the piece's movement mask.
	 * **Those moves can be outside of the board**
	 * TODO: Reverse the mask if player 1 is playing. For now, both players play in
	 * 			the same direction
	 * @return The list of legal moves for this piece
	 */
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

	/**
	 * Generates a list of possible shots based on the piece's shooting mask.
	 * **Those shots can be outside of the board**
	 * TODO: Reverse the mask if player 1 is playing. For now, both players play in
	 * 			the same direction
	 * @return The list of legal shots for this piece
	 */
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

	/**
	 * This function adds intermediate positions to a move.
	 * @param positions An array that contains the initial and final positions and 
	 * 			where we can add all the intermediate positions
	 */
	public abstract void interpolate(ArrayList<int[]> positions);

	/*
	 * Generates the coordinates that will be hit by your shot.
	 * @param direction The direction of your aim. Can be Piece.NORTH, Piece.SOUTH...etc
	 * @param strength The strength available
	 */
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
	
	// Display
	public abstract String toString();

}
