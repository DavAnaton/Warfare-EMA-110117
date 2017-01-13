package models;

public class Rook extends Piece {

	private static boolean here = false;
	private final static boolean [][] RookMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static boolean [][] RookShootingMask = {	{false, false, false, false, false},
															{ true, false,  true, false,  true},
															{false,  true,  true,  true, false},
															{ true,  true,  here,  true,  true},
															{false,  true,  true,  true, false},
															{ true, false,  true, false,  true}};
	
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
	public Rook copy(){
		Rook copy = new Rook(this.x, this.y, this.player);
		copy.movementMask = this.movementMask;
		copy.shootingMask = this.shootingMask;
		return copy;
	}
	public String toString(){
		return "S" + this.player;
	}

}
