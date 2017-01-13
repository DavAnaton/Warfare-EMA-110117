package models;

import java.util.ArrayList;

public class State {
	private Game game;
	
	public State(){
		this.game = null;
	}
	public State(Game g){
		this.game = g;
	}
	
	public String toString(){
		String display = "";
		display = this.game.getBoard().toString();
		display += this.game.getAttacker() + "\n";
		return display;
	}
}
