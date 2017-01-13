package models;

public class Infantry extends Piece {

	private static boolean here = false;
	private final static boolean [][] InfantryMovementMask = {	{false, false, false, false, false},
																{ true, false,  true, false,  true},
																{false,  true,  true,  true, false},
																{ true,  true,  here,  true,  true},
																{false,  true,  true,  true, false},
																{ true, false,  true, false,  true}};

	private final static boolean [][] InfantryShootingMask = {	{false, false, false, false, false},
																{false, false, false, false, false},
																{false, false,  true, false, false},
																{false,  true,  here,  true, false},
																{false, false, false, false, false},
																{false, false, false, false, false}};
	
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
	public Infantry copy(){
		Infantry copy = new Infantry(this.x, this.y, this.player);
		copy.movementMask = this.movementMask;
		copy.shootingMask = this.shootingMask;
		return copy;
	}
	public String toString(){
		return "L" + this.player;
	}
}
