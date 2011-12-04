package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

import junit.framework.TestCase;


/**
 * Testy klasy SingleCharacterOperator.
 */
public class TestSingleCharacterOperator extends TestCase {

    /**
     * Metoda testujaca konstruktor
     */
    public void testSingleCharacterOperator() {

        SingleCharacterOperator operator = new SingleCharacterOperator('c');
        assertNotNull(operator);
        assertEquals(operator.Character(),'c');
    }

    /**
     * Test metody createFixedAutomaton.
     */
    public final void testCreateFixedAutomaton() {

        SingleCharacterOperator operator = new SingleCharacterOperator('c');
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        assertFalse(automaton.isEmpty());
        assertTrue(automaton.isDeterministic());
        assertEquals(countTransitions(),1);

    }
}
