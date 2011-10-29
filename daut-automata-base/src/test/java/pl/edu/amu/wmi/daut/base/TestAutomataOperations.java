package pl.edu.amu.wmi.daut.base;

import java.lang.Object;
import junit.framework.TestCase;
import java.util.List;


public class TestAutomataOperations extends TestCase {

public final void testSimpleAutomaton(){

AutomatonSpecification automatonA = new NaiveAutomatonSpecification();
State q0 = automatonA.addState();
State q1 = automatonA.addState();
automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
automatonA.addLoop(q1 ,new CharTransitionLabel('a') );
automatonA.addLoop(q1, new CharTransitionLabel('b') );
automatonA.markAsInitial(q0);
automatonA.markAsFinal(q1);

AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
State q10 = automatonB.addState();
State q11 = automatonB.addState();
State q12 = automatonB.addState();
automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
automatonB.addTransition(q11, q12, new CharTransitionLabel('b'));
automatonB.markAsInitial(q10);
automatonB.markAsFinal(q12);


AutomatonSpecification Result = AutomataOperations.intersection(automatonA, automatonB);
AutomatonByRecursion automaton = AutomatonByRecursion(Result);

assertTrue(automaton.accepts("aa"));
assertTrue(automaton.accepts("ab"));
assertFalse(automaton.accepts(""));
assertFalse(automaton.accepts("a"));
}
}

