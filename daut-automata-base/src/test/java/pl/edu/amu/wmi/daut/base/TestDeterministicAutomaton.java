package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 *
 * @author szaku.
 */
public class TestDeterministicAutomaton extends TestCase {

    /**SimpleDeterministicAutomatonTest, prosty test.
     * 
     */
    public final void testSimpleDeterministicAutomaton() {
        DeterministicAutomatonSpecification stest = new NaiveDeterministicAutomatonSpecification();
        State q1 = stest.addState();
        State q2 = stest.addState();
        stest.addTransition(q1, q2, new CharTransitionLabel('a'));
        stest.addLoop(q2, new CharTransitionLabel('a'));
        stest.addLoop(q1, new CharTransitionLabel('b'));
        stest.markAsInitial(q1);
        stest.markAsFinal(q2);
        DeterministicAutomaton satest = new DeterministicAutomaton(stest);
        assertTrue(satest.accepts("a"));
        assertFalse(satest.accepts("b"));
        assertFalse(satest.accepts(""));
        assertFalse(satest.accepts("abx"));
        assertTrue(satest.accepts("aaaa"));
        assertTrue(satest.accepts("baaaa"));
        String s = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + 'a';
        }
        assertTrue(satest.accepts(s));
        String a = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + "ab";
        }
        assertFalse(satest.accepts(a));
    }

    /** OneInitialFinalStateTest, ze stanem, ktory jest poczatkowym i akceptujacym.
     * 
     */
    public final void testOneInitialFinalState() {
        DeterministicAutomatonSpecification onestate = new NaiveDeterministicAutomatonSpecification();
        State q1 = onestate.addState();
        State q2 = onestate.addState();
        onestate.addTransition(q1, q2, new CharTransitionLabel('a'));
        onestate.addTransition(q1, q2, new CharTransitionLabel('b'));
        onestate.addTransition(q2, q1, new CharTransitionLabel('a'));
        onestate.addLoop(q2, new CharTransitionLabel('b'));
        onestate.markAsInitial(q1);
        onestate.markAsFinal(q1);
        DeterministicAutomaton ostest = new DeterministicAutomaton(onestate);
        assertTrue(ostest.accepts("aa"));
        assertFalse(ostest.accepts("aaa"));
        assertFalse(ostest.accepts("a"));
        assertFalse(ostest.accepts("baa"));
    }

    /** testDeterministicAutomatonParityCheck, automat z wykladu, a i b sa parzyste.
     * 
     */
    public final void testDeterministicAutomatonParityCheck() {
        DeterministicAutomatonSpecification pcheck = new NaiveDeterministicAutomatonSpecification();
        State qpp = pcheck.addState();
        State qnp = pcheck.addState();
        State qnn = pcheck.addState();
        State qpn = pcheck.addState();
        pcheck.addTransition(qpp, qnp, new CharTransitionLabel('a'));
        pcheck.addTransition(qnp, qpp, new CharTransitionLabel('a'));
        pcheck.addTransition(qnp, qnn, new CharTransitionLabel('b'));
        pcheck.addTransition(qnn, qnp, new CharTransitionLabel('b'));
        pcheck.addTransition(qnn, qpn, new CharTransitionLabel('a'));
        pcheck.addTransition(qpn, qnn, new CharTransitionLabel('a'));
        pcheck.addTransition(qpp, qpn, new CharTransitionLabel('b'));
        pcheck.addTransition(qpn, qpp, new CharTransitionLabel('b'));
        pcheck.markAsInitial(qpp);
        pcheck.markAsFinal(qpp);
        DeterministicAutomaton pctest = new DeterministicAutomaton(pcheck);
        assertTrue(pctest.accepts("aabb"));
        assertFalse(pctest.accepts("aab"));
        assertTrue(pctest.accepts("aa"));
        assertTrue(pctest.accepts("bb"));
        assertFalse(pctest.accepts("abb"));
        assertFalse(pctest.accepts("vabb"));
        assertFalse(pctest.accepts("aabbh"));
    }
}

