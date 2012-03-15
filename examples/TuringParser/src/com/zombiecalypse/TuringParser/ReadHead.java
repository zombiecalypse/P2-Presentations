package com.zombiecalypse.TuringParser;

public class ReadHead {
	public State state;
	public TapeChar tapechar;
	public ReadHead(String state, String read) {
		this.state = new State(state);
		this.tapechar = new TapeChar(read);
	}
	
	public String toString() {
		return "R("+state+", "+tapechar+")";
	}
}