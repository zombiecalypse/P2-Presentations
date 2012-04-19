package com.zombiecalypse.downloadsorter.tests;

import java.util.HashMap;
import java.util.Map;

import com.zombiecalypse.downloadsorter.IFileSystem;

public class FauxFileSystem implements IFileSystem {
	
	private Map<String, String> files;

	public FauxFileSystem() {
		this.files = new HashMap<String, String>();
	}
	
	public String get(String from) {
		return files.get(from);
	}

	@Override
	public void move(String from, String to) {
		files.put(to, from);
	}

}
