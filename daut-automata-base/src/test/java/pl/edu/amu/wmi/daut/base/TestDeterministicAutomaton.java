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
        DeterministicAutomatonSpecification simpleTest = new NaiveDeterministicAutomatonSpecification();
        State q1 = simpleTest.addState();
        State q2 = simpleTest.addState();
        simpleTest.addTransition(q1, q2, new CharTransitionLabel('a'));
        simpleTest.addLoop(q2, new CharTransitionLabel('a'));
        simpleTest.addLoop(q1, new CharTransitionLabel('b'));
        simpleTest.markAsInitial(q1);
        simpleTest.markAsFinal(q2);
        DeterministicAutomaton simpleAutomatonTest = new DeterministicAutomaton(simpleTest);
        assertTrue(simpleAutomatonTest.accepts("a"));
        assertFalse(simpleAutomatonTest.accepts("b"));
        assertFalse(simpleAutomatonTest.accepts(""));
        assertFalse(simpleAutomatonTest.accepts("abx"));
        assertTrue(simpleAutomatonTest.accepts("aaaa"));
        assertTrue(simpleAutomatonTest.accepts("baaaa"));
        String s = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + 'a';
        }
        assertTrue(simpleAutomatonTest.accepts(s));
        String a = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + "ab";
        }
        assertFalse(simpleAutomatonTest.accepts(a));
    }

    /** OneInitialFinalStateTest, ze stanem, ktory jest poczatkowym i akceptujacym.
     * 
     */
    public final void testOneInitialFinalState() {
        DeterministicAutomatonSpecification OneFinalInitialState = new NaiveDeterministicAutomatonSpecification();
        State q1 = OneFinalInitialState.addState();
        State q2 = OneFinalInitialState.addState();
        OneFinalInitialState.addTransition(q1, q2, new CharTransitionLabel('a'));
        OneFinalInitialState.addTransition(q1, q2, new CharTransitionLabel('b'));
        OneFinalInitialState.addTransition(q2, q1, new CharTransitionLabel('a'));
        OneFinalInitialState.addLoop(q2, new CharTransitionLabel('b'));
        OneFinalInitialState.markAsInitial(q1);
        OneFinalInitialState.markAsFinal(q1);
        DeterministicAutomaton OneFinalInitialStateTest = new DeterministicAutomaton(OneFinalInitialState);
        assertTrue(OneFinalInitialStateTest.accepts("aa"));
        assertFalse(OneFinalInitialStateTest.accepts("aaa"));
        assertFalse(OneFinalInitialStateTest.accepts("a"));
        assertFalse(OneFinalInitialStateTest.accepts("baa"));
    }

    /** testDeterministicAutomatonParityCheck, automat z wykladu, a i b sa parzyste.
     * 
     */
    public final void testDeterministicAutomatonParityCheck() {
        DeterministicAutomatonSpecification DeterministicAutomatonParityCheck = new NaiveDeterministicAutomatonSpecification();
        State qpp = DeterministicAutomatonParityCheck.addState();
        State qnp = DeterministicAutomatonParityCheck.addState();
        State qnn = DeterministicAutomatonParityCheck.addState();
        State qpn = DeterministicAutomatonParityCheck.addState();
        DeterministicAutomatonParityCheck.addTransition(qpp, qnp, new CharTransitionLabel('a'));
        DeterministicAutomatonParityCheck.addTransition(qnp, qpp, new CharTransitionLabel('a'));
        DeterministicAutomatonParityCheck.addTransition(qnp, qnn, new CharTransitionLabel('b'));
        DeterministicAutomatonParityCheck.addTransition(qnn, qnp, new CharTransitionLabel('b'));
        DeterministicAutomatonParityCheck.addTransition(qnn, qpn, new CharTransitionLabel('a'));
        DeterministicAutomatonParityCheck.addTransition(qpn, qnn, new CharTransitionLabel('a'));
        DeterministicAutomatonParityCheck.addTransition(qpp, qpn, new CharTransitionLabel('b'));
        DeterministicAutomatonParityCheck.addTransition(qpn, qpp, new CharTransitionLabel('b'));
        DeterministicAutomatonParityCheck.markAsInitial(qpp);
        DeterministicAutomatonParityCheck.markAsFinal(qpp);
        DeterministicAutomaton DeterministicAutomatonParityCheckTest = new DeterministicAutomaton(DeterministicAutomatonParityCheck);
        assertTrue(DeterministicAutomatonParityCheckTest.accepts("aabb"));
        assertFalse(DeterministicAutomatonParityCheckTest.accepts("aab"));
        assertTrue(DeterministicAutomatonParityCheckTest.accepts("aa"));
        assertTrue(DeterministicAutomatonParityCheckTest.accepts("bb"));
        assertFalse(DeterministicAutomatonParityCheckTest.accepts("abb"));
        assertFalse(DeterministicAutomatonParityCheckTest.accepts("vabb"));
        assertFalse(DeterministicAutomatonParityCheckTest.accepts("aabbh"));
    }
}

