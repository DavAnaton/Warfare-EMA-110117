package models;

public class Tank extends Piece {

	private static boolean here = false;
	private final static boolean [][] TankMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true,  here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static boolean [][] TankShootingMask = {	{false, false,  true, false, false},
															{false, false,  true, false, false},
															{false, false,  true, false, false},
															{ true,  true,  here,  true,  true},
															{false, false, false, false, false},
															{false, false, false, false, false}};
	
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
	public Tank copy(){
		Tank copy = new Tank(this.x, this.y, this.player);
		copy.movementMask = this.movementMask;
		copy.shootingMask = this.shootingMask;
		return copy;
	}
	public String toString(){
		return "R" + this.player;
	}
}
