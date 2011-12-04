package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

import junit.framework.TestCase;


/**
 * Testy klasy SingleCharOperator.
 */
public class SingleCharOperator extends TestCase {

    /**
     * Metoda testujaca konstruktor
     */
    public testSingleCharOperator() {

        SingleCharOperator operator = new SingleCharOperator('c');
        assertNotNull(operator);
        assertEquals(operator.Character(),'c')
    }

    /**
     * Test metody createFixedAutomaton.
     */
    public final void testCreateFixedAutomaton() {

        SingleCharOperator operator = new SingleCharOperator('c');
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        assertFalse(automaton.isEmpty());
        assertTrue(automaton.isDeterministic());
        assertEquals(countTransitions(),1);

    }
}
