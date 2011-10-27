package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Testy klasy AutomataOperations.
 */
public class TestAutomataOperations extends TestCase {
    
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
    
    public final void testMetodaKarola() {
        //TEST 1 Język pusty
        {
            AutomatonSpecification pustyOjciec = new NaiveAutomatonSpecification();
        
            State q0 = pustyOjciec.addState();
            pustyOjciec.addLoop(q0, new TestTransition('a'));
            pustyOjciec.addLoop(q0, new TestTransition('b'));
            pustyOjciec.markAsInitial(q0);
        
            AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(pustyOjciec);
        
            assertTrue(AutomataOperations.metodaKarola(pusteDziecko).accepts("a"));
            assertTrue(AutomataOperations.metodaKarola(pusteDziecko).accepts("abba"));
            assertTrue(AutomataOperations.metodaKarola(pusteDziecko).accepts(""));
        }
        //TEST 2 Automat dziwne a
        {
            AutomatonSpecification autLucas = new NaiveAutomatonSpecification();
            
            State q0 = autLucas.addState();
            State q1 = autLucas.addState(); 
            State q2 = autLucas.addState(); 
            State q3 = autLucas.addState();
            autLucas.addTransition(q0, q1, new TestTransition('a'));
            autLucas.addTransition(q1, q2, new TestTransition('a'));
            autLucas.addTransition(q2, q3, new TestTransition('a'));
            autLucas.addTransition(q3, q1, new TestTransition('a'));
            autLucas.markAsInitial(q0);
            autLucas.markAsFinal(q2);
            autLucas.markAsFinal(q3);
        
            AutomatonByRecursion autLucasBR = new AutomatonByRecursion(autLucas);
            
            assertFalse(AutomataOperations.metodaKarola(autLucasBR).accepts(""));
            assertFalse(AutomataOperations.metodaKarola(autLucasBR).accepts("a"));
            assertFalse(AutomataOperations.metodaKarola(autLucasBR).accepts("aa"));
            assertTrue(AutomataOperations.metodaKarola(autLucasBR).accepts("aaa"));
        }
    }
}
