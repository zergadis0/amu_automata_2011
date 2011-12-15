package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;

/**
 *
 * Test klasy DigitOperator.
 */
public class TestDigitOperator extends TestCase {

    /**
     * Test metody createFixedAutomaton.
     */
    public void testDigitOperator() {

        DigitOperator operator = new DigitOperator();
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        assertFalse(automaton.isEmpty());
        assertTrue(automaton.isDeterministic());
        assertEquals(automaton.countTransitions(), 1);
        assertEquals(automaton.countStates(), 2);
        assertFalse(automaton.acceptEmptyWord());

    }
}
