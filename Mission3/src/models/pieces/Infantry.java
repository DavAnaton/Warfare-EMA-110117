package models.pieces;

public class Infantry extends Piece {

	private static boolean here = true;
	private final static boolean [][] InfantryMovementMask = {	{false, false, false, false, false},
																{ true, false,  true, false,  true},
																{false,  true,  true,  true, false},
																{ true,  true,  here,  true,  true},
																{false,  true,  true,  true, false},
																{ true, false,  true, false,  true}};

	private final static int [][] InfantryShootingMask = {	{0, 1, 0},
															{1, 0, 1},
															{0, 0, 0}};

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
	public Infantry copy(){
		return new Infantry(this);
	}
	public String toString(){
		return "L" + this.player;
	}
}
