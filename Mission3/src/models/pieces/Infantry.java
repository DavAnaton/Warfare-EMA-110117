package models.pieces;

import java.util.ArrayList;

public class Infantry extends Piece {

	// Attributes
	private static boolean here = true; // used to quickly find the middle of the mask 
										// when you look at it
	private final static boolean [][] InfantryMovementMask = {	{false, false, false, false, false},
																{ true, false,  true, false,  true},
																{false,  true,  true,  true, false},
																{ true,  true,  here,  true,  true},
																{false,  true,  true,  true, false},
																{ true, false,  true, false,  true}};

	private final static int [][] InfantryShootingMask = {	{0, 1, 0},
															{1, 0, 1},
															{0, 0, 0}};
	// Constructors
	public Infantry() {
		super();
		this.shootingMask = InfantryShootingMask;
		this.movementMask = InfantryMovementMask;
	}
	public Infantry(int x, int y, int player) {
		super(x, y, player);
		this.shootingMask = InfantryShootingMask;
		this.movementMask = InfantryMovementMask;
	}
	public Infantry(Infantry i){
		this(i.x, i.y, i.player);
	}
	
	// Methods
	public Infantry copy(){
		return new Infantry(this);
	}
	public void interpolate(ArrayList<int[]> positions) {
		// TODO Auto-generated method stub
		
	}
	
	// Display
	public String toString(){
		return "L" + this.player;
	}
}
