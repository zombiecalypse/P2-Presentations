package com.zombiecalypse.downloadsorter.tests;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Provider;

import com.google.inject.TypeLiteral;
import com.zombiecalypse.downloadsorter.RulesInjection;
import com.zombiecalypse.downloadsorter.rules.PdfRule;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.TrivialRule;

public class PdfRuleModule extends TestModule {
	public void configure() {
		super.configure();
		bind(new TypeLiteral<Collection<Rule>>(){})
			.annotatedWith(RulesInjection.class)
			.toProvider(PdfProvider.class);
	}
	
	static class PdfProvider implements Provider<Collection<Rule>> {
		public Collection<Rule> get() {
			return Arrays.asList(
					new TrivialRule(),
					new PdfRule());
		}
	}

}
