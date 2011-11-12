package pl.edu.amu.wmi.daut.base;
import java.util.List;
import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Testy klasy AutomatonSpecification.
 */
public class TestAutomatonSpecification extends TestCase {

    /**
     * Test metody fromString() tworzacy pusty automat.
     */
    public final void testFromString0EmptyLanguage() {
        AutomatonSpecification pustyOjciec = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states:";

        try {
            pustyOjciec.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(pustyOjciec);

        assertFalse(pusteDziecko.accepts("aaa"));
        assertFalse(pusteDziecko.accepts("baba"));
        assertFalse(pusteDziecko.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy slowo puste.
     */
    public final void testFromString1EmptyStringAutomaton() {
        AutomatonSpecification dziwny = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states: q0";

        try {
            dziwny.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion wynik = new AutomatonByRecursion(dziwny);

        assertFalse(wynik.accepts("aaa"));
        assertFalse(wynik.accepts("baba"));
        assertTrue(wynik.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy nieparzyste
     * potegi 'a'.
     */
    public final void testFromString2OddPowersOfA() {
        AutomatonSpecification masakra = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q1\nq1 -a-> q0\n-Initial state: q0\n-Final states: q1";

        try {
            masakra.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion angle = new AutomatonByRecursion(masakra);

        assertFalse(angle.accepts("aab"));
        assertFalse(angle.accepts(""));
        assertTrue(angle.accepts("aaa"));
    }

    /**
     * Test metody fromString() tworzący automat z epsilonem.
     */
    public final void testFromString3AutomatonWithEpsilonTransition() {
        AutomatonSpecification epsilon = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1 q2 q3\n-Transitions:\n"
                + "q0 -epsilon-> q1\nq1 -a-> q2\n-Initial state: q0\n"
                + "-Final states: q3";

        try {
            epsilon.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z Epsilonem!");
        }

        boolean potworek = false;
        for (int i = 0; i < 3; i++) {
            if (!epsilon.allOutgoingTransitions(epsilon.allStates().get(i)).isEmpty()) {
                if (epsilon.allOutgoingTransitions(epsilon.allStates().get(i))
                        .get(0).getTransitionLabel().canBeEpsilon()) {
                    potworek = true;
                    break;
                }
            }
        }
        assertTrue(potworek);
//  Wykomentowane do czasu obsługiwania przez jakikolwiek automat przejść epsilonowych.
//        AutomatonByRecursion ep = new AutomatonByRecursion(epsilon);
//
//        assertTrue(ep.accepts("a"));
//        assertTrue(ep.accepts("b"));
//        assertFalse(ep.accepts(""));
//        assertFalse(ep.accepts("ε"));
//        assertFalse(ep.accepts("aa"));
//        assertFalse(ep.accepts("ab"));
//        assertFalse(ep.accepts("ba"));
//        assertFalse(ep.accepts("bb"));
    }

    /**
     * Test metody fromString() z bełkotem.
     */
    public final void testFromString4Blubbering() {
        AutomatonSpecification niepoprawny = new NaiveAutomatonSpecification();

        String slowo = "niepoprawnywogolestring";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Magia: dwa trzy cztery piec";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Automaton:\n-Niestates: q0\n-Transitions: magia";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów.
     */
    public final void testFromString5WrongAutomatonStrings0States() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectStateName0 = "Automaton:\n-States: q\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName1 = "Automaton:\n-States: a1\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName2 = "Automaton:\n-States: q1.2\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName3 = "Automaton:\n-States: qa\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName3);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw przejść.
     */
    public final void testFromString5WrongAutomatonStrings1Transitions() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectTransitionName0Label0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 .q-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --.> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label3 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -\t-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "qa -a-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> qa";

        try {
            bledny.fromString(nonCorrectTransitionName2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów specjalnych.
     */
    public final void testFromString5WrongAutomatonStrings2SpecialStates() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectSpecialStates0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial error";

        try {
            bledny.fromString(nonCorrectSpecialStates0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: qa";

        try {
            bledny.fromString(nonCorrectSpecialStates1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\nerror";

        try {
            bledny.fromString(nonCorrectSpecialStates2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates3 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final error";

        try {
            bledny.fromString(nonCorrectSpecialStates3);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates4 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final states: qk";

        try {
            bledny.fromString(nonCorrectSpecialStates4);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() tworzący automat z przejściem ANY.
     */
    public final void testFromString6AutomatonWithAnyTransition() {
        AutomatonSpecification anyone = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1 q2\n-Transitions:\n"
                + "q0 -q-> q1\nq1 -ANY-> q2\n-Initial state: q0\n-Final states: q2";

        try {
            anyone.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z przejściem dowolnym!");
        }

        AutomatonByRecursion ap = new AutomatonByRecursion(anyone);

        assertTrue(ap.accepts("q0"));
        assertTrue(ap.accepts("qε"));
        assertFalse(ap.accepts(""));
        assertFalse(ap.accepts("q"));
        assertFalse(ap.accepts("q10"));
        assertFalse(ap.accepts("aq0"));
        assertFalse(ap.accepts("01"));
    }

    /**
     * Test metody fromString() tworzący automat z przejściem po znaku z dopełnienia
     * sumy przedziałów.
     */
    public final void testFromString7AutomatonWithCCharClassTransition() {
        AutomatonSpecification ccchar = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -[^ac-e]-> q1\n-Initial state: q0\n-Final states: q1";

        try {
            ccchar.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z odwrotnością przejścia"
                    + "z sumy przedziałów!");
        }

        AutomatonByRecursion cccp = new AutomatonByRecursion(ccchar);

        assertFalse(cccp.accepts("a"));
        assertFalse(cccp.accepts("c"));
        assertFalse(cccp.accepts("d"));
        assertFalse(cccp.accepts("e"));
        assertFalse(cccp.accepts(""));
        assertTrue(cccp.accepts("f"));
        assertTrue(cccp.accepts("b"));
    }

    /**
     * Test metody fromString() tworzący automat z przejściem po znaku z sumy przedziałów.
     */
//    public final void testFromString8AutomatonWithCharClassTransition() {
//        AutomatonSpecification cchar = new NaiveAutomatonSpecification();
//
//        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
//                + "q0 -[ac-e]-> q1\n-Initial state: q0\n-Final states: q1";
//
//        try {
//            cchar.fromString(slowo);
//        } catch (Exception e) {
//            fail("fromString() zwrocil wyjatek dla automatu z odwrotnością przejścia"
//                    + "z sumy przedziałów!");
//        }
//
//        AutomatonByRecursion ccp = new AutomatonByRecursion(cchar);
//
//        assertTrue(ccp.accepts("a"));
//        assertTrue(ccp.accepts("c"));
//        assertTrue(ccp.accepts("d"));
//        assertTrue(ccp.accepts("e"));
//        assertFalse(ccp.accepts(""));
//        assertFalse(ccp.accepts("b"));
//        assertFalse(ccp.accepts("f"));
//    }

    /**
     * Test metody countStates.
     */
    public final void testCountStates() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countStates(), 0);

        //Test 2
        State q0 = spec.addState();
        assertEquals(spec.countStates(), 1);

        //Test 3
        for (int i = 1; i <= 123456; i++) {
            State q = spec.addState();
        }
        assertEquals(spec.countStates(), 123456 + 1);
    }

    /**
     * Test metody countStates.
     */
    public final void testCountTransitions() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countTransitions(), 0);

        //Test 2
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q2);

        assertEquals(spec.countTransitions(), 2);

        //Test 3
        spec.addTransition(q2, q0, new CharTransitionLabel('c'));

        assertEquals(spec.countTransitions(), 3);

        //Test 4
        spec.addTransition(q0, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 4);

        //Test 5
        spec.addTransition(q2, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 5);
    }

    /**
     * Test metody makeEmptyStringAutomaton.
     */
    public final void testmakeEmptyStringAutomaton() {
        AutomatonSpecification automaton1 = new NaiveAutomatonSpecification();
        automaton1.makeEmptyStringAutomaton();
        AutomatonByRecursion angle = new AutomatonByRecursion(automaton1);

        assertFalse(angle.accepts("qqqqqq"));
        assertTrue(angle.accepts(""));
        assertFalse(angle.accepts("x"));
        assertFalse(angle.accepts("qwertyuiopasdfghjklzxcvbnm1234567890"));
        assertFalse(angle.accepts(" "));
    }

    /**
     * Testy dla metody addTransitionSequence().
     */
    public final void testAddTransitionSequence() {
        // Tworzymy automat do testów
        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        // Proste testy ilości stanów i przejść
        // Pusty ciąg przejść
        State s0 = spec.addState();
        spec.markAsInitial(s0);
        spec.addTransitionSequence(s0, "");
        assertEquals(0, spec.countTransitions());
        assertEquals(1, spec.countStates());

        // Ciąg przejść składający się z jednego znaku
        spec = new NaiveAutomatonSpecification();
        s0 = spec.addState();
        spec.markAsInitial(s0);
        spec.addTransitionSequence(s0, "a");
        assertEquals(1, spec.countTransitions());
        assertEquals(2, spec.countStates());

        // Ciąg przejść składający się z jednego znaku
        // i dodanie stanu na podstawie tego co zwróciła
        // metoda addTransitionSequence()
        spec = new NaiveAutomatonSpecification();
        s0 = spec.addState();
        spec.markAsInitial(s0);
        State s1 = spec.addTransitionSequence(s0, "a");
        spec.addTransition(s1, new CharTransitionLabel('b'));
        assertEquals(2, spec.countTransitions());
        assertEquals(3, spec.countStates());

        // Ciąg przejść składający się z takich samych znaków
        spec = new NaiveAutomatonSpecification();
        s0 = spec.addState();
        spec.markAsInitial(s0);
        spec.addTransitionSequence(s0, "aa");
        assertEquals(2, spec.countTransitions());
        assertEquals(3, spec.countStates());

        // Ciąg przejść składający się z różnych znaków
        spec = new NaiveAutomatonSpecification();
        s0 = spec.addState();
        spec.markAsInitial(s0);
        spec.addTransitionSequence(s0, "abc");
        assertEquals(3, spec.countTransitions());
        assertEquals(4, spec.countStates());

        // Sprawdzamy czy przejścia mają odpowiednie oznaczenia oraz
        // akceptują odpowiednie znaki, dla ciągu "abc"
        State s;
        List<OutgoingTransition> sOuts;

        // Dla jasności pobieramy stan początkowy automatu
        s = spec.getInitialState();

        // Sprawdzamy możliwe przejścia ze stanu początkowego,
        // sprawdzamy ich ilość, oznaczenia oraz jakie znaki akceptują
        // (oczekujemy a)
        sOuts = spec.allOutgoingTransitions(s);
        assertEquals(1, sOuts.size());
        assertEquals('a', ((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).getChar());
        assertTrue(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('a'));
        assertFalse(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('c'));

        // Kolejne przejście (oczekujemy b)
        s = sOuts.get(0).getTargetState();
        sOuts = spec.allOutgoingTransitions(s);
        assertEquals(1, sOuts.size());
        assertEquals('b', ((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).getChar());
        assertTrue(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('b'));
        assertFalse(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('a'));
        assertFalse(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('c'));

        // Kolejne przejście (oczekujemy c)
        s = sOuts.get(0).getTargetState();
        sOuts = spec.allOutgoingTransitions(s);
        assertEquals(1, sOuts.size());
        assertEquals('c', ((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).getChar());
        assertTrue(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('c'));
        assertFalse(((CharTransitionLabel)
                sOuts.get(0).getTransitionLabel()).canAcceptCharacter('a'));
    }

    /**
     * Test metody addBranch().
     */
    public final void testaddBranch() {

       // Budowanie automatu o 4 stanach.
       AutomatonSpecification spec = new NaiveAutomatonSpecification();
       State s0 = spec.addState();
       spec.markAsInitial(s0);
       List<TransitionLabel> transitions =
               Arrays.<TransitionLabel>asList(
            new CharTransitionLabel('a'),
            new CharTransitionLabel('b'),
            new CharTransitionLabel('c')
            );
       State s3 = spec.addBranch(s0, transitions);
       spec.markAsFinal(s3);

       //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);
        assertEquals(r0Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r1 = r0Outs.get(0).getTargetState();
        List<OutgoingTransition> r1Outs = spec.allOutgoingTransitions(r1);
        assertEquals(r1Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r1Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r2 = r1Outs.get(0).getTargetState();
        List<OutgoingTransition> r2Outs = spec.allOutgoingTransitions(r2);
        assertEquals(r2Outs.size(), 1);   //sprawdza ze jest tylko jedno przejscie
        assertTrue(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'c');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'a');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).getChar() == 'b');
        assertFalse(((CharTransitionLabel) r2Outs.get(0).getTransitionLabel()).canBeEpsilon());

        State r3 = r2Outs.get(0).getTargetState();

        assertFalse(spec.isFinal(r2));
        assertTrue(spec.isFinal(r3));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r1, r2);
        assertNotSame(r2, r3);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 4);

        // Budowanie automatu o 1 stanie.
        AutomatonSpecification spec2 = new NaiveAutomatonSpecification();
        State st0 = spec2.addState();
        spec.markAsInitial(st0);
        List<TransitionLabel> transitions2 =
                Arrays.<TransitionLabel>asList(
            );
        State st1 = spec2.addBranch(st0, transitions2);
        spec.markAsFinal(st0);

        //testowanie
        State rr0 = spec2.getInitialState();

        List<OutgoingTransition> r0Outs2 = spec2.allOutgoingTransitions(rr0);
        assertEquals(r0Outs2.size(), 0);

        assertTrue(spec.isFinal(st1));
        assertSame(st1, spec.getInitialState());

    }


    /**
     * Testuje działanie metody toString().
     */
    public final void testToString() {
        /**
         * Pozwala na wygenerowanie tekstu "oszukanego" automatu
         * na podstawie stanów i krawędzi wprowadzonych jako tekst.
         * Imitacja działania metody toString() dla automatu.
         */
        class AutomatonString {
            private String states, transitions, istates, fstates;

            /**
             * Ustala stany, przejścia, stan początkowy oraz stany końcowe
             */
            public AutomatonString(String states, String transitions,
                    String istates, String fstates) {
                this.states = states;
                this.transitions = transitions;
                this.istates = istates;
                this.fstates = fstates;
            }

            /**
             * Zwraca "oszukany" automat w postaci tekstowej.
             */
            @Override
            public String toString() {
                StringBuffer str = new StringBuffer();
                str.append("Automaton:");
                str.append("\n-States: " + states);
                if (states.length() > 0) {
                    str.append(" ");
                }
                str.append("\n-Transitions:\n");
                if (transitions.length() > 0) {
                    str.append("  " + transitions.replaceAll(",", "\n  "));
                    str.append("\n");
                }
                str.append("-Initial state: " + istates);
                str.append("\n-Final states: " + fstates);
                if (fstates.length() > 0) {
                    str.append(" ");
                }
                return str.toString();
            }
        }

        // Pierwszy testowy automat:
        // Jeden stan z krawędzią
        NaiveAutomatonSpecification ta1 = new NaiveAutomatonSpecification();

        State ta1s0 = ta1.addState();
        ta1.addTransition(ta1s0, ta1s0, new CharTransitionLabel('a'));
        ta1.markAsInitial(ta1s0);
        ta1.markAsFinal(ta1s0);

        AutomatonString str;

        str = new AutomatonString("q0", "q0 -a-> q0", "q0", "q0");
        assertEquals(str.toString(), ta1.toString());

        // Drugi testowy automat:
        // Dwa stany, krawędzie jak w przypadku NFA
        NaiveAutomatonSpecification ta2 = new NaiveAutomatonSpecification();

        State ta2s0 = ta2.addState();
        State ta2s1 = ta2.addState();
        ta2.addTransition(ta2s0, ta2s0, new CharTransitionLabel('a'));
        ta2.addTransition(ta2s0, ta2s1, new CharTransitionLabel('a'));
        ta2.markAsInitial(ta2s0);
        ta2.markAsFinal(ta2s0);
        ta2.markAsFinal(ta2s1);

        String transitions = "q0 -a-> q0,q0 -a-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q0 q1");
        assertEquals(str.toString(), ta2.toString());

        // Trzeci testowy automat:
        // Dwa stany, dwa różne rodzaje krawędzi
        NaiveAutomatonSpecification ta3 = new NaiveAutomatonSpecification();

        State ta3s0 = ta3.addState();
        State ta3s1 = ta3.addState();
        ta3.addTransition(ta3s0, ta3s0, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s0, ta3s1, new CharTransitionLabel('b'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('b'));
        ta3.markAsInitial(ta3s0);
        ta3.markAsFinal(ta3s1);

        transitions = "q0 -a-> q0,q0 -b-> q1,q1 -a-> q1,q1 -b-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q1");
        assertEquals(str.toString(), ta3.toString());

        // Czwarty testowy automat:
        // sprawdzamy co jest zwracane dla "pustej" instancji klasy automatu
        NaiveAutomatonSpecification ta4 = new NaiveAutomatonSpecification();

        str = new AutomatonString("", "", "", "");
        assertEquals(str.toString(), ta4.toString());

        // Piąty testowy automat:
        // Brak stanów początkowych i końcowych
        NaiveAutomatonSpecification ta5 = new NaiveAutomatonSpecification();

        State ta5s0 = ta5.addState();
        ta5.addTransition(ta5s0, ta5s0, new CharTransitionLabel('a'));

        str = new AutomatonString("q0", "q0 -a-> q0", "", "");
        assertEquals(str.toString(), ta5.toString());
    }
    /**
     * Testuje działanie metody testPrefixChecker().
     */
    public final void testPrefixChecker() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q0, q3, new CharTransitionLabel('c'));

        spec.addTransition(q1, q3, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));

        spec.addTransition(q2, q3, new CharTransitionLabel('a'));
        spec.addLoop(q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q5, new CharTransitionLabel('c'));

        spec.addTransition(q3, q0, new CharTransitionLabel('a'));
        spec.addTransition(q3, q0, new CharTransitionLabel('b'));
        spec.addTransition(q3, q4, new CharTransitionLabel('c'));

        // Stan 4 jest pułapką

        spec.addLoop(q4, new CharTransitionLabel('a'));
        spec.addLoop(q4, new CharTransitionLabel('b'));
        spec.addLoop(q4, new CharTransitionLabel('c'));

        // Stan 5 prowadzi tylko do stanu 4

        spec.addLoop(q5, new CharTransitionLabel('a'));
        spec.addTransition(q5, q4, new CharTransitionLabel('b'));
        spec.addLoop(q5, new CharTransitionLabel('c'));

        assertTrue(spec.prefixChecker(q0));
        assertTrue(spec.prefixChecker(q1));
        assertTrue(spec.prefixChecker(q2));
        assertTrue(spec.prefixChecker(q3));
        assertFalse(spec.prefixChecker(q4));
        assertFalse(spec.prefixChecker(q5));
    }
    /**
     * Testuje działanie metody checkPrefix().
     * Bazuje bezpośrednio na teście metody prefixChecker()
     */
    public final void testCheckPrefix() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();
        State q6 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q0, q3, new CharTransitionLabel('c'));
        spec.addTransition(q0, q6, new EpsilonTransitionLabel());

        spec.addTransition(q1, q3, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));

        spec.addTransition(q2, q3, new CharTransitionLabel('a'));
        spec.addLoop(q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q5, new CharTransitionLabel('c'));

        spec.addTransition(q3, q0, new CharTransitionLabel('a'));
        spec.addTransition(q3, q0, new CharTransitionLabel('b'));
        spec.addTransition(q3, q4, new CharTransitionLabel('c'));

        // Stan 4 jest pułapką

        spec.addLoop(q4, new CharTransitionLabel('a'));
        spec.addLoop(q4, new CharTransitionLabel('b'));
        spec.addLoop(q4, new CharTransitionLabel('c'));

        // Stan 5 prowadzi tylko do stanu 4

        spec.addLoop(q5, new CharTransitionLabel('a'));
        spec.addTransition(q5, q4, new CharTransitionLabel('b'));
        spec.addLoop(q5, new CharTransitionLabel('c'));

        spec.addTransition(q6, q3, new CharTransitionLabel('c'));

        assertTrue(spec.checkPrefix("abbb"));
        assertTrue(spec.checkPrefix("cacbab"));
        assertTrue(spec.checkPrefix("bbaabcaa"));
        assertTrue(spec.checkPrefix("cababbc"));
        assertTrue(spec.checkPrefix("c"));
        assertTrue(spec.checkPrefix("cbcbcbcbcbacbbbbaab"));

        assertFalse(spec.checkPrefix("aacc"));
        assertFalse(spec.checkPrefix("sdfcs"));
        assertFalse(spec.checkPrefix("bcca"));
        assertFalse(spec.checkPrefix("cc"));
        assertFalse(spec.checkPrefix("abaabacac"));
        assertFalse(spec.checkPrefix("caccb"));
        assertFalse(spec.checkPrefix("bac"));
    }

    /**
     *test automatu pustego.
     */
    public final void testIsEmpty() {
        AutomatonSpecification automat = new NaiveAutomatonSpecification();
        assertTrue(automat.isEmpty());
    }
    /**
     *test automatu niepustego.
     */
    public final void testIsEmptyforNotEmpty() {
        AutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s1 = automat.addState();
        assertFalse(automat.isEmpty());
    }
}
