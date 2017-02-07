package models;

import java.util.ArrayList;

import algorithms.State;
import models.pieces.Infantry;
import models.pieces.Piece;
import models.pieces.Rook;
import models.pieces.Tank;
import models.players.AI;
import models.players.Bot;
import models.players.Player;

public class Game {
	private Board board;
	private Player[] players;
	private int attacker;
	
	public Game() {
		this.board = new Board();
		this.players = new Player[2];
		this.players[0] = new Player();
		this.players[1] = new Player();
		this.attacker = 0;
		this.players[0].setGame(this);
		this.players[1].setGame(this);
	}
	public Game(Player p1, Player p2){
		this();
		this.players[0] = p1;
		this.players[1] = p2;
		this.players[0].setGame(this);
		this.players[1].setGame(this);
	}
	public Game(String[][] contentMap) {
		this();
		this.placePieces(contentMap);
	}
	public Game(Player p1, Player p2, String[][] contentMap){
		this(p1, p2);
		this.placePieces(contentMap);
	}
	
	public Game(State s){
		this(s.getContentMap());
		this.attacker = s.getAttacker();
	}
	
	public Game copy() {
		Game copy = new Game();
		copy.board = this.board.copy();
		copy.attacker = this.attacker;
		return copy;
	}

	public Board getBoard() {
		return board;
	}
	public int getAttacker() {
		return attacker;
	}
	public Player getPlayer(int i){
		Player p = null;
		if(i<this.players.length){
			p = this.players[i];
		}
		return p;
	}
	private void placePieces(String[][] contentMap) {
		this.board = new Board(contentMap[0].length, contentMap.length);
		for (int j = 0; j < contentMap.length; j++) {
			for (int i = 0; i < contentMap[j].length; i++) {
				String pieceCode= contentMap[j][i];
				if (pieceCode == null) {
					continue;
				} else {
					int ownerIndex = Integer.parseInt(pieceCode.substring(1, 2));
					String pieceType = pieceCode.substring(0, 1);
					Player owner = this.players[ownerIndex];
					Piece piece = null;
					switch (pieceType) {
						case "R":
							piece = new Tank(i, j, ownerIndex);
							break;
						case "S":
							piece = new Rook(i, j, ownerIndex);
							break;
						case "L":
							piece = new Infantry(i, j, ownerIndex);
							break;
						}
						owner.placePiece(piece);
						this.board.setContent(i, j, piece);
				}
			}
		}
	}

	public void play() {
			System.out.println(this.board);
		while(true){
			this.players[this.attacker].play();
			
			int defender = (this.attacker == 0) ? 1 : 0;
			this.players[defender].updatePiecesList(this.board);
			
			System.out.println(this.board);
			
			if(this.players[defender].getNbPieces() == 0){
				break;
			}else{
				this.attacker = defender;
			}

		}
		System.out.println("Player " + this.attacker + " won");
	}
	
	public void test(){
		this.players[this.attacker].play();
	}
	
	public String[][] getContentMap(){
		String[][] contentMap = new String[this.board.getSizeY()][this.board.getSizeX()];
		String cellValue;
		for (int i = 0; i < contentMap.length; i++) {
			for (int j = 0; j < contentMap[0].length; j++) {
				cellValue = this.board.getContent(j, i) == null ? null : this.board.getContent(j, i).toString();
				contentMap[i][j] = cellValue;
			}
		}
		return contentMap;
	}
	
	public ArrayList<int[]> getHistory(int i){
		return this.board.getHistory(i);
	}
	

}
