package models.pieces;

public class Tank extends Piece {

	private static boolean here = true;
	private final static boolean [][] TankMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static int [][] TankShootingMask = {	{0, 2, 0},
														{2, 0, 2},
														{0, 0, 0}};
	
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
	public Tank copy(){
		return new Tank(this);
	}
	public String toString(){
		return "R" + this.player;
	}
}
