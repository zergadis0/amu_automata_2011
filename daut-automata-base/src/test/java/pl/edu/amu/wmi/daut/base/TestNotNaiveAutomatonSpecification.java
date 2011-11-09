package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveAutomatonSpecification.
 */
public class TestNotNaiveAutomatonSpecification extends TestCase {

    /**
     * Prosta etykieta przejścia dla celów testowych.
     */
    private static class TestTransition implements TransitionLabel {
        /**
         * Konstruuje etykietę oznaczoną znakiem 'c'.
         */
        public TestTransition(char c) {
            ch_ = c;
        }

        public boolean canBeEpsilon() {
            return false;
        }

        public boolean canAcceptCharacter(char c) {
            return c == ch_;
        }

        public char getChar() {
            return ch_;
        }

        private char ch_;
    }

    /**
     * Test prostego automatu o trzech stanach.
     */
    public final void testSimpleAutomaton() {
        NotNaiveAutomatonSpecification spec = new NotNaiveAutomatonSpecification();

        // budowanie

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new TestTransition('a'));
        State s2 = spec.addState();
        spec.addTransition(s0, s2, new TestTransition('b'));
	spec.addTransition(s1, s2, new TestTransition('c'));
	State s3 = spec.addState();
        spec.addTransition(s0, s3, new TestTransition('d'));

        spec.markAsInitial(s0);
        spec.markAsFinal(s3);;
        
        State r0 = spec.getInitialState();
        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);
        
        // test f-cji get initial
		
	assertEquals(r0Outs.size(), 2);
		
	// test f-cji is final
		
        assertFalse(spec.isFinal(r0));
        
        int check = spec.getNumberOfOutgoingTransitions(null);
        assertEquals(check, 0);
		
		State r1;
		State r2;
		State r3;
		
		if (((TestTransition) r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            r2 = r0Outs.get(1).getTargetState();
			r3 = r0Outs.get(2).getTargetState();
            assertEquals(((TestTransition) r0Outs.get(1).getTransitionLabel()).getChar(), 'b');
			assertEquals(((TestTransition) r0Outs.get(2).getTransitionLabel()).getChar(), 'd');
			assertFalse(((TestTransition) r0Outs.get(2).getTransitionLabel()).getChar() == 'c');
            assertTrue(
                ((TestTransition) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('b'));
			assertTrue(
                ((TestTransition) r0Outs.get(2).getTransitionLabel()).canAcceptCharacter('d'));	
            assertFalse(
                ((TestTransition) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('c'));
			assertFalse(
                ((TestTransition) r0Outs.get(2).getTransitionLabel()).canAcceptCharacter('c'));	
            assertFalse(((TestTransition) r0Outs.get(1).getTransitionLabel()).canBeEpsilon());
        } else if (((TestTransition)r0Outs.get(0).getTransitionLabel()).getChar() == 'd') {
			r1 = r0Outs.get(0).getTargetState();
			assertTrue(spec.isFinal(r1));
		} else {
		 r1 = r0Outs.get(0).getTargetState();
		 assertEquals(((TestTransition) r0Outs.get(0).getTransitionLabel()).getChar(), 'b');
		}
        
        

        

        assertFalse(spec.isFinal(r1));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 3);
    }
}
