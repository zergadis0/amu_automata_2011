package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
/**
 *
 * @author damian
 */
public class TestDeterministicAutomaton extends TestCase {
    /**test 1
     * 
     */
    public final void test1() {
        DeterministicAutomatonSpecification test = new NaiveDeterministicAutomatonSpecification();
        State q1 = test.addState();
        State q2 = test.addState();
        test.addTransition(q1, q2, new CharTransitionLabel('a'));
        test.markAsInitial(q1);
        test.markAsFinal(q2);
        DeterministicAutomaton test1 = new DeterministicAutomaton(test);
        assertTrue(test1.accepts("a"));
        assertFalse(test1.accepts("b"));
    }
    public final void test2() {
        
    }
    public final void test3() {
        
    }
    public final void test4() {
        
    }
}
