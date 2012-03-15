package com.zombiecalypse.TuringParser;

public class TapeChar {
	public String name;
	public TapeChar(String s) {
		this.name = s;
	}
	
	public String toString() {
		return "T("+name+")";
	}
}