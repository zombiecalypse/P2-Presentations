package com.zombiecalypse.TuringParser;

class Transition extends Expression {
	ReadHead readhead;
	Output output;
	
	public Transition(ReadHead r, Output o) {
		this.readhead = r;
		this.output = o;
	}
	
	@Override
	public void addTo(TuringMachineBuilder tmb) {
		tmb.transitions.put(readhead, output);
	}
}