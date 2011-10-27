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
        
            AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(AutomataOperations.metodaKarola(pustyOjciec));
        
            assertTrue(pusteDziecko.accepts("a"));
            assertTrue(pusteDziecko.accepts("abba"));
            assertTrue(pusteDziecko.accepts(""));
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
        
            AutomatonByRecursion autLucasBR = new AutomatonByRecursion(AutomataOperations.metodaKarola(autLucas));
            
            assertFalse(autLucasBR.accepts(""));
            assertFalse(autLucasBR.accepts("a"));
            assertFalse(autLucasBR.accepts("aa"));
            assertTrue(autLucasBR.accepts("aaa"));
        }
        //TEST 3 Automat akceptujacy tylko slowa "ab" i "ba".
        {
            AutomatonSpecification abba = new NaiveAutomatonSpecification();
            
            State q0 = abba.addState();
            State qa = abba.addTransition(q0, new TestTransition('a'));
            State qb = abba.addTransition(q0, new TestTransition('b'));
            State qab = abba.addTransition(qa, new TestTransition('b'));
            State qba = abba.addTransition(qb, new TestTransition('a'));
            State smietnik = abba.addTransition(qa, new TestTransition('a'));
            abba.addTransition(qb, smietnik, new TestTransition('b'));
            abba.addTransition(qab, smietnik, new TestTransition('a'));
            abba.addTransition(qab, smietnik, new TestTransition('b'));
            abba.addTransition(qba, smietnik, new TestTransition('a'));
            abba.addTransition(qba, smietnik, new TestTransition('b'));
            abba.addLoop(smietnik, new TestTransition('a'));
            abba.addLoop(smietnik, new TestTransition('b'));
            abba.markAsInitial(q0);
            abba.markAsFinal(qab);
            abba.markAsFinal(qba);
            
            AutomatonByRecursion abbaBR = new AutomatonByRecursion(AutomataOperations.metodaKarola(abba));
            
            assertTrue(abbaBR.accepts(""));
            assertTrue(abbaBR.accepts("a"));
            assertTrue(abbaBR.accepts("b"));
            assertFalse(abbaBR.accepts("ab"));
            assertFlase(abbaBR.accepts("ba"));
            assertTrue(abbaBR.accepts("aa"));
            assertTrue(abbaBR.accepts("bb"));
            assertTrue(abbaBR.accepts("aba"));
            assertTrue(abbaBR.accepts("bab"));
            assertTrue(abbaBR.accepts("abb"));
            assertTrue(abbaBR.accepts("baa"));
        }
    }
}
