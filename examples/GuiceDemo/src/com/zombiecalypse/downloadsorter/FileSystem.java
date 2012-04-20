package com.zombiecalypse.downloadsorter;

import java.util.Arrays;
import java.util.List;

public class FileSystem implements IFileSystem {

	@Override
	public void move(String from, String to) {
		System.out.println(from + " => " + to);
		// ...
	}

	@Override
	public List<String> list(String path) {
		return Arrays.asList("a.txt", "b.pdf"); // All my folder contain only these two files.
	}

}
