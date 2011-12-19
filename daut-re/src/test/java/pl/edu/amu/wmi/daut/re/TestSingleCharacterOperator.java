package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

import junit.framework.TestCase;

import java.util.List;

import java.util.ArrayList;


/**
 * Testy klasy SingleCharacterOperator.
 */
public class TestSingleCharacterOperator extends TestCase {

    /**
     * Metoda testujaca konstruktor.
     */
    public void testSingleCharacterOperator() {

        SingleCharacterOperator operator = new SingleCharacterOperator('c');
        assertNotNull(operator);
        assertEquals(operator.getCharacter(), 'c');
    }

    /**
     * Test metody createFixedAutomaton.
     */
    public final void testCreateFixedAutomaton() {

        SingleCharacterOperator operator = new SingleCharacterOperator('c');
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        assertFalse(automaton.isEmpty());
        assertEquals('c', operator.getCharacter());
    }

    /**
     * Test fabryki.
     */
    public final void testFactory() {

        RegexpOperatorFactory factory = new SingleCharacterOperator.Factory();
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        assertNotNull(factory.doCreateOperator(list));
        assertEquals(1, factory.numberOfParams());

    }
}
