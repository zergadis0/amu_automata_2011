package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import java.util.Set;
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

        assertFalse(trans.doCheckContext("panandrzej", 3));
        assertFalse(trans.doCheckContext("cojapacze", 15));
   
       /**
        * Test atrybutów.
        */

        assertEquals(trans.toString(), "EndOfText");

        assertFalse(trans.isEmpty());
        assertFalse(canAcceptCharacter('s'));

    }

}
