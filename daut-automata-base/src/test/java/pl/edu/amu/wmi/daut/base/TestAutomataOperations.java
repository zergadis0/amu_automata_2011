package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Testy różnych operacji na automatach.
*/
public class TestAutomataOperations extends TestCase {

    /**
* Test prostego automatu.
*/
    public final void testSimpleAutomaton() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        State q12 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q12);


        // proszę odkomentować, kiedy AutomataOperations.intersection
        // będzie gotowe!!!
        // AutomatonSpecification Result = AutomataOperations.intersection(automatonA, automatonB);
        // AutomatonByRecursion automaton = AutomatonByRecursion(Result);

        // assertTrue(automaton.accepts("aa"));
        // assertTrue(automaton.accepts("ab"));
        // assertFalse(automaton.accepts(""));
        // assertFalse(automaton.accepts("a"));

    }
    /** Test sprawdza metode Sum w AutomataOperations. */
    public final void testSumAB() {
        /*Automat A */
            AutomatonSpecification automatonA = new NaiveAutomatonSpecification();
            State q0 = automatonA.addState();
            State q1 = automatonA.addState();
            automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonA.addTransition(q0, q1, new CharTransitionLabel('b'));
            automatonA.markAsInitial(q0);
            automatonA.markAsFinal(q1);
            /*Automat B*/
            AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q01 = automatonB.addState();
            State q11 = automatonB.addState();
            State q21 = automatonB.addState();
            automatonB.addTransition(q01, q11, new CharTransitionLabel('a'));
            automatonB.addTransition(q01, q11, new CharTransitionLabel('b'));
            automatonB.addTransition(q11, q21, new CharTransitionLabel('a'));
            automatonB.addTransition(q11, q21, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q01);
            automatonB.markAsFinal(q21);
            
            AutomatonSpecification result = AutomataOperations.sum(automatonA, automatonB);
            NondeterministicAutomatonByThompsonApproach automaton = new NondeterministicAutomatonByThompsonApproach(result);
            assertTrue(automaton.accepts("a"));
            assertTrue(automaton.accepts("ba"));
            assertTrue(automaton.accepts("bb"));
            assertFalse(automaton.accepts("bbb"));
            assertFalse(automaton.accepts("aaaaaaaaaaaaaaaxaaaaaa"));
            assertFalse(automaton.accepts("bab"));
     }
}

