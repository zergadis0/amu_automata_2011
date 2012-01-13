package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Klasa testujaca klase WordBoundaryTransitionLabel.
*/
public class TestWordBoundaryTransitionLabel extends TestCase {

/**
* Test metody doCheckContext.
*/
    public final void testDoCheckContext() {
        WordBoundaryTransitionLabel trans = new WordBoundaryTransitionLabel();

        assertTrue(trans.doCheckContext("baobab", 7));
        assertTrue(trans.doCheckContext("snob", 5));

        try {
            trans.doCheckContext("gorczyca", 10);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

        try {
            trans.doCheckContext("robot", 3);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

/**
* Test atrybutów.
*/

        assertEquals(trans.toString(), "WordBoundary");

        assertFalse(trans.isEmpty());
        assertFalse(trans.canAcceptCharacter('d'));

    }

}
