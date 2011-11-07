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
    /** Test sprawdza metode Sum w AutomataOperations.  */
    public final void testSum() {
        /*Automat A */
        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

            State q0 = automatonA.addState();
            State q1 = automatonA.addState();

            automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('b'));
            automatonA.markAsInitial(q0);
            automatonA.markAsFinal(q1);

        /*Automat B*/
        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
            State q0B = automatonB.addState();
            State q1B = automatonB.addState();
            State q2B = automatonB.addState();
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
            automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
            automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0B);
            automatonB.markAsFinal(q2B);
        /*Automat C */
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
            State q0C = automatonC.addState();
            automatonC.addLoop(q0C, new CharTransitionLabel('a'));
            automatonC.addLoop(q0C, new CharTransitionLabel('b'));
            automatonC.addLoop(q0C, new CharTransitionLabel('c'));
            automatonC.addLoop(q0C, new CharTransitionLabel('d'));
            automatonC.markAsInitial(q0C);
            automatonC.markAsFinal(q0C);
        /* Automat D */
        AutomatonSpecification automatonD = new NaiveAutomatonSpecification();
            State q0D = automatonD.addState();
            State q1D = automatonD.addState();
            State q2D = automatonD.addState();
            State q3D = automatonD.addState();
            automatonD.addTransition(q0D, q1D, new CharTransitionLabel('a'));
            automatonD.addTransition(q0D, q2D, new CharTransitionLabel('b'));
            automatonD.addTransition(q1D, q3D, new CharTransitionLabel('a'));
            automatonD.addTransition(q1D, q2D, new CharTransitionLabel('b'));
            automatonD.addTransition(q2D, q0D, new CharTransitionLabel('c'));
            automatonD.addTransition(q2D, q1D, new CharTransitionLabel('b'));
            automatonD.addTransition(q2D, q3D, new CharTransitionLabel('a'));
            automatonD.addTransition(q3D, q2D, new CharTransitionLabel('c'));
            automatonD.addTransition(q3D, q0D, new CharTransitionLabel('b'));
            automatonD.markAsInitial(q0D);
            automatonD.markAsFinal(q3D);
            /*Automat E*/
            AutomatonSpecification automatonE = new NaiveAutomatonSpecification();
            State q0E = automatonE.addState();
            automatonE.addTransition(q0E, q0E, new EpsilonTransitionLabel());
            automatonE.markAsInitial(q0E);
            automatonE.markAsFinal(q0E);
        /* Test A z B */
            AutomatonSpecification result = AutomataOperations.sum(automatonA, automatonB);
            AutomatonByRecursion automaton = new AutomatonByRecursion(result);
            assertTrue(automaton.accepts("aa"));
            assertTrue(automaton.accepts("ba"));
            assertTrue(automaton.accepts("aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaa"));
            assertTrue(automaton.accepts("bb"));
            assertTrue(automaton.accepts("abbbbabbbabbb"));
            assertFalse(automaton.accepts("bbb"));
            assertFalse(automaton.accepts("Tegomaniezakceptowac"));
            assertFalse(automaton.accepts("baaaaaaaaaa"));
            assertFalse(automaton.accepts("aaaaaaaaaaaaaaaxaaaaaa"));
            assertFalse(automaton.accepts("bab"));
        /*Test D z B */
            result = AutomataOperations.sum(automatonB, automatonD);
            automaton = new AutomatonByRecursion(result);
            assertTrue(automaton.accepts("ab"));
            assertTrue(automaton.accepts("abbabba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aacacaca"));
            assertTrue(automaton.accepts("aa"));
            assertFalse(automaton.accepts("zle"));
            assertFalse(automaton.accepts("b"));
            assertFalse(automaton.accepts(""));
            assertFalse(automaton.accepts("aac"));
        /*Test B z C */
            result = AutomataOperations.sum(automatonB, automatonC);
            automaton = new AutomatonByRecursion(result);
            assertTrue(automaton.accepts("babbaccddcaaccb"));
            assertTrue(automaton.accepts("bbaccddbaba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aaaaaaaaaaaaaaaa"));
            assertFalse(automaton.accepts("bbaccddxbaba"));
            assertFalse(automaton.accepts("CzyTwojProgramMackuToZaakceptuje"));
            assertFalse(automaton.accepts(""));
            assertFalse(automaton.accepts("zielonosmutnaniebieskowesolapomaranczowa"));
           /*Test B z C */
            result = AutomataOperations.sum(automatonB, automatonE);
            automaton = new AutomatonByRecursion(result);
            assertTrue(automaton.accepts(""));
            assertTrue(automaton.accepts("aa"));
            assertFalse(automaton.accepts("bbaccddxbaba"));
            assertFalse(automaton.accepts("aabbbaaaa"));
    }
}