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
        
        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);
        
        assertEquals(r0Outs.size(), 2);
        
        State r1;
        State r2;

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            r2 = r0Outs.get(1).getTargetState();
            assertEquals(((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).getChar(), 'b');
            assertTrue(
                ((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('b'));
            assertFalse(
                ((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canBeEpsilon());
        } else {
            // kolejność może być odwrócona
            r1 = r0Outs.get(1).getTargetState();
            r2 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'b');
        }

        assertFalse(spec.isFinal(r1));
        assertTrue(spec.isFinal(r2));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r0, r2);
        assertNotSame(r1, r2);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 3);
   
    }
     public final void testSimpleNaiveCompatibleAutomaton() {
        NotNaiveAutomatonSpecification spec2 = new NotNaiveAutomatonSpecification();
    
        State x0 = spec2.addState();
        State x1 = spec2.addState();
        spec2.addNaiveTransition(x0, x1, new CharTransitionLabel('x'));
        
        spec2.markAsInitial(x0);
        spec2.markAsFinal(x1);
     }
}