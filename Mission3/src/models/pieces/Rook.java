package models.pieces;

public class Rook extends Piece {

	private static boolean here = true;
	private final static boolean [][] RookMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static int [][] RookShootingMask = {	{2, 2, 2},
														{2, 0, 2},
														{2, 2, 2}};
	
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
	public Rook copy(){
		return new Rook(this);
	}
	public String toString(){
		return "S" + this.player;
	}

}
