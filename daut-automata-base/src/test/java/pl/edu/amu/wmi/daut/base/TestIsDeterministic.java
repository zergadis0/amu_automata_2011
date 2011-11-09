package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca metodę isDeterministic.
 */
public class TestIsDeterministic extends TestCase {

    public final void testOneTransition() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        AutomatonSpecification automata = aut.makeOneTransitionAutomaton('a');

        assertTrue(automata.isDeterministic());
    }

    public final void testFourTransitions() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s1);
        aut.markAsFinal(s2);
        aut.markAsFinal(s3);

        aut.addTransition(s0, s1, new CharTransitionLabel('a'));
        aut.addTransition(s0, s2, new CharTransitionLabel('a'));
        aut.addTransition(s0, s3, new CharTransitionLabel('a'));

        assertFalse(aut.isDeterministic());
    }

    public final void testSixTransitions() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        State s4 = aut.addState();
        State s5 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        aut.markAsFinal(s4);
        aut.markAsFinal(s5);

        aut.addTransition(s0, s1, new CharTransitionLabel('a'));
        aut.addTransition(s1, s2, new CharTransitionLabel('a'));
        aut.addTransition(s2, s3, new CharTransitionLabel('a'));
        aut.addTransition(s3, new CharTransitionLabel('a'));
        aut.addTransition(s2, s4, new CharTransitionLabel('a'));
        aut.addTransition(s4, new CharTransitionLabel('a'));
        aut.addTransition(s2, s5, new CharTransitionLabel('a'));
        aut.addTransition(s5, new CharTransitionLabel('a'));

        assertFalse(aut.isDeterministic());
    }

    /**public final void testEmptyAutomaton() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        assertTrue(aut.isDeterministic());
    }*/
};