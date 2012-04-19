package com.zombiecalypse.downloadsorter.rules;

import java.io.File;

public class PdfRule extends Rule {

	@Override
	public boolean matches(String path) {
		return path.matches(".*\\.pdf");
	}

	@Override
	public String output(String path) {
		File filepath = new File(path);
		return "~/Texts/"+filepath.getName();
	}
	
	public Integer priority() {
		return 10;
	}

}
