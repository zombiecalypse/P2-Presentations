package com.zombiecalypse.downloadsorter.tests;

import java.util.logging.LogManager;

import com.google.inject.AbstractModule;
import com.zombiecalypse.downloadsorter.IFileSystem;

public abstract class TestModule extends AbstractModule {
	private void shutUpLogs() {
		LogManager.getLogManager().reset();
	}

	@Override
	protected void configure() {
		shutUpLogs();
		bind(IFileSystem.class).to(FauxFileSystem.class).asEagerSingleton();
	}

}
