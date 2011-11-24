/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
/**
 *
 * @author marcinwacho
 */
public class TestCharClassTransitionLabel extends TestCase {

    /**
     * Testujemy metody CharClassTransitionLabel, addChar, canBeEpsilon,
     * canAcceptCharacter, isEmpty, toString.
     */

public final void testCharClassTransitionLabel() {

     //budujemy
        TransitionLabel test1 = new CharClassTransitionLabel("0-9");

        //testujemy
        assertTrue(test1.canAcceptCharacter('0'));
        assertTrue(test1.canAcceptCharacter('5'));
        assertTrue(test1.canAcceptCharacter('9'));
        assertEquals(test1.toString(), "[0-9]");
        assertFalse(test1.canBeEpsilon());
        assertFalse(test1.isEmpty());
        assertFalse(test1.canAcceptCharacter('a'));


//budujemy
        TransitionLabel test2 = new CharClassTransitionLabel("6a-fA-Z");

        //testujemy
        assertTrue(test1.canAcceptCharacter('a'));
        assertTrue(test1.canAcceptCharacter('b'));
        assertTrue(test1.canAcceptCharacter('f'));
        assertTrue(test1.canAcceptCharacter('A'));
        assertTrue(test1.canAcceptCharacter('C'));
        assertTrue(test1.canAcceptCharacter('G'));
        assertTrue(test1.canAcceptCharacter('Y'));
        assertTrue(test1.canAcceptCharacter('6'));
        assertTrue(test1.canAcceptCharacter('Z'));
        assertEquals(test1.toString(), "[a-fA-Z]");
        assertFalse(test1.canBeEpsilon());
        assertFalse(test1.isEmpty());
        assertFalse(test1.canAcceptCharacter('g'));
        assertFalse(test1.canAcceptCharacter('5'));

}

 /**
     * Testujemy intersectWith.
     */
    public final void testintersect() {

        //budujemy
        TransitionLabel test1 = new CharClassTransitionLabel("a-c");
        TransitionLabel test2 = new CharClassTransitionLabel("b-f");

        //testujemy
        TransitionLabel inter = test1.intersect(test2);
        assertTrue(inter.canAcceptCharacter('b'));
        assertTrue(inter.canAcceptCharacter('c'));
        assertFalse(inter.canAcceptCharacter('a'));
        assertFalse(inter.canAcceptCharacter('d'));
        assertFalse(inter.canAcceptCharacter('f'));
        assertFalse(inter.canBeEpsilon());
        assertFalse(inter.isEmpty());
        assertEquals(inter.toString(), "[b-c]");
        assertFalse(inter.canBeEpsilon());


}
}
