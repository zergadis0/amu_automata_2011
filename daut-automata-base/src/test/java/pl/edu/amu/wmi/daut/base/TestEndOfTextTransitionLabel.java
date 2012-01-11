package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca klase EndOfTextTransitionLabel.
 */
public class TestEndOfTextTransitionLabel extends TestCase {

    /**
     * Test metody doCheckContext.
     */
    public final void testDoCheckContext() {
        EndOfTextTransitionLabel trans = new EndOfTextTransitionLabel();

        assertTrue(trans.doCheckContext("trololololo", 11));
        assertTrue(trans.doCheckContext("masa", 4));

        /**
         * Test atrybutów.
         */

        assertEquals(trans.toString(), "EndOfText");

        assertFalse(trans.isEmpty());
        assertFalse(trans.canAcceptCharacter('s'));

    }

}
