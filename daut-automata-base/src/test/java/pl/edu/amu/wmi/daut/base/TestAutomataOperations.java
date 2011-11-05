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

    public final void testComplementLanguageAutomaton_EmptyAutomaton() {
        DeterministicAutomatonSpecification pustyOjciec = new
                NaiveDeterministicAutomatonSpecification();

        State q0 = pustyOjciec.addState();
        pustyOjciec.addLoop(q0, new CharTransitionLabel('a'));
        pustyOjciec.addLoop(q0, new CharTransitionLabel('b'));
        pustyOjciec.markAsInitial(q0);

        AutomatonByRecursion pusteDziecko = new
                AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(pustyOjciec));

        assertTrue(pusteDziecko.accepts("a"));
        assertTrue(pusteDziecko.accepts("abba"));
        assertTrue(pusteDziecko.accepts(""));
        assertFalse(pusteDziecko.accepts("cc"));
    }
    
    public final void testComplementLanguageAutomaton_StrangeAAutomaton() {
        DeterministicAutomatonSpecification autLucas = new NaiveDeterministicAutomatonSpecification();

        State q0 = autLucas.addState();
        State q1 = autLucas.addState(); 
        State q2 = autLucas.addState(); 
        State q3 = autLucas.addState();
        autLucas.addTransition(q0, q1, new CharTransitionLabel('a'));
        autLucas.addTransition(q1, q2, new CharTransitionLabel('a'));
        autLucas.addTransition(q2, q3, new CharTransitionLabel('a'));
        autLucas.addTransition(q3, q1, new CharTransitionLabel('a'));
        autLucas.markAsInitial(q0);
        autLucas.markAsFinal(q2);
        autLucas.markAsFinal(q3);

        AutomatonByRecursion autLucasBR = new
                AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(autLucas));

        assertFalse(autLucasBR.accepts(""));
        assertFalse(autLucasBR.accepts("a"));
        assertFalse(autLucasBR.accepts("aa"));
        assertTrue(autLucasBR.accepts("aaa"));
    }
    
    public final void testComplementLanguageAutomaton_AbBaAutomaton() {
        DeterministicAutomatonSpecification abba = new NaiveDeterministicAutomatonSpecification();

        State q0 = abba.addState();
        State qa = abba.addTransition(q0, new CharTransitionLabel('a'));
        State qb = abba.addTransition(q0, new CharTransitionLabel('b'));
        State qab = abba.addTransition(qa, new CharTransitionLabel('b'));
        State qba = abba.addTransition(qb, new CharTransitionLabel('a'));
        State smietnik = abba.addTransition(qa, new CharTransitionLabel('a'));
        abba.addTransition(qb, smietnik, new CharTransitionLabel('b'));
        abba.addTransition(qab, smietnik, new CharTransitionLabel('a'));
        abba.addTransition(qab, smietnik, new CharTransitionLabel('b'));
        abba.addTransition(qba, smietnik, new CharTransitionLabel('a'));
        abba.addTransition(qba, smietnik, new CharTransitionLabel('b'));
        abba.addLoop(smietnik, new CharTransitionLabel('a'));
        abba.addLoop(smietnik, new CharTransitionLabel('b'));
        abba.markAsInitial(q0);
        abba.markAsFinal(qab);
        abba.markAsFinal(qba);

        AutomatonByRecursion abbaBR = new
                AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(abba));

        assertTrue(abbaBR.accepts(""));
        assertTrue(abbaBR.accepts("a"));
        assertTrue(abbaBR.accepts("b"));
        assertFalse(abbaBR.accepts("ab"));
        assertFalse(abbaBR.accepts("ba"));
        assertTrue(abbaBR.accepts("aa"));
        assertTrue(abbaBR.accepts("bb"));
        assertTrue(abbaBR.accepts("aba"));
        assertTrue(abbaBR.accepts("bab"));	
        assertTrue(abbaBR.accepts("abb"));
        assertTrue(abbaBR.accepts("baa"));
    }
}
