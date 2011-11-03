package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveDeterministicAutomatonSpecification.
 */
public class TestNaiveDeterministicAutomatonSpecification extends TestCase {
    
    /**
     * Test prostego automatu
     */
    public final void TestDeterministicAutomaton() {
        NaiveDeterministicAutomatonSpecification automat = new NaiveDeterministicAutomatonSpecification();
        
        State s1 = automat.addState();
        State s2 = automat.addState();
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        State s3 = automat.addState();
        automat.addTransition(s2, s3, new CharTransitionLabel('b'));
        State s4 = automat.addState();
        automat.addTransition(s3, s4, new CharTransitionLabel('c'));

        automat.markAsInitial(s1);
        automat.markAsFinal(s4);

        State s0 = automat.getInitialState();

        List<OutgoingTransition> s0Out = automat.allOutgoingTransitions(s0);
        
        assertEquals(s0Out.size(), 1);
        assertFalse(automat.isFinal(s0));
        assertEquals(((CharTransitionLabel)s0Out.get(0).getTransitionLabel()).getChar(), 'a');
        assertTrue(((CharTransitionLabel)s0Out.get(0).getTransitionLabel()).canAcceptCharacter('a'));
        assertFalse(((CharTransitionLabel)s0Out.get(0).getTransitionLabel()).canAcceptCharacter('b'));
        
        State r0 = automat.targetState(s2, 'b');
        assertSame(r0, s3);
        assertSame(r0, s1);
    }
}
