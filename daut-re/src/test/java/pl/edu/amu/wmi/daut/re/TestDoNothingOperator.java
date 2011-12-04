package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

import junit.framework.TestCase;


/**
 * Testy klasy DoNothingOperator.
 */
public class TestDoNothingOperator extends TestCase {

    /**
     * Test konstruktora DoNothingOperator.
     */
    public TestDoNothingOperator() {

        DoNothingOperator operator = new DoNothingOperator();
        assertNotNull(operator);
    }

    /**
     * Test metody createAutomatonFromOneAutomaton.
     */
    public final void testCreateAutomatonFromOneAutomaton() {

        DoNothingOperator operator = new DoNothingOperator();
        AutomatonSpecification subautomaton = new NaiveAutomatonSpecification();

        AutomatonSpecification automaton =
                operator.createAutomatonFromOneAutomaton(subautomaton);

        assertEquals(automaton, subautomaton);
    }
}
