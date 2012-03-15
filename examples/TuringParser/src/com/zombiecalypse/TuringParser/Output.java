package com.zombiecalypse.TuringParser;

public class Output {
	public State newState;
	public TapeChar writtenChar;
	public Movement movement;
	public Output(String newstate, String written, Movement movement) {
		this.newState = new State(newstate);
		this.writtenChar = new TapeChar(written);
		this.movement = movement;
	}
	public String toString() {
		return "R("+newState+", "+writtenChar+", "+movement+")";
	}
}