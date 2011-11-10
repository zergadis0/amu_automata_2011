package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy AutomatonByRecursion.
 */
public class TestAutomatonByRecursion extends TestCase {
    /**
     * Symulacja testu.
     */
    public final void testLessComplicated() {

        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();

        spec.addTransition(q0a, q1a, new CharTransitionLabel('a'));
        spec.addLoop(q1a, new CharTransitionLabel('a'));
        spec.addLoop(q1a, new CharTransitionLabel('b'));

        spec.markAsInitial(q0a);
        spec.markAsFinal(q1a);

        final AutomatonByRecursion automaton = new AutomatonByRecursion(spec);

        assertTrue(automaton.accepts("ab"));
        assertFalse(automaton.accepts("bb"));
        assertFalse(automaton.accepts("a a"));
        assertTrue(automaton.accepts("aa"));
    }
    /**
     * Trochę bardziej skomplikowany test.
     */
    public final void testComplicated() {

        final AutomatonSpecification specA = new NaiveAutomatonSpecification();

        State q0A = specA.addState();
        State q1A = specA.addState();
        State q2A = specA.addState();
        State q3A = specA.addState();

        specA.addTransition(q0A, q1A, new CharTransitionLabel('a'));
        specA.addTransition(q1A, q2A, new CharTransitionLabel('b'));
        specA.addTransition(q1A, q3A, new CharTransitionLabel('b'));
        specA.markAsInitial(q0A);
        specA.markAsFinal(q2A);

        AutomatonByRecursion automatonA = new AutomatonByRecursion(specA);

        assertTrue(automatonA.accepts("ab"));
        assertFalse(automatonA.accepts("ab "));
        assertTrue(automatonA.accepts("ab"));
        assertFalse(automatonA.accepts("a b"));
    }
    /**
     * Najbardziej skomplikowany test.
     */
    public final void testVeryComplicated() {

        final AutomatonSpecification specB = new NaiveAutomatonSpecification();

        State q0B = specB.addState();
        State q1B = specB.addState();
        State q2B = specB.addState();
        State q3B = specB.addState();
        State q4B = specB.addState();
        State q5B = specB.addState();
        State q6B = specB.addState();
        State q7B = specB.addState();
        State q8B = specB.addState();
        State q9B = specB.addState();
        State q10B = specB.addState();
        State q11B = specB.addState();

        specB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        specB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        specB.addTransition(q2B, q3B, new CharTransitionLabel('c'));
        specB.addTransition(q3B, q4B, new CharTransitionLabel('d'));
        specB.addTransition(q4B, q5B, new CharTransitionLabel('e'));
        specB.addLoop(q5B, new CharTransitionLabel('f'));
        specB.addTransition(q3B, q6B, new CharTransitionLabel('d'));
        specB.addTransition(q6B, q7B, new CharTransitionLabel('e'));
        specB.addTransition(q7B, q8B, new CharTransitionLabel('f'));
        specB.addTransition(q7B, q10B, new CharTransitionLabel('f'));
        specB.addLoop(q10B, new CharTransitionLabel('g'));
        specB.addTransition(q8B, q9B, new CharTransitionLabel('g'));
        specB.addTransition(q10B, q11B, new CharTransitionLabel('h'));

        specB.markAsInitial(q0B);
        specB.markAsFinal(q11B);

        AutomatonByRecursion automatonB = new AutomatonByRecursion(specB);

        assertTrue(automatonB.accepts("abcdefgh"));
        assertTrue(automatonB.accepts("abcdefgggh"));
        assertFalse(automatonB.accepts("abcdefgh "));
        assertTrue(automatonB.accepts("abcdefgh"));
        assertFalse(automatonB.accepts("abcde fgh"));
        assertTrue(automatonB.accepts("abcdefh"));
        assertFalse(automatonB.accepts("abc defgh"));
    }
}
