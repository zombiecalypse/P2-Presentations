package com.zombiecalypse.downloadsorter;

import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.Rules;


public class WorkModule extends AbstractModule {

	@Override
	public void configure() {
		install(new ConstantsModule());
		
		bind(IFileSystem.class).to(FileSystem.class).asEagerSingleton();
		bind(new TypeLiteral<Collection<Rule>>(){})
				.annotatedWith(Names.named("rules"))
				.toProvider(Rules.class);
	}

}
