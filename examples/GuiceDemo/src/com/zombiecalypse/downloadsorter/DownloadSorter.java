package com.zombiecalypse.downloadsorter;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.zombiecalypse.downloadsorter.rules.Rule;

public class DownloadSorter {
	private static final Comparator<Rule> byPriority = new Comparator<Rule> () {
		public int compare(Rule arg0, Rule arg1) {
			return arg1.priority().compareTo(arg0.priority());
		}};
	
	private final IFileSystem fs;
	private final Logger logger;
	private final SortedSet<Rule> rules;
	private final String downloadFolder;
	
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new WorkModule());
		DownloadSorter sorter = injector.getInstance(DownloadSorter.class);
		
		sorter.cleanup();
	}
	
	@Inject
	public DownloadSorter(IFileSystem fs, Logger logger, 
			@Named("rules") Collection<Rule> rules, 
			@Named("downloadFolder") String downloadFolder) {
		this.fs = fs;
		this.logger = logger;
		this.rules = new TreeSet<Rule>(byPriority);
		this.rules.addAll(rules);
		this.downloadFolder = downloadFolder;
	}

	/**
	 * Moves to specialized Library or possibly leave file in place.
	 * @param path of file to be moved to library relative to download folder
	 */
	void moveToLibrary(String path) {
		String outputPath = findMatching(path);
		
		String fullpath = (downloadFolder+path);

		if (fullpath.equals(outputPath)) return;
		
		if (outputPath == null) {
			logger.warning("No rule for " + path + "found.");
			return;
		}
		
		logger.info("Moving "+path+" => " + outputPath);
		fs.move(fullpath, outputPath);
	}
	
	public void cleanup() {
		for (String path : fs.list(downloadFolder)) {
			moveToLibrary(path);
		}
	}

	/**
	 * Gives the output for the first matching rule or null if no rule matches.
	 */
	private String findMatching(String folder) {
		for (Rule rule : rules) {
			if (rule.matches(folder)) {
				return rule.output(folder);
			}
		}
		return null;
	}
}
