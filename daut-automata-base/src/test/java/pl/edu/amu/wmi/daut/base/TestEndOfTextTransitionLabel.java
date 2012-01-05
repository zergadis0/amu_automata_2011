package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca klase EndOfTextTransitionLabel.
 */
public class TestEndOfTextTransitionLabel extends TestCase {

    /**
     * Test metody doCheckContext.
     */
    public final void testDoCheckContext() {
        EndOfTextTransitionLabel trans = new EndOfTextTransitionLabel();

        assertTrue(trans.doCheckContext("trololololo", 11));
        assertTrue(trans.doCheckContext("masa", 4));

        assertFalse(trans.doCheckContext("panandrzej", 3));
        assertFalse(trans.doCheckContext("cojapacze", 15));

        /**
         * Test atrybutów.
         */

        assertEquals(trans.toString(), "EndOfText");

        assertFalse(trans.isEmpty());
        assertFalse(trans.canAcceptCharacter('s'));

    }

    /**
     * Przykladowy automat z uzyciem EndOfTextTransitionLabel.
     */
    public final void testEndOfTextTransitionLabelAutomaton() {

        final AutomatonSpecification spec = new NaiveAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q1, q0, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new EndOfTextTransitionLabel());

        spec.markAsInitial(q0);
        spec.markAsFinal(q2);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("ababa"));

        assertFalse(automaton.accepts("kabanos"));
        assertFalse(automaton.accepts("ab"));
    }

}
