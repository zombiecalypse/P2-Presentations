package com.zombiecalypse.downloadsorter;

import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.Rules;


public class WorkModule extends AbstractModule {

	@Override
	public void configure() {
		bind(IFileSystem.class).to(FileSystem.class).asEagerSingleton();
		bind(new TypeLiteral<Collection<Rule>>(){})
				.annotatedWith(RulesInjection.class)
				.toProvider(Rules.class);
	}

}
