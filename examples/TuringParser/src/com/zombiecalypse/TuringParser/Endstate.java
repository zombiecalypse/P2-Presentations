package com.zombiecalypse.TuringParser;

class Endstate extends Expression {
	String name;
	public Endstate(String arg0) {
		this.name = arg0;
	}
	@Override
	public void addTo(TuringMachineBuilder tmb) {
		tmb.endstates.add(new State(name));
	}
	
}