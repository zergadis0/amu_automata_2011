package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy AutomatonByRecursion.
 */
public class TestAutomatonByRecursion extends TestCase {

    /**
     * Prosta etykieta przejścia dla celów testowych.
     *
     * FIXME Powielony kod z TestNaiveAutomatonSpecification -
     * uporządkować po zrobieniu zadania #104.
     */
    private static class TestTransition extends TransitionLabel {
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

        public boolean isEmpty() {
            return false;
        }

        public char getChar() {
            return ch_;
        }

        protected TransitionLabel intersectWith(TransitionLabel label) {
            return label.canAcceptCharacter(ch_) ? this : new EmptyTransitionLabel();
        }

        private char ch_;
    }

    public final void testSimpleAutomaton() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();

        spec.addTransition(q0, q1, new TestTransition('a'));
        spec.addLoop(q1, new TestTransition('a'));
        spec.addLoop(q1, new TestTransition('b'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q1);

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);

        assertTrue(automaton.accepts("ab"));
        assertFalse(automaton.accepts("bb"));
    }

    public final void testStaticIsEvilUnlessYouKnowTheReasonTheyAre() {
        AutomatonSpecification specA = new NaiveAutomatonSpecification();
        State q0A = specA.addState();
        State q1A = specA.addState();
        specA.addTransition(q0A, q1A, new TestTransition('a'));
        specA.markAsInitial(q0A);
        specA.markAsFinal(q1A);
        AutomatonByRecursion autA = new AutomatonByRecursion(specA);

        AutomatonSpecification specB = new NaiveAutomatonSpecification();
        State q0B = specB.addState();
        State q1B = specB.addState();
        specB.addTransition(q0B, q1B, new TestTransition('b'));
        specB.markAsInitial(q0B);
        specB.markAsFinal(q1B);
        AutomatonByRecursion autB = new AutomatonByRecursion(specB);

        assertTrue(autA.accepts("a")); 
        assertTrue(autB.accepts("b")); 
    }
}

