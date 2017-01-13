package models;

public class Game {
	private Board board;
	private Joueur[] joueurs;
	private int attacker;
	
	public Game() {
		this.board = new Board();
		this.joueurs = new Joueur[2];
		this.joueurs[0] = new Joueur();
		this.joueurs[1] = new Joueur();
		this.attacker = 0;
	}
	public Game(int sizeX, int sizeY) {
		this.board = new Board(sizeX, sizeY);
		this.joueurs = new Joueur[2];
		this.joueurs[0] = new Joueur();
		this.joueurs[1] = new Joueur();
		this.attacker = 0;
	}
	public Game(char[][] contentMap) {
		this.board = new Board(contentMap);
		this.joueurs = new Joueur[2];
		this.joueurs[0] = new Joueur();
		this.joueurs[1] = new Joueur();
		this.attacker = 0;
		placePieces(contentMap);
	}

	public void start(){
		System.out.println(this.board);
	}
	
	public Game copy(){
		Game copy = new Game();
		copy.board = this.board.copy();
		copy.attacker = this.attacker;
		return copy;
	}
	
	public void placePieces(char[][] contentMap){
		for (int i = 0; i < contentMap.length; i++) {
			for (int j = 0; j < contentMap[i].length; j++) {
				char pieceType = contentMap[i][j];
				int x1 = j, y1 = i,
					x2 = this.board.getSizeX() - (j + 1), y2 = this.board.getSizeY() - (i + 1);
				Piece piece0 = null, piece1 = null;
				switch (pieceType){
					case 'R':
						piece0 = new Tank(x1, y1, 0);
						piece1 = new Tank(x2, y2, 1);
					case 'S':
						piece0 = new Rook(x1, y1, 0);
						piece1 = new Rook(x2, y2, 1);
					case 'L':
						piece0 = new Infantry(x1, y1, 0);
						piece1 = new Infantry(x2, y2, 1);
				}
				joueurs[0].placePiece(piece0);
				board.setContent(x1, y1, piece0);
				joueurs[1].placePiece(piece1);
				board.setContent(x2, y2, piece1);
				
			}
		}
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
	public int getAttacker() {
		return attacker;
	}
	public void setAttacker(int attacker) {
		this.attacker = attacker;
	}
	

}
