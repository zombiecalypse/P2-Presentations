package com.zombiecalypse.TuringParser;

class StartState extends Expression {
	String name;

	public StartState(String arg0) {
		this.name = arg0;
	}

	@Override
	public void addTo(TuringMachineBuilder tmb) {
		tmb.startstate = new State(name);
	}
}