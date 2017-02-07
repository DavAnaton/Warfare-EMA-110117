package models;

import java.util.ArrayList;

public class Piece {
	protected boolean [][] movementMask = null;
	protected int [][] shootingMask =  null;

	private final static boolean here = true;
	private final static boolean [][] RookMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true, 	here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	 private final static int [][] RookShootingMask = {	{2, 2, 2},
	 													{2, 0, 2},
	 													{2, 2, 2}};

	private final static boolean [][] TankMovementMask = {	{false, false, false, false, false},
															{false, false, false, false, false},
															{false,  true,  true,  true, false},
															{false,  true, 	here,  true, false},
															{false,  true,  true,  true, false},
															{false, false, false, false, false}};

	private final static int [][] TankShootingMask = {	{0, 2, 0},
														{2, 0, 2},
														{0, 0, 0}};

	private final static boolean [][] InfantryMovementMask = {	{false, false, false, false, false},
																{ true, false,  true, false,  true},
																{false,  true,  true,  true, false},
																{ true,  true, 	here,  true,  true},
																{false,  true,  true,  true, false},
																{ true, false,  true, false,  true}};

	private final static int [][] InfantryShootingMask = {	{0, 1, 0},
															{1, 0, 1},
															{0, 0, 0}};
	public final static int NORTHWEST = 0;
	public final static int NORTH = 1;
	public final static int NORTHEAST = 2;
	public final static int EAST = 5;
	public final static int SOUTHEAST = 8;
	public final static int SOUTH = 7;
	public final static int SOUTHWEST = 6;
	public final static int WEST = 3;
	
	public Piece(String s){
		switch (s){
			case "S":
				this.movementMask = RookMovementMask;
				this.shootingMask = RookShootingMask;
				break;
			case "R":
				this.movementMask = TankMovementMask;
				this.shootingMask = TankShootingMask;
				break;
			case "L":
				this.movementMask = InfantryMovementMask;
				this.shootingMask = InfantryShootingMask;
				break;
		}
	}

	public ArrayList<int[]> getValidMoves(int x, int y){
		ArrayList<int[]> validMoves = new ArrayList<int[]>();
		for (int i = 0; i < movementMask.length; i++) {
			for (int j = 0; j < movementMask[i].length; j++) {
				if(this.movementMask[i][j]){
					int[] move = {i - 3 + x, j - 2 + y};
					validMoves.add(move);
				}
			}
		}
		return validMoves;
		
	}
	public ArrayList<int[]> getValidShotDirections(){
		ArrayList<int[]> validShots = new ArrayList<int[]>();
		for (int i = 0; i < this.shootingMask.length; i++) {
			for (int j = 0; j < this.shootingMask[i].length; j++) {
				if(this.shootingMask[i][j] != 0){
					int[] shot = {i* 3 + j, this.shootingMask[i][j]};
					validShots.add(shot);
				}
			}
		}
		return validShots;
		
	}
}
