package com.zombiecalypse.downloadsorter;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.zombiecalypse.downloadsorter.rules.Rule;

public class DownloadSorter {
	private static final Comparator<Rule> byPriority = new Comparator<Rule> () {
		public int compare(Rule arg0, Rule arg1) {
			return arg1.priority().compareTo(arg0.priority());
		}};
	
	private IFileSystem fs;
	private Logger logger;
	private SortedSet<Rule> rules;
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new WorkModule());
		DownloadSorter sorter = injector.getInstance(DownloadSorter.class);
		
		String[] paths = {"a.txt", "b.pdf"};
		for (String folder : paths) {
			sorter.moveToLibrary(folder);
		}
	}
	
	@Inject
	public DownloadSorter(IFileSystem fs, Logger logger, @RulesInjection Collection<Rule> rules) {
		this.fs = fs;
		this.logger = logger;
		this.rules = new TreeSet<Rule>(byPriority);
		this.rules.addAll(rules);
	}

	public void moveToLibrary(String path) {
		String outputPath = findMatching(path);
		
		if (outputPath == null) {
			logger.warning("No rule for " + path + "found.");
			return;
		}
		
		logger.info("Moving "+path+" => " + outputPath);
		fs.move(path, outputPath);
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
