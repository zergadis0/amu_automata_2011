package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Testy różnych operacji na automatach.
*/
public class TestAutomataOperations extends TestCase {

    /**
* Pierwszy test.
*/
    public final void testIntersection1() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
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


        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);
        AutomatonByRecursion automaton = new AutomatonByRecursion(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("ab"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Blablablabla bla"));
        assertFalse(automaton.accepts("abababab"));
        assertFalse(automaton.accepts("aba"));
        assertFalse(automaton.accepts("ba"));
    }
    /**
    *drugi test
    */

    public final void testIntersection2() {

        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();

        State q10 = automatonC.addState();
        State q11 = automatonC.addState();
        State q12 = automatonC.addState();
        automatonC.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonC.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonC.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonC.addTransition(q11, q12, new CharTransitionLabel('b'));
        automatonC.markAsInitial(q10);
        automatonC.markAsFinal(q12);

        AutomatonSpecification result = AutomataOperations.intersection(automatonC, automatonC);
        AutomatonByRecursion automaton1 = new AutomatonByRecursion(result);

        assertTrue(automaton1.accepts("abababa"));
        assertTrue(automaton1.accepts("abbbbbbbbb"));
        assertTrue(automaton1.accepts("aaaaaaaaaaaaa"));
        assertFalse(automaton1.accepts(""));
        assertFalse(automaton1.accepts("baba"));
        assertFalse(automaton1.accepts("dziwne dlugie slowo"));
    }
}
