package com.zombiecalypse.downloadsorter.rules;

import java.io.File;

public class TrivialRule extends Rule {

	@Override
	public boolean matches(String path) {
		return true;
	}

	@Override
	public String output(String path) {
		File filepath = new File(path);
		return "~/Downloads/" + filepath.getName();
	}

}
