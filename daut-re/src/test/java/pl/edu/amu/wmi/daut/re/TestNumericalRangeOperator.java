/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;

/**
 *
 * @author Darek
 */
public class TestNumericalRangeOperator extends TestCase {
    static final int BASE = 0;

    /**
     * Testuje automat roznych znakow, takze pustego przejscia.
     */
    public final void testcreateFixedAutomaton() {
        NumericalRangeOperator spec = new NumericalRangeOperator(5, 5);
        NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec.createFixedAutomaton());
        assertTrue(automaton.accepts("5"));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("2"));
        assertFalse(automaton.accepts("05"));
        assertFalse(automaton.accepts("4"));
        assertFalse(automaton.accepts("6"));
        assertFalse(automaton.accepts("111"));

        NumericalRangeOperator spec2 = new NumericalRangeOperator(5, 10);
        NondeterministicAutomatonByThompsonApproach automaton2 =
                new NondeterministicAutomatonByThompsonApproach(spec2.createFixedAutomaton());

        for (int i = 5; i <= 10; ++i) {
            assertTrue(automaton2.accepts(Integer.toString(i)));
        }

        assertFalse(automaton2.accepts("1"));
        assertFalse(automaton2.accepts("4"));
        assertFalse(automaton2.accepts("11"));
        assertFalse(automaton2.accepts("17"));
        
        
        NumericalRangeOperator spec3 = new NumericalRangeOperator(0, 9);
        NondeterministicAutomatonByThompsonApproach automaton3 =
                new NondeterministicAutomatonByThompsonApproach(spec3.createFixedAutomaton());

        for (int i = 0; i <= 9; ++i) {
            assertTrue(automaton3.accepts(Integer.toString(i)));
        }

        assertFalse(automaton3.accepts("11"));
        assertFalse(automaton3.accepts("14"));
        assertFalse(automaton3.accepts("411"));
        assertFalse(automaton3.accepts("1715"));
    }
}