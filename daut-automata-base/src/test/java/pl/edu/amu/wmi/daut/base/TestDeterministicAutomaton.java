package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 *
 * @author szaku.
 */
public class TestDeterministicAutomaton extends TestCase {

    /**test 1.
     * 
     */
    public final void test1() {
        DeterministicAutomatonSpecification test = new NaiveDeterministicAutomatonSpecification();
        State q1 = test.addState();
        State q2 = test.addState();
        test.addTransition(q1, q2, new CharTransitionLabel('a'));
        test.addLoop(q2, new CharTransitionLabel('a'));
        test.addLoop(q1, new CharTransitionLabel('b'));
        test.markAsInitial(q1);
        test.markAsFinal(q2);
        DeterministicAutomaton test1 = new DeterministicAutomaton(test);
        assertTrue(test1.accepts("a"));
        assertFalse(test1.accepts("b"));
        assertFalse(test1.accepts(""));
        assertTrue(test1.accepts("aaaa"));
        assertTrue(test1.accepts("baaaa"));
        String s = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + 'a';
        }
        assertTrue(test1.accepts(s));
        String a = new String();
        for (int i = 1; i < 1000; i++) {
            s = s + "ab";
        }
        assertFalse(test1.accepts(a));
    }

    /** test 2.
     * 
     */
    public final void test2() {
        DeterministicAutomatonSpecification test = new NaiveDeterministicAutomatonSpecification();
        State q1 = test.addState();
        State q2 = test.addState();
        test.addTransition(q1, q2, new CharTransitionLabel('a'));
        test.addTransition(q1, q2, new CharTransitionLabel('b'));
        test.addTransition(q2, q1, new CharTransitionLabel('a'));
        test.addLoop(q2, new CharTransitionLabel('b'));
        test.markAsInitial(q1);
        test.markAsFinal(q1);
        DeterministicAutomaton test1 = new DeterministicAutomaton(test);
        assertTrue(test1.accepts("aa"));
        assertFalse(test1.accepts("aaa"));
        assertFalse(test1.accepts("a"));
        assertFalse(test1.accepts("baa"));
    }

    /** test 3, automat z wykladu, a i b parzyste.
     * 
     */
    public final void test3() {
        DeterministicAutomatonSpecification test = new NaiveDeterministicAutomatonSpecification();
        State qpp = test.addState();
        State qnp = test.addState();
        State qnn = test.addState();
        State qpn = test.addState();
        test.addTransition(qpp, qnp, new CharTransitionLabel('a'));
        test.addTransition(qnp, qpp, new CharTransitionLabel('a'));
        test.addTransition(qnp, qnn, new CharTransitionLabel('b'));
        test.addTransition(qnn, qnp, new CharTransitionLabel('b'));
        test.addTransition(qnn, qpn, new CharTransitionLabel('a'));
        test.addTransition(qpn, qnn, new CharTransitionLabel('a'));
        test.addTransition(qpp, qpn, new CharTransitionLabel('b'));
        test.addTransition(qpn, qpp, new CharTransitionLabel('b'));
        test.markAsInitial(qpp);
        test.markAsFinal(qpp);
        DeterministicAutomaton test1 = new DeterministicAutomaton(test);
        assertTrue(test1.accepts("aabb"));
        assertFalse(test1.accepts("aab"));
        assertTrue(test1.accepts("aa"));
        assertTrue(test1.accepts("bb"));
        assertFalse(test1.accepts("abb"));
    }
}