package com.zombiecalypse.downloadsorter;

import static org.mockito.Mockito.mock;

import java.util.logging.LogManager;

import com.google.inject.AbstractModule;

public abstract class TestModule extends AbstractModule {
	private void shutUpLogs() {
		LogManager.getLogManager().reset();
	}

	@Override
	protected void configure() {
		install(new ConstantsModule());
		shutUpLogs();
		bind(IFileSystem.class).toInstance(mock(IFileSystem.class));
	}

}
