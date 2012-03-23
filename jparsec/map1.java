Parser<TuringMachine> turingparser = expParser.map(
  new org.codehaus.jparsec.functors.Map<List<Expression>, TuringMachine>() {
public TuringMachine map(List<Expression> arg0) {
    return compileTuringMachine(arg0);
  }}});
