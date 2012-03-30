package com.zombiecalypse.geoparser;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parser.Reference;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.functors.Pair;
import org.codehaus.jparsec.functors.Tuple4;
import org.junit.Test;

import com.zombiecalypse.geoparser.Expression.BlockExpression;
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
			"	do\n" +
			"		rect 0 0 1 1\n" +
			"		translate 50 50\n" +
			"		rotate 20\n" +
			"	end\n" +
			"	rect 0 0 1 1\n"+
			"	rotate 60\n"+
			"end\n"+
			"do\n" +
			"	rect 0 0 1 1\n"+
			"	translate 50 50\n"+
			"end";
	private Reference<Expression> expressionReference;
	
	private Parser<Integer> whiteSpaceInteger() {
		return Scanners.WHITESPACES.next(Scanners.INTEGER)
				.map(new org.codehaus.jparsec.functors.Map<String, Integer>() {
					public Integer map(String from) {
						return Integer.parseInt(from);
					}
				});
	}
	
	@Test
	public void whiteSpaceIntegerTest() {
		assertThat(whiteSpaceInteger().parse("    \n50"), instanceOf(Integer.class));
		assertThat(whiteSpaceInteger().parse("     50"), is((Object) 50));
	}
	
	private Parser<RotateExpression> rotateExpression() {
		return Scanners.string("rotate").next(whiteSpaceInteger())
				.map(new org.codehaus.jparsec.functors.Map<Integer, RotateExpression>(){
					public RotateExpression map(Integer from) {
						return new RotateExpression(from);
					}
				});
				
	}
	
	@Test
	public void rotateExpressionTest() {
		assertThat(rotateExpression().parse("rotate 60"), is((Object)new RotateExpression(60)));
	}
	
	private Parser<TranslateExpression> translateExpression() {
		return Scanners.string("translate").next(Parsers.tuple(whiteSpaceInteger(), whiteSpaceInteger()))
				.map(new org.codehaus.jparsec.functors.Map<Pair<Integer, Integer>, TranslateExpression>() {
					public TranslateExpression map(Pair<Integer, Integer> from) {
						return new TranslateExpression(from.a, from.b);
					}
				});
	}
	
	@Test
	public void translateExpressionTest() {
		assertThat(translateExpression().parse("translate 40 50"), is((Object) new TranslateExpression(40, 50)));
	}
	
	private Parser<RectExpression> rectExpression() {
		return Scanners.string("rect").next(whiteSpaceInteger().times(4))
				.map(new org.codehaus.jparsec.functors.Map<List<Integer>, RectExpression>() {
					public RectExpression map(List<Integer> arg0) {
						return new RectExpression(arg0.get(0), arg0.get(1), arg0.get(2), arg0.get(3));
					}
				});
	}
	
	private Parser<Expression> expression() {
		if (this.expressionReference == null) {
			this.expressionReference = Parser.newReference();
			this.expressionReference.set(Parsers.or(
					rectExpression(), 
					rotateExpression(),
					translateExpression(), 
					block()));
		}
		return expressionReference.lazy();
	}
	
	private Parser<BlockExpression> block() {
		
		return Parsers.between(
				Scanners.string("do").next(Scanners.WHITESPACES), 
				expression().sepBy(Scanners.WHITESPACES), 
				Scanners.WHITESPACES.next(Scanners.string("end")))
				.map(new org.codehaus.jparsec.functors.Map<List<Expression>, BlockExpression>() {
					public BlockExpression map(List<Expression> arg0) {
						return new BlockExpression(arg0);
					}
				});
	}
	
	public Parser<List<BlockExpression>> descriptionParser() {
		return block().sepBy(Scanners.WHITESPACES);
	}
	
	public static void main(String[] args) {
		System.out.println((new GeoParserFactory()).descriptionParser().parse(example));
	}
}























