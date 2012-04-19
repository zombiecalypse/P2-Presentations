package com.zombiecalypse.downloadsorter.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zombiecalypse.downloadsorter.DownloadSorter;
import com.zombiecalypse.downloadsorter.IFileSystem;

public class PdfRuleSorting extends GeneralRuleSorting {

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new PdfRuleModule());
		this.sorter = injector.getInstance(DownloadSorter.class);
		this.fs = (FauxFileSystem) injector.getInstance(IFileSystem.class);
	}

	@Test
	public void shouldSortPdfToTextFolder() {
		this.sorter.moveToLibrary("/path/to/peirce.pdf");
		assertThat(fs.get("~/Texts/peirce.pdf"), is("/path/to/peirce.pdf"));
	}
}
