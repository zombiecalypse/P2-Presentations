package com.zombiecalypse.downloadsorter;

public class FileSystem implements IFileSystem {

	@Override
	public void move(String from, String to) {
		System.out.println(from + " => " + to);
	}

}
