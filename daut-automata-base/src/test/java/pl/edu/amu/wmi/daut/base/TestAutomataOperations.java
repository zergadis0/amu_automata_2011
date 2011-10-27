AutomatonSpecification automatonA = new NaiveAutomatonSpecification();
State q0 = automatonA.addState();
State q1 = automatonA.addState();
automatonA.addTransmisionA(q0, q1, new TestTransmision('a'));
automatonA.addLoop(q1 new TestTransmiosion('a') );
automatonA.addLoop(q1 new TestTransmiosion('b') );
automatonA.markAsInitial(q0);
automatonA.markAsFinal(q1);

AutomatonSpecification automatonB = new....

AutomatonSpecification Result = Automata.Operations.intersection(automatonA, automatonB);
AutomatonByRecursion automaton(result);

assertTrue(automaton.accepts("aa"));
assertTrue(automaton.accepts("ab"));
assertFalse(automaton.accepts(""));
assertFalse(automaton.accepts("a"));
