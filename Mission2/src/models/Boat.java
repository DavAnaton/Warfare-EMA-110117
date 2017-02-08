package models;

public class Boat {
	// Attributes
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	// Constructors
	public Boat(){
		this.name = "";
		this.time = 0;
	}
	public Boat(String n, int t){
		this.name = n;
		this.time = t;
	}

	// Display
	public String toString(){
		return this.name + ": " + this.time/60 + "h" + this.time%60;
	}
	
}
