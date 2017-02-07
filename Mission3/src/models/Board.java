package models;

import java.util.ArrayList;

import models.pieces.Piece;

public class Board {
	private int sizeX;
	private int sizeY;
	
	private Piece[][] content;
	
	private ArrayList<ArrayList<int[]>> history;

	public Board() {
		this.sizeX = 0;
		this.sizeY = 0;
		this.content = new Piece[sizeY][sizeX];
		this.history = new ArrayList<ArrayList<int[]>>();
	}
	public Board(int sizeX, int sizeY) {
		this();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.content = new Piece[sizeY][sizeX];
	}
	public Board(char[][] contentMap) {
		this(contentMap[0].length, contentMap.length);
	}
	public Board(Board b){
		this(b.content[0].length, b.content.length);

		for (int i = 0; i < this.content.length; i++) {
			for (int j = 0; j < this.content[i].length; j++) {
				this.content[i][j] = b.content[i][j] != null ? b.content[i][j].copy() : null;
			}
		}
		for (ArrayList<int[]> move : b.history) {
			ArrayList<int[]> copy = new ArrayList<int[]>();
			for (int[] is : move) {
				copy.add( new int[]{is[0], is[1]});
			}
			this.history.add(copy);
		}
	}
	
	public Board copy(){
		return new Board(this);
	}
	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public Piece getContent(int x, int y) {
		return content[y][x];
	}

	public void setContent(int x, int y, Piece p) {
		this.content[y][x] = p;
	}
	
	public String toString(){
		String display = "";
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				display += content[i][j] != null ? content[i][j] : "__";
				display += " ";
			}
			display += "\n";
		}
		display += this.displayHistory(1) + "\n";
		return display;
	}

	public boolean validateMove(ArrayList<int[]> positions){
		boolean validMove = true;
		int size = positions.size();
		for (int i = 1; i < size; i++) {
			int[] move = positions.get(i);
			if( 	!(move[0] >= 0 && move[0] < this.sizeX)
				||	!(move[1] >= 0 && move[1] < this.sizeY)
				|| 	this.get(move[0], move[1]) != null){
				validMove = false;
				break;
			}
		}
		return validMove;
	}
	public void move(ArrayList<int[]> positions){
		int size = positions.size();
			int[] endPosition = positions.get(size - 1);
			int[] startPosition = positions.get(0);
			Piece p = this.get(startPosition[0], startPosition[1]);
			this.set(endPosition[0], endPosition[1], p);
			this.set(startPosition[0], startPosition[1], null);
			p.move(endPosition[0], endPosition[1]);
			
			ArrayList<int[]> registeredMove = new ArrayList<int[]>();
			registeredMove.add(positions.get(0));
			registeredMove.add(positions.get(size - 1));
			this.history.add(0, registeredMove);
	}
	public boolean validateShot(ArrayList<int[]> shotRange){
		boolean validShot = false;
		int size = shotRange.size();
		if(size == 0){
			return true;
		}
		int[] firstShotCell = shotRange.get(0);
		if(		(firstShotCell[0] >= 0 && firstShotCell[0] < this.sizeX)
			&& 	(firstShotCell[1] >= 0 && firstShotCell[1] < this.sizeY)){
			validShot = true;
		}
		return validShot;
	}
	public void shoot(ArrayList<int[]> shotRange){
		int size = shotRange.size();
		for (int i = 0; i < size; i++) {
			int[] shot = shotRange.get(i);
			if(	(shot[0] >= 0 && shot[0] < this.sizeX)
			&& 	(shot[1] >= 0 && shot[1] < this.sizeY)){
				this.set(shot[0], shot[1], null);
				this.history.get(0).add(shot);
			}
		}
	}

	public Piece get(int x, int y){
		return this.content[y][x];
	}
	public void set(int x, int y, Piece p){
		this.content[y][x] = p;
	}
	
	public String displayHistory(int steps){
		String display = "";
		if(history.size() == 0){
			return "Init";
		}else{
			for (int i = 0; i < steps; i++) {
				int[] from = this.history.get(i).get(0);
				int[] to = this.history.get(i).get(1);
				display += from[0] + "-" + from[1];
				display += " -> ";
				display += to[0] + "-" + to[1];
				if(this.history.get(i).size() > 2){
					display += " x";
					for (int j = 2; j < this.history.get(i).size(); j++) {
						int[] shot = this.history.get(i).get(j);
						display += " "+ shot[0] + "-" + shot[1];
					}
				}
			}
		}
		
		return display;
	}
	
	public ArrayList<int[]> getHistory(int i){
		if(this.history.size()==0){
			return new ArrayList<int[]>();
		}
		return this.history.get(i);
	}

}
