package models;

public class Board {
	private int sizeX;
	private int sizeY;
	
	private Piece[][] content;

	public Board() {
		this.sizeX = 0;
		this.sizeY = 0;
		this.content = new Piece[sizeY][sizeX];
	}
	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.content = new Piece[sizeY][sizeX];
	}
	public Board(char[][] contentMap) {
		this.sizeX = contentMap[0].length;
		this.sizeY = contentMap.length;
		this.content = new Piece[sizeY][sizeX];
	}

	public Board copy(){
		Board copy = new Board();
		copy.sizeX = this.sizeX;
		copy.sizeY = this.sizeY;
		copy.content = new Piece[this.sizeY][this.sizeX];
		for (int i = 0; i < this.content.length; i++) {
			for (int j = 0; j < this.content[i].length; j++) {
				copy.content[i][j] = this.content[i][j].copy();
			}
		}
		return copy;
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
		return display;
	}
	

}
