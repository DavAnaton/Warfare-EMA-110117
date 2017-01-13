package models;

public abstract class Piece {
	protected int x;
	protected int y;
	protected int player;
	
	protected boolean [][] movementMask =  new boolean [6][5];
	protected boolean [][] shootingMask =  new boolean [6][5];

	public final static int NORTH = 0;
	public final static int NORTHEAST = 1;
	public final static int EAST = 2;
	public final static int SOUTHEAST = 3;
	public final static int SOUTH = 4;
	public final static int SOUTHWEST = 5;
	public final static int WEST = 6;
	public final static int NORTHWEST = 7;
	
	protected Piece(){
		this.x = 0;
		this.y = 0;
		this.player = 0;
	}
	protected Piece(int x, int y, int player){
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	public void move(int direction, int nbCases){
		boolean validMove;
		switch(direction){
		case Piece.NORTH:
			validMove = this.movementMask[3 - nbCases][2];
			break;		
		case Piece.NORTHEAST:
			validMove = this.movementMask[3 - nbCases][2] && this.movementMask[3][2 + nbCases];
			break;
		case Piece.EAST:
			validMove = this.movementMask[3][2 + nbCases];
			break;
		case Piece.SOUTHEAST:
			validMove = this.movementMask[3 + nbCases][2 + nbCases];
			break;
		case Piece.SOUTH:
			validMove = this.movementMask[3 + nbCases][2];
			break;	
		case Piece.SOUTHWEST:
			validMove = this.movementMask[3 + nbCases][2 - nbCases];
			break;	
		case Piece.WEST:
			validMove = this.movementMask[3][2 - nbCases];
			break;	
		case Piece.NORTHWEST:
			validMove = this.movementMask[3 - nbCases][2 - nbCases];
			break;		
		}
	};
	public void shoot(int direction){};
	
	public abstract Piece copy();
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public abstract String toString();
	
	
	
}
