package com.zombiecalypse.TuringParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.functors.Pair;
import org.codehaus.jparsec.functors.Tuple3;
import org.codehaus.jparsec.functors.Tuple5;



public class TuringParser {
	static String example = ">a\n" +
			":a\n" +
			":b\n" +
			"a t => b t L";
	static String newline = System.getProperty("line.separator");
	
	public static void main(String[] args) throws FileNotFoundException {
		Parser<TuringMachine> parser = generate();
		
		// if (args.length == 0) throw new RuntimeException("Too few arguments");
		
		//String readFile = readFile(args[0]);
		TuringMachine tm = parser.parse(example);
		tm.run();
		System.out.println(tm);
	}

	private static String readFile(String filename) throws FileNotFoundException {
		StringBuilder text = new StringBuilder();
		Scanner scanner = new Scanner(new FileInputStream(filename), "UTF-8");
		try {
			while (scanner.hasNextLine()) {
				text.append(scanner.nextLine() + newline);
			}
		}
		finally {
			scanner.close();
		}
		return text.toString();
	}

	private static Parser<TuringMachine> generate() {
		return filepattern();
	}

	private static Parser<TuringMachine> filepattern() {
		final Parser<List<Expression>> expParser = expression()
					.sepBy(Scanners.string(newline));
		return expParser.map(new org.codehaus.jparsec.functors.Map<List<Expression>, TuringMachine>() {
			@Override
			public TuringMachine map(List<Expression> arg0) {
				try {
					return compileTuringMachine(arg0);
				} catch (ParsingError e) {
					System.err.println("Parse error");
					return null;
				}
			}
		});
	}

	protected static TuringMachine compileTuringMachine(List<Expression> expressions) throws ParsingError {
		TuringMachineBuilder builder = new TuringMachineBuilder();
		for (Expression e : expressions) {
			e.addTo(builder);
		}
		
		return builder.generate();
	}
	
	private static Parser<Expression> expression() {
		return Parsers.or(endstate(), startstate(), transition());
	}
	
	
	private static Parser<Transition> transition() {
		Pair<ReadHead, Output> t;
		return Parsers.tuple(readhead(),Scanners.WHITESPACES.many1(), Scanners.string("=>"),Scanners.WHITESPACES.many1(), output()).map(new org.codehaus.jparsec.functors.Map<Tuple5<ReadHead, List<Void>,Void,List<Void>, Output>, Transition>() {
			@Override
			public Transition map(Tuple5<ReadHead, List<Void>,Void,List<Void>, Output> arg0) {
				return new Transition(arg0.a, arg0.e);
			}
		});
	}

	private static Parser<Output> output() {
		return Parsers.tuple(Scanners.IDENTIFIER, Scanners.WHITESPACES.many1(), Scanners.IDENTIFIER, Scanners.WHITESPACES.many1(), movement()).map(new org.codehaus.jparsec.functors.Map<Tuple5<String, List<Void>, String, List<Void>, Movement>, Output>() {
			@Override
			public Output map(Tuple5<String, List<Void>, String, List<Void>, Movement> arg0) {
				return new Output(arg0.a, arg0.c, arg0.e);
			}
		
		});
	}

	private static Parser<Movement> movement() {
		return Scanners.IDENTIFIER.map(new org.codehaus.jparsec.functors.Map<String, Movement>() {
			@Override
			public Movement map(String arg0) {
				if (arg0.equalsIgnoreCase("l"))
					return Movement.L;
				else if (arg0.equalsIgnoreCase("n"))
					return Movement.N;
				else if (arg0.equalsIgnoreCase("r"))
					return Movement.R;
				return null;
			}
		});
	}

	private static Parser<ReadHead> readhead() {
		return Parsers.tuple(Scanners.IDENTIFIER, Scanners.WHITESPACES.many1(), Scanners.IDENTIFIER).map(
				new org.codehaus.jparsec.functors.Map<Tuple3<String, List<Void>, String>, ReadHead>() {
					@Override
					public ReadHead map(Tuple3<String, List<Void>, String> arg0) {
						return new ReadHead(arg0.a, arg0.c);
					}
				});
	}

	private static Parser<StartState> startstate() {
		return Parsers
				.sequence(Scanners.isChar('>'), Scanners.IDENTIFIER)
				.map(new org.codehaus.jparsec.functors.Map<String, StartState>() {
					@Override
					public StartState map(String arg0) {
						return new StartState(arg0);
					}
				});
	}

	private static Parser<Endstate> endstate() {
		return Parsers
				.sequence(Scanners.isChar(':'), Scanners.IDENTIFIER)
				.map(new org.codehaus.jparsec.functors.Map<String, Endstate>() {
			@Override
			public Endstate map(String arg0) {
				return new Endstate(arg0);
			}
		});
	}
}
