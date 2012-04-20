package com.zombiecalypse.downloadsorter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Provider;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.TrivialRule;

public class GeneralRuleSorting {
	public static class TrivialTestingModule extends TestModule {

		@Override
		protected void configure() {
			super.configure();
			bind(new TypeLiteral<Collection<Rule>>(){})
				.annotatedWith(Names.named("rules"))
				.toProvider(TrivialProvider.class);
		}
		
		static class TrivialProvider implements Provider<Collection<Rule>> {
			private final Provider<TrivialRule> trivialRuleProvider;
			
			@Inject
			public TrivialProvider(Provider<TrivialRule> trivialRuleProvider) {
				this.trivialRuleProvider = trivialRuleProvider;
			}
			public Collection<Rule> get() {
				return Arrays.asList((Rule)trivialRuleProvider.get());
			}
		}

	}


	protected DownloadSorter sorter;
	protected IFileSystem fs;

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new TrivialTestingModule());
		this.sorter = injector.getInstance(DownloadSorter.class);
		this.fs = injector.getInstance(IFileSystem.class);
	}

	@Test
	public void shouldNotCopyBareFile() {
		sorter.moveToLibrary("myfile");
		verifyNoMoreInteractions(fs);
	}
	
	@Test
	public void shouldNotCopyAnyOfTwo() {
		sorter.moveToLibrary("myfile");
		sorter.moveToLibrary("myfolder");
		verifyNoMoreInteractions(fs);
	}
	
	@Test
	public void shouldCopyAbsolutePath() {
		when(fs.list("~/Downloads/")).thenReturn(Arrays.asList(
				"myfile",
				"myfolder"
				));
		sorter.cleanup();
		verify(fs).list("~/Downloads/");
		verifyNoMoreInteractions(fs);
	}

}
