package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;

/**
 * Klasa, testuje klasę BellCharacterOperator.
 */
public class TestBellCharacterOperator extends TestCase {

    /**
     * Konstruuje przykładowy automat, a następnie go testuje.
     */
    public void testAutomaton() {

        BellCharacterOperator operator = new BellCharacterOperator();
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach aut = new NondeterministicAutomatonByThompsonApproach(automaton);

        assertTrue(aut.accepts(Character.toString('\7')));
        assertFalse(aut.accepts(Character.toString('8')));
        assertFalse(aut.accepts(Character.toString('c')));
        assertTrue(aut.accepts(Character.toString((char) 7)));
        assertFalse(aut.accepts(Character.toString(' ')));
        assertFalse(aut.accepts(Character.toString('\6')));
    }
}
