package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Przykładowe testy przykładowej klasy NaiveDeterministicAutomatonSpecification.
 */
public class TestNaiveDeterministicAutomatonSpecification extends TestCase {
    
    /**
     * Test prostego automatu
     */
    public final void testDeterministicAutomaton() {
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

        State r0 = automat.targetState(s2, 'b');
        assertSame(r0, s3);
        assertNotSame(r0, s1);

    }
}