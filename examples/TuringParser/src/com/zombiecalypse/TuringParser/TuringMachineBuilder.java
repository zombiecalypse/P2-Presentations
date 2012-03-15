package com.zombiecalypse.TuringParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TuringMachineBuilder {
	Set<State> endstates = new HashSet<State>();
	Map<ReadHead, Output> transitions = new HashMap<ReadHead, Output>();
	State startstate;
	
	public TuringMachine generate() {
		return new TuringMachine(startstate, transitions, endstates);
	}
}
