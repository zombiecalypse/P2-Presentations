package com.zombiecalypse.downloadsorter.rules;

import java.util.Arrays;
import java.util.Collection;

import com.google.inject.Provider;

public class Rules implements Provider<Collection<Rule>> {
	public Collection<Rule> get() {
		return Arrays.asList(
				new TrivialRule(),
				new PdfRule()
				);
	}

}
