package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
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

    /**
     * Test complementLanguageAutomaton() dla automatu "pustego".
     */
    public final void testComplementLanguageAutomaton0EmptyAutomaton() {
        DeterministicAutomatonSpecification pustyOjciec = new
                NaiveDeterministicAutomatonSpecification();

        State q0 = pustyOjciec.addState();
        pustyOjciec.addLoop(q0, new CharTransitionLabel('a'));
        pustyOjciec.markAsInitial(q0);
        HashSet<Character> zbior = new HashSet<Character>();
        zbior.add('a');
        zbior.add('b');
        zbior.add('c');

        AutomatonByRecursion pusteDziecko = new
         AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(pustyOjciec, zbior));

        assertEquals(AutomataOperations.complementLanguageAutomaton(pustyOjciec, zbior), 2);
        assertTrue(pusteDziecko.accepts("a"));
        assertTrue(pusteDziecko.accepts("abba"));
        assertTrue(pusteDziecko.accepts(""));
        assertTrue(pusteDziecko.accepts("cc"));
        assertFalse(pusteDziecko.accepts("dd"));
    }

    /**
     * Test complementLanguageAutomaton() dla automatu akceptującego dziwne "a".
     */
    public final void testComplementLanguageAutomaton1StrangeAAutomaton() {
        DeterministicAutomatonSpecification autLucas = new
                NaiveDeterministicAutomatonSpecification();

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
        HashSet<Character> zbior = new HashSet<Character>();
        zbior.add('a');

        AutomatonByRecursion autLucasBR = new
            AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(autLucas, zbior));

        assertEquals(AutomataOperations.complementLanguageAutomaton(autLucas, zbior), 4);
        assertTrue(autLucasBR.accepts(""));
        assertTrue(autLucasBR.accepts("a"));
        assertTrue(autLucasBR.accepts("aaaa"));
        assertFalse(autLucasBR.accepts("aa"));
        assertFalse(autLucasBR.accepts("aaa"));
    }

    /**
     * Test complementLanguageAutomaton() dla automatu akceptującego "ab" i "ba".
     */
    public final void testComplementLanguageAutomaton2AbBaAutomaton() {
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
        HashSet<Character> zbior = new HashSet<Character>();
        zbior.add('a');
        zbior.add('b');

        AutomatonByRecursion abbaBR = new
                AutomatonByRecursion(AutomataOperations.complementLanguageAutomaton(abba, zbior));

        assertEquals(AutomataOperations.complementLanguageAutomaton(abba, zbior), 6);
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
