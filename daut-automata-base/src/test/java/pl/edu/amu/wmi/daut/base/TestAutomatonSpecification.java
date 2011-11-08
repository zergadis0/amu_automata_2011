package pl.edu.amu.wmi.daut.base;
import java.util.List;
import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Testy klasy AutomatonSpecification.
 */
public class TestAutomatonSpecification extends TestCase {

    /**
     * Test metody countStates.
     */
    public final void testCountStates() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countStates(), 0);

        //Test 2
        State q0 = spec.addState();
        assertEquals(spec.countStates(), 1);

        //Test 3
        for (int i = 1; i <= 123456; i++) {
            State q = spec.addState();
        }
        assertEquals(spec.countStates(), 123456 + 1);
    }

    /**
     * Test metody countStates.
     */
    public final void testCountTransitions() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countTransitions(), 0);

        //Test 2
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q2);

        assertEquals(spec.countTransitions(), 2);

        //Test 3
        spec.addTransition(q2, q0, new CharTransitionLabel('c'));

        assertEquals(spec.countTransitions(), 3);

        //Test 4
        spec.addTransition(q0, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 4);

        //Test 5
        spec.addTransition(q2, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 5);
    }

    /**
     * Test metody addBranch().
     */
    public final void testaddBranch() {

       // Budowanie automatu o 4 stanach.
       AutomatonSpecification spec = new NaiveAutomatonSpecification();
       State s0 = spec.addState();
       spec.markAsInitial(s0);
       List<TransitionLabel> transitions =
               Arrays.<TransitionLabel>asList(
            new CharTransitionLabel('a'),
            new CharTransitionLabel('b'),
            new CharTransitionLabel('c')
            );
       State s3 = spec.addBranch(s0, transitions);
       spec.markAsFinal(s3);

       //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);
        assertEquals(r0Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r1 = r0Outs.get(0).getTargetState();
        List<OutgoingTransition> r1Outs = spec.allOutgoingTransitions(r1);
        assertEquals(r1Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r2 = r1Outs.get(0).getTargetState();
        List<OutgoingTransition> r2Outs = spec.allOutgoingTransitions(r2);
        assertEquals(r2Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r3 = r2Outs.get(0).getTargetState();

        assertFalse(spec.isFinal(r2));
        assertTrue(spec.isFinal(r3));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r1, r2);
        assertNotSame(r2, r3);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 4);
        
        // Budowanie automatu o 1 stanie.
        AutomatonSpecification spec2 = new NaiveAutomatonSpecification();
        State st0 = spec2.addState();
        spec.markAsInitial(st0);
        List<TransitionLabel> transitions2 =
                Arrays.<TransitionLabel>asList(
            );
        st0=spec2.addBranch(st0, transitions2);
        spec.markAsFinal(st0);
        
        //testowanie
        State rr0 = spec2.getInitialState();
        
        List<OutgoingTransition> r0Outs2 = spec2.allOutgoingTransitions(rr0);
        assertEquals(r0Outs2.size(), 0);
        
        
        
    }


    /**
     * Testuje działanie metody toString().
     */
    public final void testToString() {
        /**
         * Pozwala na wygenerowanie tekstu "oszukanego" automatu
         * na podstawie stanów i krawędzi wprowadzonych jako tekst.
         * Imitacja działania metody toString() dla automatu.
         */
        class AutomatonString {
            private String states, transitions, istates, fstates;

            public AutomatonString(String states, String transitions, String istates,
                    String fstates) {
                this.states = states;
                this.transitions = transitions;
                this.istates = istates;
                this.fstates = fstates;
            }

            /**
             * Zwraca "oszukany" automat w postaci tekstowej.
             */
            @Override
            public String toString() {
                StringBuffer str = new StringBuffer();
                str.append("Automaton:");
                str.append("\n-States: " + states);
                if (states.length() > 0) {
                    str.append(" ");
                }
                str.append("\n-Transitions:\n");
                if (transitions.length() > 0) {
                    str.append("  " + transitions.replaceAll(",", "\n  "));
                    str.append("\n");
                }
                str.append("-Initial state: " + istates);
                str.append("\n-Final states: " + fstates);
                if (fstates.length() > 0) {
                    str.append(" ");
                }
                return str.toString();
            }
        }

        // Pierwszy testowy automat:
        // Jeden stan z krawędzią
        NaiveAutomatonSpecification ta1 = new NaiveAutomatonSpecification();

        State ta1s0 = ta1.addState();
        ta1.addTransition(ta1s0, ta1s0, new CharTransitionLabel('a'));
        ta1.markAsInitial(ta1s0);
        ta1.markAsFinal(ta1s0);

        AutomatonString str;

        str = new AutomatonString("q0", "q0 -a-> q0", "q0", "q0");
        assertEquals(str.toString(), ta1.toString());

        // Drugi testowy automat:
        // Dwa stany, krawędzie jak w przypadku NFA
        NaiveAutomatonSpecification ta2 = new NaiveAutomatonSpecification();

        State ta2s0 = ta2.addState();
        State ta2s1 = ta2.addState();
        ta2.addTransition(ta2s0, ta2s0, new CharTransitionLabel('a'));
        ta2.addTransition(ta2s0, ta2s1, new CharTransitionLabel('a'));
        ta2.markAsInitial(ta2s0);
        ta2.markAsFinal(ta2s0);
        ta2.markAsFinal(ta2s1);

        String transitions = "q0 -a-> q0,q0 -a-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q0 q1");
        assertEquals(str.toString(), ta2.toString());

        // Trzeci testowy automat:
        // Dwa stany, dwa różne rodzaje krawędzi
        NaiveAutomatonSpecification ta3 = new NaiveAutomatonSpecification();

        State ta3s0 = ta3.addState();
        State ta3s1 = ta3.addState();
        ta3.addTransition(ta3s0, ta3s0, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s0, ta3s1, new CharTransitionLabel('b'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('b'));
        ta3.markAsInitial(ta3s0);
        ta3.markAsFinal(ta3s1);

        transitions = "q0 -a-> q0,q0 -b-> q1,q1 -a-> q1,q1 -b-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q1");
        assertEquals(str.toString(), ta3.toString());

        // Czwarty testowy automat:
        // sprawdzamy co jest zwracane dla "pustej" instancji klasy automatu
        NaiveAutomatonSpecification ta4 = new NaiveAutomatonSpecification();

        str = new AutomatonString("", "", "", "");
        assertEquals(str.toString(), ta4.toString());
    }
    /**
     * Testuje działanie metody testPrefixChecker().
     */
    public final void testPrefixChecker() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q0, q3, new CharTransitionLabel('c'));

        spec.addTransition(q1, q3, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));

        spec.addTransition(q2, q3, new CharTransitionLabel('a'));
        spec.addLoop(q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q5, new CharTransitionLabel('c'));

        spec.addTransition(q3, q0, new CharTransitionLabel('a'));
        spec.addTransition(q3, q0, new CharTransitionLabel('b'));
        spec.addTransition(q3, q4, new CharTransitionLabel('c'));

        // Stan 4 jest pułapką

        spec.addLoop(q4, new CharTransitionLabel('a'));
        spec.addLoop(q4, new CharTransitionLabel('b'));
        spec.addLoop(q4, new CharTransitionLabel('c'));

        // Stan 5 prowadzi tylko do stanu 4

        spec.addLoop(q5, new CharTransitionLabel('a'));
        spec.addTransition(q5, q4, new CharTransitionLabel('b'));
        spec.addLoop(q5, new CharTransitionLabel('c'));

        assertTrue(spec.prefixChecker(q0));
        assertTrue(spec.prefixChecker(q1));
        assertTrue(spec.prefixChecker(q2));
        assertTrue(spec.prefixChecker(q3));
        assertFalse(spec.prefixChecker(q4));
        assertFalse(spec.prefixChecker(q5));
    }
}
