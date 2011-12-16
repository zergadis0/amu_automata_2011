package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.re.DigitOperator.Factory;

/**
 *
 * Test klasy DigitOperator.
 */
public class TestDigitOperator extends TestCase {

    /**
     * Test metody createFixedAutomaton.
     */
    public void testCreateFixedAutomaton() {

        DigitOperator operator = new DigitOperator();
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        assertFalse(automaton.isEmpty());
        assertTrue(automaton.isDeterministic());
        assertEquals(automaton.countTransitions(), 1);
        assertEquals(automaton.countStates(), 2);
        assertFalse(automaton.acceptEmptyWord());

    }

    /**
     * Test fabryki.
     */
    public void testFactory() {

        Factory factory = new Factory();
        assertEquals(factory.numberOfParams(), 0);
        RegexpOperator operator2 = factory.doCreateOperator(null);
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}
