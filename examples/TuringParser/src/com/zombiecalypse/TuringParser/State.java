package com.zombiecalypse.TuringParser;

public class State {
	public String name;
	public State(String s) {
		this.name = s;
	}
	
	public String toString() {
		return "S("+name+")";
	}
}