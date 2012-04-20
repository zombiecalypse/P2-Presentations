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
import com.zombiecalypse.downloadsorter.rules.PdfRule;
import com.zombiecalypse.downloadsorter.rules.Rule;
import com.zombiecalypse.downloadsorter.rules.TrivialRule;

public class PdfRuleSorting extends GeneralRuleSorting {
	static class PdfRuleModule extends TestModule {
		
		
		public void configure() {
			super.configure();
			bind(new TypeLiteral<Collection<Rule>>(){})
				.annotatedWith(Names.named("rules"))
				.toProvider(PdfProvider.class);
		}
		
		static class PdfProvider implements Provider<Collection<Rule>> {
			private final Provider<TrivialRule> trivialRuleProvider;
			private final Provider<PdfRule> pdfRuleProvider;
			
			@Inject
			public PdfProvider(Provider<TrivialRule> trivialRuleProvider,
					Provider<PdfRule> pdfRuleProvider) {
				super();
				this.trivialRuleProvider = trivialRuleProvider;
				this.pdfRuleProvider = pdfRuleProvider;
			}
			
			public Collection<Rule> get() {
				return Arrays.asList(
						trivialRuleProvider.get(),
						pdfRuleProvider.get());
			}
		}

	}


	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new PdfRuleModule());
		this.sorter = injector.getInstance(DownloadSorter.class);
		this.fs = injector.getInstance(IFileSystem.class);
	}

	@Test
	public void shouldSortPdfToTextFolder() {
		this.sorter.moveToLibrary("peirce.pdf");
		verify(fs).move("~/Downloads/peirce.pdf", "~/Texts/peirce.pdf");
	}
	
	@Test
	public void shouldSortPdfToTextFolderButNotJpg() {
		when(fs.list("~/Downloads/")).thenReturn(Arrays.asList(
						"peirce.pdf",
						"longcat.jpg"));
		this.sorter.cleanup();
		verify(fs).list("~/Downloads/");
		verify(fs).move("~/Downloads/peirce.pdf", "~/Texts/peirce.pdf");
		verifyNoMoreInteractions(fs);
	}
}
