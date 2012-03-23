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
}
