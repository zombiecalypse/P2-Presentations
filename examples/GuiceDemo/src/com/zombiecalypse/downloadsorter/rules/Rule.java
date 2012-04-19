package com.zombiecalypse.downloadsorter.rules;

public abstract class Rule {
	public abstract boolean matches(String path);

	public abstract String output(String path);
	
	/**
	 * Importance of rule. Higher is more important. Is functional.
	 * @return constant non-negative integer
	 */
	public Integer priority() {
		return 0;
	}

}
