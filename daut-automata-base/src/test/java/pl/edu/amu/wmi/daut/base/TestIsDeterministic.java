package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Klasa testujaca metodę isDeterministic.
 */
public class TestIsDeterministic extends TestCase {

    public final void oneTransition() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        AutomatonSpecification automata = aut.makeOneTransitionAutomaton('a');

        assertTrue(automata.isDeterministic());

    }

    /**public final void fourTransitions() {

    	AutomatonSpecification aut = new NaiveAutomatonSpecification();

    	State s0 = aut.addState();
    	State s1 = aut.addState();
    	State s2 = aut.addState();
    	State s3 = aut.addState();

    	spec.addTransition(s0, s1, new CharTransitionLabel('a'));

    }*/
};