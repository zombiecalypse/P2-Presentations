package com.zombiecalypse.downloadsorter.tests;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Provider;

import com.google.inject.TypeLiteral;
import com.zombiecalypse.downloadsorter.RulesInjection;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.TrivialRule;

public class TrivialTestingModule extends TestModule {

	@Override
	protected void configure() {
		super.configure();
		bind(new TypeLiteral<Collection<Rule>>(){})
				.annotatedWith(RulesInjection.class)
				.toProvider(TrivialProvider.class);
	}
	
	static class TrivialProvider implements Provider<Collection<Rule>> {
		public Collection<Rule> get() {
			return Arrays.asList((Rule)new TrivialRule());
		}
	}

}
