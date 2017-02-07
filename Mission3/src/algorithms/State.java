package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import models.Game;
import models.pieces.Piece;
import models.players.Player;

public class State {
	private String[][] contentMap;
	private int attacker;
	private ArrayList<int[]> lastMove;

	private float score;

	public State() {
		this.contentMap = new String[0][0];
		this.attacker = 0;
		this.lastMove = new ArrayList<int[]>();
	}

	public State(Game g) {
		this();
		this.contentMap = g.getContentMap();
		this.attacker = g.getAttacker();
		this.lastMove = g.getHistory(0);
	}

	public String toString() {
		String display = "";
		for (int i = 0; i < this.contentMap.length; i++) {
			for (int j = 0; j < this.contentMap[i].length; j++) {
				display += this.contentMap[i][j] != null ? this.contentMap[i][j] : "__";
				display += " ";
			}
			display += "\n";
		}
		display += lastMove() + "\nA:P" + this.attacker + " Score: " + this.score + "\n";
		return display;
	}

	public float calcScore() {
		float s = 0;
		int p0 = 0, p1 = 0;
		for (int i = 0; i < this.contentMap.length; i++) {
			for (int j = 0; j < this.contentMap[i].length; j++) {
				if (contentMap[i][j] != null) {
					int ownerIndex = Integer.parseInt(contentMap[i][j].substring(1, 2));
					if (ownerIndex == 0) {
						p0++;
					} else {
						p1++;
					}
				}
			}
		}
		if(p0 == 0){
			s = - Float.MAX_VALUE;
		}else if(p1 == 0){
			s = Float.MAX_VALUE;
		}else{
			s = p0 - p1;
		}
			
		s += 0.1;
		this.score = s;
		return this.score;
	}

	public void setScore(float s) {
		this.score = s;
	}

	public float getScore() {
		return this.score;
	}

	public ArrayList<State> getNexts() {
		ArrayList<State> nexts = new ArrayList<State>();

		Game g = new Game(this);

		ArrayList<Piece> p = g.getPlayer(this.attacker).getPieces();

		for (Piece piece : p) {
			ArrayList<ArrayList<int[]>> validMoves = piece.getMoves();
			for (ArrayList<int[]> move : validMoves) {
				g = new Game(this);
				if (g.getBoard().validateMove(move)) {
					g.getBoard().move(move);
					nexts.add(new State(g));
					Piece shooting = g.getBoard().get(move.get(move.size() - 1)[0], move.get(move.size() - 1)[1]);
					ArrayList<ArrayList<int[]>> validShots = shooting.getShots();

					for (ArrayList<int[]> shot : validShots) {
						if (g.getBoard().validateShot(shot) && shot.size() > 0) {
							Game copy = g.copy();
							copy.getBoard().shoot(shot);
							nexts.add(new State(copy));
						}
					}
				}
			}
		}
		int newAttacker = this.attacker == 0 ? 1 : 0;
		for (State s : nexts) {
			s.attacker = newAttacker;
		}

		return nexts;
	}

	public String[][] getContentMap() {
		return this.contentMap;
	}

	public int getAttacker() {
		return this.attacker;
	}

	private String lastMove() {
		String display = "";
		if (this.lastMove.size() == 0) {
			return "Init";
		} else {
			int[] from = this.lastMove.get(0);
			int[] to = this.lastMove.get(1);
			display += from[0] + "-" + from[1];
			display += " -> ";
			display += to[0] + "-" + to[1];
			if (this.lastMove.size() > 2) {
				display += " x";
				for (int j = 2; j < this.lastMove.size(); j++) {
					int[] shot = this.lastMove.get(j);
					display += " " + shot[0] + "-" + shot[1];
				}
			}
		}

		return display;
	}

	public ArrayList<int[]> getLastMove() {
		return this.lastMove;
	}
}
