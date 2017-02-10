package models.pieces;

import java.util.ArrayList;

public class Rook extends Piece {
	
	// Attributes
	private static boolean here = true; // used to quickly find the middle of the mask 
										// when you look at it
	private final static boolean [][] RookMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static int [][] RookShootingMask = {	{2, 2, 2},
														{2, 0, 2},
														{2, 2, 2}};
	
	// Constructors
	public Rook() {
		super();
		this.shootingMask = RookShootingMask;
		this.movementMask = RookMovementMask;
	}
	public Rook(int x, int y, int player) {
		super(x, y, player);
		this.shootingMask = RookShootingMask;
		this.movementMask = RookMovementMask;
	}
	public Rook(Rook r){
		this(r.x, r.y, r.player);
	}
	
	// Methods
	public Rook copy(){
		return new Rook(this);
	}
	public void interpolate(ArrayList<int[]> positions) {
		// TODO Auto-generated method stub
		
	}
	
	// Display
	public String toString(){
		return "S" + this.player;
	}

}
