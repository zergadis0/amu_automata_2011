package pl.edu.amu.wmi.daut.base;

import java.lang.Object;
import junit.framework.TestCase;
import java.util.List;

public class TestAutomataOperations extends TestCase {

    public final void testSimpleAutomaton(){

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1 ,new CharTransitionLabel('a') );
        automatonA.addLoop(q1, new CharTransitionLabel('b') );
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

    public final void testSum() {
        
        /*Automat A */
        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        {
            State q0 = automatonA.addState();
            State q1 = automatonA.addState();

            automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('a'));
            automatonA.addLoop(q1, new CharTransitionLabel('b'));
            automatonA.markAsInitial(q0);
            automatonA.markAsFinal(q1);
        }

        /*Automat B*/
        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
        {
            State q0 = automatonB.addState();
            State q1 = automatonB.addState();
            State q2 = automatonB.addState();
            automatonB.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonB.addTransition(q0, q1, new CharTransitionLabel('b'));
            automatonB.addTransition(q1, q2, new CharTransitionLabel('a'));
            automatonB.addTransition(q1, q2, new CharTransitionLabel('b'));
            automatonB.markAsInitial(q0);
            automatonB.markAsFinal(q2);
        }
        /*Automat C */
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
        {
            State q0 = automatonC.addState();

            automatonC.addLoop(q0, new CharTransitionLabel('a'));
            automatonC.addLoop(q0, new CharTransitionLabel('b'));
            automatonC.addLoop(q0, new CharTransitionLabel('c'));
            automatonC.addLoop(q0, new CharTransitionLabel('d'));
            automatonC.markAsInitial(q0);
            automatonC.markAsFinal(q0);
        }
        /* Automat D */
        AutomatonSpecification automatonD = new NaiveAutomatonSpecification();
        {
            State q0 = automatonD.addState();
            State q1 = automatonD.addState();
            State q2 = automatonD.addState();
            State q3 = automatonD.addState();

            automatonD.addTransition(q0, q1, new CharTransitionLabel('a'));
            automatonD.addTransition(q0, q2, new CharTransitionLabel('b'));
            automatonD.addTransition(q1, q3, new CharTransitionLabel('a'));
            automatonD.addTransition(q1, q2, new CharTransitionLabel('b'));
            automatonD.addTransition(q2, q0, new CharTransitionLabel('c'));
            automatonD.addTransition(q2, q1, new CharTransitionLabel('b'));
            automatonD.addTransition(q2, q3, new CharTransitionLabel('a'));
            automatonD.addTransition(q3, q2, new CharTransitionLabel('c'));
            automatonD.addTransition(q3, q0, new CharTransitionLabel('b'));

            automatonD.markAsInitial(q0);
            automatonD.markAsFinal(q3);
        }
        /* Test A z B */
        { 
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
        }
        /*Test D z B */
        {
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonD);
            AutomatonByRecursion automaton = new AutomatonByRecursion(result);

            assertTrue(automaton.accepts("ab"));
            assertTrue(automaton.accepts("abbabba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aacacaca"));
            assertTrue(automaton.accepts("aa"));
            assertFalse(automaton.accepts("zle"));
            assertFalse(automaton.accepts("b"));
            assertFalse(automaton.accepts(" "));
            assertFalse(automaton.accepts("aac"));
        }
        /*Test B z C */
        {
            AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonC);
            AutomatonByRecursion automaton = new AutomatonByRecursion(result);
        

            assertTrue(automaton.accepts("babbaccddcaaccb"));
            assertTrue(automaton.accepts("bbaccddbaba"));
            assertTrue(automaton.accepts("bbbcaacba"));
            assertTrue(automaton.accepts("aaaaaaaaaaaaaaaa"));
            assertFalse(automaton.accepts("bbaccddxbaba"));
            assertFalse(automaton.accepts("CzyTwojProgramMackuToZaakceptuje"));
            assertFalse(automaton.accepts(" "));
            assertFalse(automaton.accepts("zielonosmutnaniebieskowesolapomaranczowa"));
        }
    }
}

