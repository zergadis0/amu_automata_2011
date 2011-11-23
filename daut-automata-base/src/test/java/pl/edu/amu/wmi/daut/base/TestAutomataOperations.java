package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Testy różnych operacji na automatach.
*/
public class TestAutomataOperations extends TestCase {

    /**
* Test metody intersection z AutomataOperations na automatach z petlami.
*/
    public final void testIntersectionSimple() {

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


        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);
        AutomatonByRecursion automaton = new AutomatonByRecursion(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("ab"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("Blablablabla bla"));
        assertFalse(automaton.accepts("abababab"));
        assertFalse(automaton.accepts("aba"));
        assertFalse(automaton.accepts("ba"));
    }

    /**
    *drugi test.
    */

    public final void testIntersection2() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addTransition(q0, q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addLoop(q11, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q11);


        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);
        AutomatonByRecursion automaton = new AutomatonByRecursion(result);

        assertTrue(automaton.accepts("a"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("baba"));
        assertFalse(automaton.accepts("dziwne dlugie slowo"));
    }

    /**
     * Test metody intersection z AutomataOperations na automacie A z soba samym.
     */
    public final void testIntersectionWithTheSameAutomaton() {
        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        State q2 = automatonA.addState();
        automatonA.addTransition(q0, q2, new CharTransitionLabel('b'));
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addTransition(q1, q2, new CharTransitionLabel('a'));
        automatonA.addLoop(q2, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q2);

        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonA);
        AutomatonByRecursion automaton = new AutomatonByRecursion(result);

        assertTrue(automaton.accepts("aabb"));
        assertTrue(automaton.accepts("bb"));
        assertTrue(automaton.accepts("bbbbbbbbbbbbbbbbb"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaa"));
        assertFalse(automaton.accepts("xxxxaabbxxxxxx"));
        assertFalse(automaton.accepts(""));

    }

     /**
     * Test metody intersection z AutomataOperations na automatach z epsilon przejsciami.
     */
    public final void testIntersectionwithEpsilonTransition() {
        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        State q2 = automatonA.addState();

        automatonA.addTransition(q0, q1, new EpsilonTransitionLabel());
        automatonA.addTransition(q0, q2, new EpsilonTransitionLabel());
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q2, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q0);
        automatonA.markAsFinal(q1);
        automatonA.markAsFinal(q2);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        State q12 = automatonB.addState();
        State q13 = automatonB.addState();
        State q14 = automatonB.addState();
        State q15 = automatonB.addState();
        State q16 = automatonB.addState();

        automatonB.addTransition(q10, q11, new EpsilonTransitionLabel());
        automatonB.addTransition(q10, q14, new EpsilonTransitionLabel());
        automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonB.addTransition(q12, q13, new CharTransitionLabel('b'));
        automatonB.addLoop(q13, new CharTransitionLabel('b'));
        automatonB.addTransition(q14, q15, new CharTransitionLabel('b'));
        automatonB.addTransition(q15, q16, new CharTransitionLabel('b'));
        automatonB.addLoop(q16, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q12);
        automatonB.markAsFinal(q13);
        automatonB.markAsFinal(q15);

        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts("a"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaa"));
        assertFalse(automaton.accepts("bbbbbbbbbbbbbbbbbb"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("abbb"));
        assertFalse(automaton.accepts("nieakceptowanedlugieslowo"));



    }

    /**
     * Test metody intersection z AutomataOperations gdzie automat A 
     * ma epsilon przejscia i stan koncowy jest poczatkowym.
     */
    public final void testIntersectionwhereInitialIsFinalA() {
        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        automatonA.addLoop(q0, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q0);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addTransition(q10, q11, new EpsilonTransitionLabel());
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q10);
        automatonB.markAsFinal(q11);

        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("bbbbbbbbbbbbbb"));
        assertFalse(automaton.accepts("bardzodlugieslowowbardzodlugieslowo"));

    }

    /**
     * Test metody intersection z AutomataOperations gdzie automat B 
     * ma epsilon przejscia i stan koncowy jest poczatkowym.
     */
    public final void testIntersectionwhereInitialisFinalB() {


        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q10 = automatonA.addState();
        State q11 = automatonA.addState();
        automatonA.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonA.addTransition(q10, q11, new EpsilonTransitionLabel());
        automatonA.markAsInitial(q10);
        automatonA.markAsFinal(q10);
        automatonA.markAsFinal(q11);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0 = automatonB.addState();
        automatonB.addLoop(q0, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0);
        automatonB.markAsFinal(q0);

        AutomatonSpecification result = AutomataOperations.intersection(automatonA, automatonB);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("bbbbbbbbbbbbbb"));
        assertFalse(automaton.accepts("bardzodlugieslowowbardzodlugieslowo"));

    }




}
