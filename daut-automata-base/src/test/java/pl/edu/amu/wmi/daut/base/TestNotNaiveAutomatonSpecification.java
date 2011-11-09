package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveAutomatonSpecification.
 */
public class TestNotNaiveAutomatonSpecification extends TestCase {

    public final void testSimpleAutomaton() {
        NotNaiveAutomatonSpecification spec = new NotNaiveAutomatonSpecification();

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new CharTransitionLabel('a'));
        State s2 = spec.addState();
        spec.addTransition(s0, s2, new CharTransitionLabel('b'));
        spec.addTransition(s1, s2, new CharTransitionLabel('c'));
        
        spec.markAsInitial(s0);
        spec.markAsFinal(s2);
        
        State r0 = spec.getInitialState();
        
        assertFalse(spec.isFinal(r0));
       
    }
}