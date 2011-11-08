package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author s362606
 * Klasa testująca ComplementCharClassTransitionLabel.
 */
public class TestComplementCharClassTransitionLabel extends TestCase {

    /**
     * Test metody canAcceptCharacter.
     */
    public final void testcanAcceptCharacter() {

        //budowanie

        ComplementCharClassTransitionLabel test =
                new ComplementCharClassTransitionLabel("A-Ya-y0-8");
        ComplementCharClassTransitionLabel test1 =
                new ComplementCharClassTransitionLabel("A-Hakrwhelz46753");
        ComplementCharClassTransitionLabel test2 =
                new ComplementCharClassTransitionLabel("-");

        //testowanie

        assertTrue(test.canAcceptCharacter('z'));
        assertTrue(test.canAcceptCharacter('Z'));
        assertFalse(test.canAcceptCharacter('a'));
        assertFalse(test.canAcceptCharacter('G'));
        assertTrue(test.canAcceptCharacter('9'));
        assertFalse(test.canAcceptCharacter('1'));
        assertTrue(test1.canAcceptCharacter('K'));
        assertTrue(test1.canAcceptCharacter('o'));
        assertTrue(test1.canAcceptCharacter('9'));
        assertFalse(test1.canAcceptCharacter('B'));
        assertFalse(test1.canAcceptCharacter('6'));
        assertFalse(test1.canAcceptCharacter('r'));
        assertTrue(test2.canAcceptCharacter('g'));
        assertFalse(test2.canAcceptCharacter('-'));
    }

    /**
     * Test metod canBeEpsilon i isEmpty.
     */
    public final void testSimple() {

        //budowanie

        ComplementCharClassTransitionLabel test =
                new ComplementCharClassTransitionLabel("");

        //testowanie

        assertFalse(test.canBeEpsilon());
        assertFalse(test.isEmpty());
    }

    /**
     * Test metody getString.
     */
    public final void testgetString() {

        //budowanie

        ComplementCharClassTransitionLabel test1 =
                new ComplementCharClassTransitionLabel("abc");
        ComplementCharClassTransitionLabel test2 =
                new ComplementCharClassTransitionLabel("abcFHG5678");
        ComplementCharClassTransitionLabel test3 =
                new ComplementCharClassTransitionLabel("a-dA-G0-5");

        //testowanie

        assertEquals(test1.getString(), "abc");
        assertEquals(test2.getString(), "abcFHG5678");
        assertEquals(test3.getString(), "a-dA-G0-5");
    }

    /**
     * Test metody getSet.
     */
    public final void testgetSet() {

        //budowanie

        ComplementCharClassTransitionLabel test1 =
                new ComplementCharClassTransitionLabel("abG8");
        ComplementCharClassTransitionLabel test2 =
                new ComplementCharClassTransitionLabel("a-d0-5A-G");
        Set hashTest1 = new HashSet();
        Set hashTest2 = new HashSet();
        Set hashTest3 = new HashSet();
        hashTest1.add('G');
        hashTest1.add('a');
        hashTest1.add('8');
        hashTest1.add('b');
        String s  = "abcdABCDEFG012345";
        for (int i = 0; i < s.length(); i++) {
            hashTest2.add(s.charAt(i));
        }

        //testowanie

        assertTrue(hashTest1.equals(test1.getSet()));
        hashTest1.add('h');
        assertFalse(hashTest1.equals(test1.getSet()));
        assertTrue(hashTest2.equals(test2.getSet()));
        hashTest2.remove('d');
        assertFalse(hashTest2.equals(test2.getSet()));
    }

    /**
     * Test wyznaczania przecięcia.
     */
    public final void testintersectWith() {

        //budowanie

        ComplementCharClassTransitionLabel test =
                new ComplementCharClassTransitionLabel("a");
        ComplementCharClassTransitionLabel test1 =
                new ComplementCharClassTransitionLabel("AFG0-9-");
        ComplementCharClassTransitionLabel test2 =
                new ComplementCharClassTransitionLabel("abc");
        EmptyTransitionLabel emptyTransition = new EmptyTransitionLabel();
        ComplementCharClassTransitionLabel inter =
                new ComplementCharClassTransitionLabel("");

        //testowanie

        assertTrue(emptyTransition.intersectWith(test1).isEmpty());
        //assertTrue(test1.intersectWith(emptyTransition).isEmpty());
        assertTrue(emptyTransition.intersectWith(emptyTransition).isEmpty());

        inter = (ComplementCharClassTransitionLabel) test.intersectWith(test);
        Set hashTest = new HashSet();
        hashTest.add('a');

        assertTrue(inter.canAcceptCharacter('e'));
        assertFalse(inter.canAcceptCharacter('a'));
        assertFalse(inter.canBeEpsilon());
        assertEquals("a", inter.getString());
        assertTrue(hashTest.equals(inter.getSet()));

        inter = (ComplementCharClassTransitionLabel) test.intersectWith(test2);

        assertTrue(inter.canAcceptCharacter('d'));
        assertFalse(inter.canAcceptCharacter('a'));
        assertEquals(inter.getSet(), test2.getSet());
        assertEquals(inter.getString(), "bca");

        inter = (ComplementCharClassTransitionLabel) test1.intersectWith(test2);
        String str = "AFGabc0123456789-";

        assertTrue(inter.canAcceptCharacter('k'));
        assertFalse(inter.canAcceptCharacter('a'));
        assertFalse(inter.canAcceptCharacter('1'));
        assertFalse(inter.canBeEpsilon());
        assertTrue(str.length() == (inter.getString()).length());
        assertFalse((str.length() + 1) == (inter.getString()).length());

    }
}
