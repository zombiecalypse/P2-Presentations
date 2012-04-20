package com.zombiecalypse.downloadsorter.rules;

import java.util.Arrays;
import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class Rules implements Provider<Collection<Rule>> {
	private final Provider<TrivialRule> trivialRuleProvider;
	private final Provider<PdfRule> pdfRuleProvider;
	
	@Inject
	public Rules(Provider<TrivialRule> trivialRuleProvider,
			Provider<PdfRule> pdfRuleProvider) {
		super();
		this.trivialRuleProvider = trivialRuleProvider;
		this.pdfRuleProvider = pdfRuleProvider;
	}



	public Collection<Rule> get() {
		return Arrays.asList(
				trivialRuleProvider.get(),
				pdfRuleProvider.get()
				);
	}

}
