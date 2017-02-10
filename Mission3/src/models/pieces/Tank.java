package models.pieces;

import java.util.ArrayList;

public class Tank extends Piece {
	
	// Attributes
	private static boolean here = true; // used to quickly find the middle of the mask 
										// when you look at it
	private final static boolean [][] TankMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static int [][] TankShootingMask = {	{0, 2, 0},
														{2, 0, 2},
														{0, 0, 0}};
	
	// Constructors
	public Tank() {
		super();
		this.shootingMask = TankShootingMask;
		this.movementMask = TankMovementMask;
	}
	public Tank(int x, int y, int player) {
		super(x, y, player);
		this.shootingMask = TankShootingMask;
		this.movementMask = TankMovementMask;
	}
	public Tank(Tank t){
		this(t.x, t.y, t.player);
	}
	public void interpolate(ArrayList<int[]> positions) {
		// TODO Auto-generated method stub
		
	}
	
	// Methods
	public Tank copy(){
		return new Tank(this);
	}
	
	// Display
	public String toString(){
		return "R" + this.player;
	}
}
