package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Klasa testujaca metodę isDeterministic.
 */
public class TestIsDeterministic extends TestCase {

    /**
     * Metoda testująca metodę isDeterministic.
     */
    public final void testIsDeterministic() {

        //budowanie
        AutomatonSpecification test = new NaiveAutomatonSpecification();
        State s0 = test.addState();
        
        test.markAsInitial(s0);
        test.markAsFinal(s0);
 
        test.addTransition(s0, new CharTransitionLabel('a'));
        
        //testowanie
        State r0 = test.getInitialState();
        
        List<OutgoingTransition> OutgoingTransitionsList = test.allOutgoingTransitions(r0);
        
        assertEquals(OutgoingTransitionsList.size(), 1);
        assertTrue(test.isFinal(r0));
        
        assertFalse(test.isFinal(s0));
        assertTrue(test.isFinal(s0));
        assertSame(r0, test.getInitialState());
        assertSame(r0, s0);

        assertTrue(test.isDeterministic());
        
        
        assertEquals(OutgoingTransitionsList.size(), 1);
        assertTrue(test.isFinal(r0));
        
        assertFalse(test.isFinal(s0));
        assertTrue(test.isFinal(s0));
        assertSame(r0, test.getInitialState());
        assertSame(r0, s0);
    
        List<State> states = test.allStates();

        assertEquals(states.size(), 1);
    
    }
  
};