package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import models.Piece;

public class State {
	private String[][] map;
	private int attacker;
	private State bestChild;
	private State worstChild;
	private String previousAction;
	private int min, max, points;
	private double score;
	private boolean EOG;

	
	public State(){
		this.map = null;
		this.attacker = 0;
		this.bestChild = null;
		this.worstChild = null;
		this.previousAction = "Init";
		this.EOG = false;
		this.score = 0;
	}
	public State(char[][] content){
		this();
		this.placePieces(content);
		this.calcPoints();
	}
	
	public String toString(){
		String display = "";
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				display += this.map[i][j] != "" ? this.map[i][j] : "__";
				display += " ";
			}
			display += "\n";
		}
		display += this.previousAction + "\nPOINTS:" + this.points + "SCORE:" + this.score + " MIN:"+  this.min + " MAX:" + this.max + "\n";
		return display;
	}
	
	private void placePieces(char[][] contentMap){
		this.map = new String[contentMap.length][contentMap[0].length];
		for (int i = 0; i < contentMap.length; i++) {
			for (int j = 0; j < contentMap[i].length; j++) {
				char pieceType = contentMap[i][j];
				int x1 = j, y1 = i,
					x2 = contentMap[0].length - (j + 1), y2 = contentMap.length - (i + 1);
				
				map[y1][x1] = pieceType == ' ' ? "" : Character.toString(pieceType) + "0";
				map[y2][x2] = pieceType == ' ' ? "" : Character.toString(pieceType) + "1";
				
			}
		}
	}
	
	private State copy(){
		State copy = new State();
		String[][] mapCopy = new String[this.map[0].length][this.map.length];
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				mapCopy[i][j] = this.map[i][j];
			}
		}
		copy.map = mapCopy;
		copy.attacker = this.attacker;
		copy.previousAction = this.previousAction;
		
		copy.points = this.points;
		copy.EOG = this.EOG;
		
		return copy;
	}
	
	public ArrayList<State> getNexts(){
		ArrayList<State> nexts = new ArrayList<State>();
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j]!="" && Integer.parseInt(map[i][j].substring(1, 2)) == attacker){
					Piece p = new Piece(map[i][j].substring(0, 1));
					ArrayList<int[]> validMoves = p.getValidMoves(j, i);
					
					for (Iterator iterator = validMoves.iterator(); iterator.hasNext();) {
						int[] move = (int[]) iterator.next();
						if( 		move[0] >= 0 && move[0] < this.map[0].length &&  move[1] >= 0 && move[1] < this.map.length //Doesn't go out of the map
								&&	(this.map[move[1]][move[0]] == "__" // Is empty
									|| (move[0] == j && move[1] == i))){// Doesn't move
							
							State moveState = this.copy();
							moveState.attacker = this.attacker == 0 ? 1 : 0;
							
							String temp = moveState.map[move[1]][move[0]];
							moveState.map[move[1]][move[0]] = moveState.map[i][j];
							moveState.map[i][j] = temp;
							moveState.previousAction += "\n"+moveState.map[move[1]][move[0]] + ": " + i + "-" + j + " -> " + move[0] + "-" + move[1];
							nexts.add(moveState);

							ArrayList<int[]> validShots = p.getValidShotDirections();
							for (Iterator iteratorShot = validShots.iterator(); iteratorShot.hasNext();) {
								int[] shot = (int[]) iteratorShot.next();
								int x = 0, y = 0;
								String direction = null;
								switch(shot[0]){
									case Piece.NORTHWEST:
										x = -1;
										y = -1;
										direction = "^<";
										break;
									case Piece.NORTH:
										x = 0;
										y = -1;
										direction = "^";
										break;
									case Piece.NORTHEAST:
										x = 1;
										y = -1;
										direction = "^>";
										break;
									case Piece.EAST:
										x = 1;
										y = 0;
										direction = ">";
										break;
									case Piece.SOUTHEAST:
										x = 1;
										y = 1;
										direction = "v>";
										break;
									case Piece.SOUTH:
										x = 0;
										y = 1;
										direction = "v";;
										break;
									case Piece.SOUTHWEST:
										x = -1;
										y = 1;
										direction = "v<";;
										break;
									case Piece.WEST:
										x = -1;
										y = 0;
										direction = "<";
										break;
								}
								State shotState = moveState.copy();
								boolean addedState = false;
								for (int k = 1; k <= shot[1]; k++) {
									if( 		move[1] + y * k >= 0 && move[1] + y * k < this.map[0].length
											&&  move[0] + x * k >= 0 && move[0] + x * k < this.map[0].length){
										shotState.map[move[1] + y * k][move[0] + x * k] = "";
										calcPoints();
										addedState = true;
									}
								}
								if(addedState){
									shotState.previousAction += " x" + direction;
									if(!moveState.equals(shotState)){
										shotState.calcPoints();
										nexts.add(shotState);
										if(!(this.max > shotState.points)){
											this.bestChild = shotState;
										}
										if(!(this.min < shotState.points)){
											this.worstChild = shotState;
										}
										this.max = this.max > shotState.points ? this.max : shotState.points;
										this.min = this.min < shotState.points ? this.min : shotState.points;
										this.score += shotState.points;
									}
								}
							}	
						}
					}
				}
			}
		}
		if(nexts.size()!=0){
			this.score /= nexts.size();
		}else{
			this.score = attacker == 0 ? -10 : 10;
		}
		return nexts;
	}
	
	public boolean equals(State s){
		
		if(this.attacker != s.attacker){
			return false;
		}else
		{
			
			for (int i = 0; i < this.map.length; i++) {
				for (int j = 0; j < this.map[0].length; j++) {
					if(!this.map[i][j].equals(s.map[i][j])){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void calcPoints(){
		int points = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				if(map[i][j]!="" && Integer.parseInt(map[i][j].substring(1, 2)) == 0){
					points++;
				}else if(map[i][j]!="" && Integer.parseInt(map[i][j].substring(1, 2)) == 1){
					points--;
				}
			}
		}
		this.points = points;
	}
	public void updateScore(){
		if(this.bestChild != null){
			this.max = this.bestChild.max;
		}
		if(this.worstChild != null){
			this.min = this.worstChild.min;
		}
	}
}
