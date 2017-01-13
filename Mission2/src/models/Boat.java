package models;

public class Boat {
	// Attributes
	private String name;
	private int time;
	
	// Constructors
	public Boat(){
		this.name = "";
		this.time = 0;
	}
	
	public Boat(String n, int t){
		this.name = n;
		this.time = t;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	// Display
	public String toString(){
		return this.name + ": " + this.time/60 + "h" + this.time%60;
	}
	
}
