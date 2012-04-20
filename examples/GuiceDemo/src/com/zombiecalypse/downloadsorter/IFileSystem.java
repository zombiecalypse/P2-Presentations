package com.zombiecalypse.downloadsorter;

import java.util.List;

public interface IFileSystem {
	void move(String from, String to);
	List<String> list(String path);
}
