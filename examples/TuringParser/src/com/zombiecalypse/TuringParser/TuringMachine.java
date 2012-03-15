package com.zombiecalypse.TuringParser;

import java.util.Map;
import java.util.Set;

public class TuringMachine {
	private State startState;
	private Map<ReadHead, Output> transitions;
	private Set<State> endstates;

	public TuringMachine(State startState, Map<ReadHead, Output> transitions, Set<State> endstates) {
		this.startState = startState;
		this.transitions = transitions;
		this.endstates = endstates;
	}


	public void run() {
		// ...
	}
	
	public String toString() {
		return "TM<"+startState+", "+ transitions + ", " + endstates+">";
	}
}
