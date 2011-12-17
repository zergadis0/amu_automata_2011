package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.base.State;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AnyTransitionLabel;

/**
 * test klasy anystringoperator.
 */
public class TestAnyStringOperator extends TestCase {

    /**
     * test konstruktora anystringoperator.
     */
    public void testAnyStringOperator() {
        AnyStringOperator operator = new AnyStringOperator();
    }

    /**
     *  testuje automat roznych znakow, takze pustego przejscia.
     */
    public final void testcreateFixedAutomaton() {
        
        AnyStringOperator spec2 = new AnyStringOperator();
        NondeterministicAutomatonByThompsonApproach automaton2 =
        new NondeterministicAutomatonByThompsonApproach
                (spec2.createFixedAutomaton());
        assertTrue(automaton2.accepts("qwer"));
        assertTrue(automaton2.accepts("qqqq"));
        assertTrue(automaton2.accepts("w"));
        assertTrue(automaton2.accepts("kakademona"));
        assertTrue(automaton2.accepts("qqrrqqrr"));
        assertTrue(automaton2.accepts(""));
        assertTrue(automaton2.accepts("  s  "));
    }
}
