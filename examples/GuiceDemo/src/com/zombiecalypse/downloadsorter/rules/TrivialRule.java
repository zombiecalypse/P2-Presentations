package com.zombiecalypse.downloadsorter.rules;

import java.io.File;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TrivialRule extends Rule {
	private final String downloadFolder; 
	
	@Inject
	public TrivialRule(@Named("downloadFolder") String downloadFolder) {
		super();
		this.downloadFolder = downloadFolder;
	}

	@Override
	public boolean matches(String path) {
		return true;
	}

	@Override
	public String output(String path) {
		File filepath = new File(path);
		return downloadFolder + filepath.getName();
	}

}
