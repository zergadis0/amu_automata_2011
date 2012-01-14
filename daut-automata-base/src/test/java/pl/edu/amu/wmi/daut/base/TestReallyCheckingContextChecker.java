package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy ReallyCheckingContextChecker.
 */
public class TestReallyCheckingContextChecker extends TestCase {

    /**
     * Prosty test ReallyCheckingContextChecker.
     */
    public final void testReallyCheckingContextChecker() {

        ReallyCheckingContextChecker contextChecker = new ReallyCheckingContextChecker("napis",1);

        assertFalse(contextChecker.check(new EndOfTextTransitionLabel()));

        contextChecker = new ReallyCheckingContextChecker("napis" + '\u0003',5);

        assertTrue(contextChecker.check(new EndOfTextTransitionLabel()));

    }
}
