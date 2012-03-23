package com.zombiecalypse.geoparser;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.functors.Pair;
import org.codehaus.jparsec.functors.Tuple4;
import org.junit.Test;

import com.zombiecalypse.geoparser.Expression.RectExpression;
import com.zombiecalypse.geoparser.Expression.RotateExpression;
import com.zombiecalypse.geoparser.Expression.TranslateExpression;

public class GeoParserFactory {
	
	
	/*
	 * AK another approach - top-down can be found at
	 * https://github.com/zombiecalypse/P2-Presentations/blob/master/jparsec.mdown
	 */
	static final String example = 
			"do\n" +
			"	rect 0 0 1 1\n"+
			"	rotate 60\n"+
			"end\n"+
			"do\n" +
			"	rect 0 0 1 1\n"+
			"	translate 50 50\n"+
			"end";
	
	private Parser<Integer> whiteSpaceInteger() {
		return Scanners.WHITESPACES.next(Scanners.INTEGER)
				.map(new org.codehaus.jparsec.functors.Map<String, Integer>() {
			public Integer map(String from) {
				return Integer.parseInt(from);
			}
		});
	}
	
	@Test 
	public void parseTheNumber() {
		assertThat(whiteSpaceInteger().parse("    \n 23"), is((Object) 23));
	}
	// Map: Integer -> RotateExpression
	private Parser<RotateExpression> rotateParser() {
		return Scanners.string("rotate").next(whiteSpaceInteger())
				.map(new org.codehaus.jparsec.functors.Map<Integer, RotateExpression>() {
					public RotateExpression map(Integer from) {
						return new RotateExpression(from);
					}
				});
	}
	
	@Test
	public void parseRotateCommand() {
		assertThat(rotateParser().parse("rotate 60"), is( (Object)new RotateExpression(60)));
	}
	
	@Test
	public void parseTranslateExpression() {
		assertThat(translateParser().parse("translate 30 50"), is((Object) new Expression.TranslateExpression(30,50)));
	}

	private Parser<Expression.TranslateExpression> translateParser() {
		return Scanners.string("translate").next(Parsers.tuple(whiteSpaceInteger(), whiteSpaceInteger()))
				.map(new org.codehaus.jparsec.functors.Map<
						Pair<Integer, Integer>, 
						Expression.TranslateExpression>() {
							public TranslateExpression map(
									Pair<Integer, Integer> from) {
								return new Expression.TranslateExpression(from.a, from.b);
							}
				});
	}
	
	@Test
	public void parseRectExpression() {
		assertThat(rectParser().parse("rect 0 0 1 1"), is((Object) new Expression.RectExpression(0,0,1,1)));
	}

	private Parser<Expression.RectExpression> rectParser() {
		return Scanners.string("rect").next(Parsers.tuple(whiteSpaceInteger(), whiteSpaceInteger(), whiteSpaceInteger(), whiteSpaceInteger()))
				.map(new org.codehaus.jparsec.functors.Map<
						Tuple4<Integer, Integer, Integer, Integer>, Expression.RectExpression>() {
							public RectExpression map(
									Tuple4<Integer, Integer, Integer, Integer> from) {
								return new Expression.RectExpression(from.a, from.b, from.c, from.d);
							}
				});
	}
	
	private Parser<Expression> expressionParser() {
		return Parsers.or(rectParser(), translateParser(), rotateParser());
	}
	
	private Parser<List<Expression>> blockParser() {
		return Parsers.between(
				Scanners.string("do").next(Scanners.WHITESPACES), 
				expressionParser().sepBy(Scanners.WHITESPACES), 
				Scanners.WHITESPACES.many().next(Scanners.string("end")));
	}
	
	public Parser<List<List<Expression>>> fileParser() {
		return blockParser().sepBy(Scanners.WHITESPACES);
	}
	
	public static void main(String args[]) {
		(new GeoParserFactory()).fileParser().parse(example);
	}
}
