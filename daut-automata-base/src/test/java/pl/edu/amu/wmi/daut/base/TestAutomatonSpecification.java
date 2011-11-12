package pl.edu.amu.wmi.daut.base;

import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification.NaiveState;
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
     * Test metody acceptEmptyWord.
     */
    public final void testAcceptEmptyWord() {

        //Test 1 - stan poczatkowy jest stanem koncowym

        NaiveAutomatonSpecification testSpec1 = new NaiveAutomatonSpecification();
        NaiveState state = testSpec1.addState();
        testSpec1.markAsInitial(state);
        testSpec1.markAsFinal(state);
        assertTrue(testSpec1.acceptEmptyWord());

        //Test 2 - automat ma wiecej stanow, bez epsilon-przejsc do stanu koncowego

        NaiveAutomatonSpecification testSpec2 = new NaiveAutomatonSpecification();
        State q0 = testSpec2.addState();
        State q1 = testSpec2.addState();
        State q2 = testSpec2.addState();

        testSpec2.addTransition(q0, q1, new CharTransitionLabel('a'));
        testSpec2.addTransition(q1, q2, new CharTransitionLabel('b'));

        testSpec2.markAsInitial(q0);
        testSpec2.markAsFinal(q2);

        assertFalse(testSpec2.acceptEmptyWord());

        //Test 3 - automat jak w poprzednim przypadku
        //ale zawiera epsilon-przejscia do stanu koncowego

        State q3 = testSpec2.addState();

        testSpec2.addTransition(q0, q3, new EpsilonTransitionLabel());
        testSpec2.addTransition(q3, q2, new EpsilonTransitionLabel());

        assertTrue(testSpec2.acceptEmptyWord());
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
