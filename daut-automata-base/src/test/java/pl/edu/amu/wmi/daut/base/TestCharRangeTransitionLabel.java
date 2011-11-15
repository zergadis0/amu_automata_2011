package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca klasę CharRangeTransitionLabel.
 */
public class TestCharRangeTransitionLabel extends TestCase {

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Puste przecięcie.
     */
    public final void test1() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();

        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s2);

        TransitionLabel trans = new CharRangeTransitionLabel('a', 'c');
        TransitionLabel trans2 = new CharRangeTransitionLabel('d', 'f');

        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s0, s1, trans.intersectWith(trans2));

        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.canAcceptCharacter('b'));
        assertTrue(trans.canAcceptCharacter('c'));
        assertFalse(trans.canAcceptCharacter('h'));

         assertFalse(trans.canBeEpsilon());
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Niepuste przecięcie.
     */
    public final void test2() {

        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();

        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        TransitionLabel trans = new CharRangeTransitionLabel('a', 'c');
        TransitionLabel trans2 = new CharRangeTransitionLabel('b', 'f');
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.canBeEpsilon());
        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.canAcceptCharacter('b'));
        assertTrue(trans.canAcceptCharacter('c'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('d'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('d'));
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Puste przecięcie.
     */
    public final void test3() {
         AutomatonSpecification aut = new NaiveAutomatonSpecification();
         State s0 = aut.addState();
         State s1 = aut.addState();
         State s2 = aut.addState();
         State s3 = aut.addState();
         aut.markAsInitial(s0);
         aut.markAsFinal(s3);
         TransitionLabel trans = new CharRangeTransitionLabel('g', 'h');
         TransitionLabel trans2 = new CharRangeTransitionLabel('b', 'f');
         aut.addTransition(s0, s1, trans);
         aut.addTransition(s1, s2, trans2);
         aut.addTransition(s2, s3, trans2.intersectWith(trans));
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały zawierające się.
     */
    public final void test4() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        TransitionLabel trans = new CharRangeTransitionLabel('a', 'h');
        TransitionLabel trans2 = new CharRangeTransitionLabel('c', 'e');
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('e'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('f'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('g'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('h'));

        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('d'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('e'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('f'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('g'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('h'));
    }
    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały równe.
     */
    public final void test5() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        TransitionLabel trans = new CharRangeTransitionLabel('a', 'c');
        TransitionLabel trans2 = new CharRangeTransitionLabel('a', 'c');
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('d'));
    }

    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Końce przedziałów równe.
     */
    public final void test6() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        TransitionLabel trans = new CharRangeTransitionLabel('b', 'c');
        TransitionLabel trans2 = new CharRangeTransitionLabel('a', 'c');
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('c'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('d'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('c'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('d'));
    }
    /**
     * Metoda testująca CharRangeTransitionLabel.
     * Przedziały zawierające się, jednoznakowe.
     */
    public final void test7() {
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        State s0 = aut.addState();
        State s1 = aut.addState();
        State s2 = aut.addState();
        State s3 = aut.addState();
        aut.markAsInitial(s0);
        aut.markAsFinal(s3);
        TransitionLabel trans = new CharRangeTransitionLabel('a', 'a');
        TransitionLabel trans2 = new CharRangeTransitionLabel('a', 'a');
        aut.addTransition(s0, s1, trans);
        aut.addTransition(s1, s2, trans2);
        aut.addTransition(s2, s3, trans2.intersectWith(trans));

        assertTrue(trans.canAcceptCharacter('a'));
        assertTrue(trans.intersectWith(trans2).canAcceptCharacter('a'));
        assertFalse(trans.intersectWith(trans2).canAcceptCharacter('b'));
        assertTrue(trans2.intersectWith(trans).canAcceptCharacter('a'));
        assertFalse(trans2.intersectWith(trans).canAcceptCharacter('b'));
    }
};
