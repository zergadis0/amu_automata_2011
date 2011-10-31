package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveAutomatonSpecification.
 */
public class TestNaiveAutomatonSpecification extends TestCase {

    /**
     * Test prostego automatu o trzech stanach.
     */
    public final void testSimpleAutomaton() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        // budowanie

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new CharTransitionLabel('a'));
        State s2 = spec.addState();
        spec.addTransition(s0, s2, new CharTransitionLabel('b'));
        spec.addTransition(s1, s2, new CharTransitionLabel('c'));

        spec.markAsInitial(s0);
        spec.markAsFinal(s2);

        // testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        // w ten sposób w JUnicie wyrażamy oczekiwanie, że liczba
        // przejść wychodzących z początkowego stanu powinna być równa 2
        assertEquals(r0Outs.size(), 2);
        assertFalse(spec.isFinal(r0));

        State r1;
        State r2;

        if (((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            r2 = r0Outs.get(1).getTargetState();
            assertEquals(((CharTransitionLabel)r0Outs.get(1).getTransitionLabel()).getChar(), 'b');
            assertTrue(((CharTransitionLabel)r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('b'));
            assertFalse(((CharTransitionLabel)r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel)r0Outs.get(1).getTransitionLabel()).canBeEpsilon());
        }
        else {
            // kolejność może być odwrócona
            r1 = r0Outs.get(1).getTargetState();
            r2 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar(), 'b');
        }

        assertFalse(spec.isFinal(r1));
        assertTrue(spec.isFinal(r2));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r0, r2);
        assertNotSame(r1, r2);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 3);
    }

    /**
     * Prosty test wyznaczania przecięcia.
     */
    public final void testIntersections() {
        CharTransitionLabel tA1 = new CharTransitionLabel('a');
        CharTransitionLabel tA2 = new CharTransitionLabel('a');
        CharTransitionLabel tB = new CharTransitionLabel('b');
        EmptyTransitionLabel emptyTransition = new EmptyTransitionLabel();

        TransitionLabel intersectedA = tA1.intersect(tA2);
        assertFalse(intersectedA.isEmpty());
        assertTrue(intersectedA.canAcceptCharacter('a'));
        assertFalse(intersectedA.canAcceptCharacter('b'));

        assertTrue(tA1.intersect(tB).isEmpty());
        assertTrue(tB.intersect(tA1).isEmpty());
        assertTrue(emptyTransition.intersect(tA1).isEmpty());
        assertTrue(tA1.intersect(emptyTransition).isEmpty());
        assertTrue(emptyTransition.intersect(emptyTransition).isEmpty());
    }

    /**
     * Test metody dodaj�cej p�tle
     */
    public final void testAddLoop() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //budowanie

        State s0 = spec.addState();
        spec.addLoop(s0, new CharTransitionLabel('a'));
        spec.markAsInitial(s0);
        spec.markAsFinal(s0);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertTrue(spec.isFinal(r0));

        State r1;

        if (((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar(), 'a');
            assertTrue(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('a'));
            assertFalse(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertTrue(spec.isFinal(r0));
        assertSame(r0, spec.getInitialState());

        List<State> states = spec.allStates();

        assertEquals(states.size(), 1);

    }

    /**
     * Test metody tworz�cej jednostanowy automat z jedn� p�tl�
     */
    public final void testmakeOneLoopAutomaton(char c) {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //budowanie

        State s0 = spec.addState();
        spec.addLoop(s0, new CharTransitionLabel('c'));
        spec.markAsInitial(s0);
        spec.markAsFinal(s0);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertTrue(spec.isFinal(r0));

        State r1;

        if (((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar() == 'c') {
            r1 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar(), 'c');
            assertTrue(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertTrue(spec.isFinal(r0));
        assertSame(r0, spec.getInitialState());

        List<State> states = spec.allStates();

        assertEquals(states.size(), 1);

    }

    /**
     * Test metody tworz�cej dwustanowy automat z jednym przej�ciem
     */
    public final void testmakeOneTransitionAutomaton(char c) {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //budowanie

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new CharTransitionLabel('c'));
        spec.markAsInitial(s0);
        spec.markAsFinal(s1);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertFalse(spec.isFinal(r0));

        State r1;

        if (((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar() == 'c') {
            r1 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).getChar(), 'c');
            assertTrue(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel)r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertFalse(spec.isFinal(r0));
        assertTrue(spec.isFinal(r1));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 2);

    }

}
