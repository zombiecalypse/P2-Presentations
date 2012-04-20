package com.zombiecalypse.downloadsorter;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConstantsModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("downloadFolder")).toInstance("~/Downloads/");
	}

}
