package com.zombiecalypse.downloadsorter.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zombiecalypse.downloadsorter.DownloadSorter;
import com.zombiecalypse.downloadsorter.IFileSystem;

public class GeneralRuleSorting {

	protected DownloadSorter sorter;
	protected FauxFileSystem fs;

	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new TrivialTestingModule());
		this.sorter = injector.getInstance(DownloadSorter.class);
		this.fs = (FauxFileSystem) injector.getInstance(IFileSystem.class);
	}

	@Test
	public void shouldCopyBareFile() {
		sorter.moveToLibrary("myfile");
		assertThat(fs.get("~/Downloads/myfile"), is("myfile"));
	}
	
	@Test
	public void shouldCopyBareFolder() {
		sorter.moveToLibrary("myfolder/");
		assertThat(fs.get("~/Downloads/myfolder"), is("myfolder/"));
	}
	
	@Test
	public void shouldCopyTwo() {
		sorter.moveToLibrary("myfile");
		sorter.moveToLibrary("myfolder/");
		assertThat(fs.get("~/Downloads/myfolder"), is("myfolder/"));
		assertThat(fs.get("~/Downloads/myfile"), is("myfile"));
	}
	
	@Test
	public void shouldCopyAbsPath() {
		sorter.moveToLibrary("/var/downloads/myfolder/");
		sorter.moveToLibrary("/var/downloads/myfile");
		assertThat(fs.get("~/Downloads/myfolder"), is("/var/downloads/myfolder/"));
		assertThat(fs.get("~/Downloads/myfile"), is("/var/downloads/myfile"));
	}

}
