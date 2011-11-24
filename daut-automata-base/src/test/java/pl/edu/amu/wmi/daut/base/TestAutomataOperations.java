package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import junit.framework.TestCase;

/**
 * Testy różnych operacji na automatach.
 */
public class TestAutomataOperations extends TestCase {

    /**
     * Test prostego automatu.
     */
    public final void testSimpleAutomaton() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();
        State q10 = automatonB.addState();
        State q11 = automatonB.addState();
        State q12 = automatonB.addState();
        automatonB.addTransition(q10, q11, new CharTransitionLabel('a'));
        automatonB.addTransition(q10, q11, new CharTransitionLabel('b'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('a'));
        automatonB.addTransition(q11, q12, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q10);
        automatonB.markAsFinal(q12);


        // proszę odkomentować, kiedy AutomataOperations.intersection
        // będzie gotowe!!!
        // AutomatonSpecification Result = AutomataOperations.intersection(automatonA, automatonB);
        // AutomatonByRecursion automaton = AutomatonByRecursion(Result);

        // assertTrue(automaton.accepts("aa"));
        // assertTrue(automaton.accepts("ab"));
        // assertFalse(automaton.accepts(""));
        // assertFalse(automaton.accepts("a"));

    }

    /**
     * Test sprawdza metode Sum w AutomataOperations A.
     */
    public final void testSumA() {

        AutomatonSpecification automatonA = new NaiveAutomatonSpecification();

        State q0 = automatonA.addState();
        State q1 = automatonA.addState();
        automatonA.addTransition(q0, q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('a'));
        automatonA.addLoop(q1, new CharTransitionLabel('b'));
        automatonA.markAsInitial(q0);
        automatonA.markAsFinal(q1);

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification result = AutomataOperations.sum(automatonA, automatonB);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("ba"));
        assertTrue(automaton.accepts("aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaa"));
        assertTrue(automaton.accepts("bb"));
        assertTrue(automaton.accepts("abbbbabbbabbb"));
        assertFalse(automaton.accepts("bbb"));
        assertFalse(automaton.accepts("tegomaniezakceptowac"));
        assertFalse(automaton.accepts("baaaaaaaaaa"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaxaaaaaa"));
        assertFalse(automaton.accepts("bab"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations B.
     */
    public final void testSumB() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonD = new NaiveAutomatonSpecification();

        State q0D = automatonD.addState();
        State q1D = automatonD.addState();
        State q2D = automatonD.addState();
        State q3D = automatonD.addState();
        automatonD.addTransition(q0D, q1D, new CharTransitionLabel('a'));
        automatonD.addTransition(q0D, q2D, new CharTransitionLabel('b'));
        automatonD.addTransition(q1D, q3D, new CharTransitionLabel('a'));
        automatonD.addTransition(q1D, q2D, new CharTransitionLabel('b'));
        automatonD.addTransition(q2D, q0D, new CharTransitionLabel('c'));
        automatonD.addTransition(q2D, q1D, new CharTransitionLabel('b'));
        automatonD.addTransition(q2D, q3D, new CharTransitionLabel('a'));
        automatonD.addTransition(q3D, q2D, new CharTransitionLabel('c'));
        automatonD.addTransition(q3D, q0D, new CharTransitionLabel('b'));
        automatonD.markAsInitial(q0D);
        automatonD.markAsFinal(q3D);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonD);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("ab"));
        assertTrue(automaton.accepts("abbabba"));
        assertTrue(automaton.accepts("bbbcaacba"));
        assertTrue(automaton.accepts("aacacaca"));
        assertTrue(automaton.accepts("aa"));
        assertFalse(automaton.accepts("zle"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("aac"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations C.
     */
    public final void testSumC() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();

        State q0C = automatonC.addState();
        automatonC.addLoop(q0C, new CharTransitionLabel('a'));
        automatonC.addLoop(q0C, new CharTransitionLabel('b'));
        automatonC.addLoop(q0C, new CharTransitionLabel('c'));
        automatonC.addLoop(q0C, new CharTransitionLabel('d'));
        automatonC.markAsInitial(q0C);
        automatonC.markAsFinal(q0C);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonC);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("babbaccddcaaccb"));
        assertTrue(automaton.accepts("bbaccddbaba"));
        assertTrue(automaton.accepts("bbbcaacba"));
        assertTrue(automaton.accepts("aaaaaaaaaaaaaaaa"));
        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("bbaccddxbaba"));
        assertFalse(automaton.accepts("czytwojprogrammackutozaakceptuje"));
        assertFalse(automaton.accepts("zielonosmutnaniebieskowesolapomaranczowa"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations D.
     */
    public final void testSumD() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonE = new NaiveAutomatonSpecification();

        State q0E = automatonE.addState();
        automatonE.addTransition(q0E, q0E, new EpsilonTransitionLabel());
        automatonE.markAsInitial(q0E);
        automatonE.markAsFinal(q0E);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonE);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts(""));
        assertTrue(automaton.accepts("aa"));
        assertFalse(automaton.accepts("bbaccddxbaba"));
        assertFalse(automaton.accepts("aabbbaaaa"));
    }

    /**
     * Test sprawdza metode Sum w AutomataOperations E.
     */
    public final void testSumE() {

        AutomatonSpecification automatonB = new NaiveAutomatonSpecification();

        State q0B = automatonB.addState();
        State q1B = automatonB.addState();
        State q2B = automatonB.addState();
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('a'));
        automatonB.addTransition(q0B, q1B, new CharTransitionLabel('b'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('a'));
        automatonB.addTransition(q1B, q2B, new CharTransitionLabel('b'));
        automatonB.markAsInitial(q0B);
        automatonB.markAsFinal(q2B);

        AutomatonSpecification automatonF = new NaiveAutomatonSpecification();

        State q0F = automatonF.addState();
        State q1F = automatonF.addState();
        State q2F = automatonF.addState();
        State q3F = automatonF.addState();
        State q7F = automatonF.addState();
        State q5F = automatonF.addState();
        State q6F = automatonF.addState();
        automatonF.addTransition(q0F, q1F, new CharTransitionLabel('a'));
        automatonF.addTransition(q0F, q3F, new EpsilonTransitionLabel());
        automatonF.addTransition(q0F, q2F, new EpsilonTransitionLabel());
        automatonF.addTransition(q3F, q7F, new CharTransitionLabel('a'));
        automatonF.addTransition(q2F, q5F, new EpsilonTransitionLabel());
        automatonF.addTransition(q5F, q6F, new CharTransitionLabel('b'));
        automatonF.markAsInitial(q0F);
        automatonF.markAsFinal(q1F);
        automatonF.markAsFinal(q7F);
        automatonF.markAsFinal(q6F);

        AutomatonSpecification result = AutomataOperations.sum(automatonB, automatonF);

        NondeterministicAutomatonByThompsonApproach automaton = new
        NondeterministicAutomatonByThompsonApproach(result);

        assertTrue(automaton.accepts("aa"));
        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts("a"));
        assertFalse(automaton.accepts("aaabbbb"));
        assertFalse(automaton.accepts(""));
    }

    /**
     * Test funkcji determinize2(). Automat akceptuje słowa postaci:
     * ((b)^2n)(a^m)(*), gdzie m,n >= 0, a (*) oznacza dowolny ciąg znaków.
     */
    public final void testDeterminize2SimpleAutomaton() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        State qPoczatkowy = nonDeterministicAutomat.addState();
        State qKoncowy = nonDeterministicAutomat.addState();
        nonDeterministicAutomat.addLoop(qPoczatkowy, new CharTransitionLabel('a'));
        nonDeterministicAutomat.addTransition(qPoczatkowy, qKoncowy, new CharTransitionLabel('a'));
        nonDeterministicAutomat.addTransition(qPoczatkowy, qKoncowy, new CharTransitionLabel('b'));
        nonDeterministicAutomat.addTransition(qKoncowy, qPoczatkowy, new CharTransitionLabel('b'));
        nonDeterministicAutomat.markAsFinal(qPoczatkowy);
        nonDeterministicAutomat.markAsInitial(qPoczatkowy);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(deterministicAutomat);

        assertTrue(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("aaaaa"));
        assertTrue(zdeterminizowany.accepts("abbababbaa"));
        assertTrue(zdeterminizowany.accepts("bbaba"));
        assertFalse(zdeterminizowany.accepts("bababb"));
    }

    /**
     * Test funkcji determinize2(). Prosty automat niedeterministyczny z Wikipedii.
     */
    public final void testDeterminize2AutomatonFromWikipedia() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification innaNazwa
                = new NaiveDeterministicAutomatonSpecification();

        State qA = nonDeterministicAutomat.addState();
        State qB = nonDeterministicAutomat.addTransition(qA, new CharTransitionLabel('1'));
        State qC = nonDeterministicAutomat.addTransition(qA, new CharTransitionLabel('1'));
        nonDeterministicAutomat.addTransition(qC, qB, new CharTransitionLabel('0'));
        nonDeterministicAutomat.addTransition(qB, qA, new CharTransitionLabel('0'));
        nonDeterministicAutomat.markAsInitial(qA);
        nonDeterministicAutomat.markAsFinal(qC);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, innaNazwa);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(innaNazwa);

        assertFalse(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("1"));
        assertTrue(zdeterminizowany.accepts("101"));
        assertTrue(zdeterminizowany.accepts("10101001"));
        assertFalse(zdeterminizowany.accepts("1000"));
        assertFalse(zdeterminizowany.accepts("0"));
    }

    /**
     * Test funkcji determinize2(). Automat !już! deterministyczny akceptuje tylko słowa:
     * Marcin, Maria, Marianna, Mariusz, Marek, Marzena.
     */
    public final void testDeterminize2DeterministicSixNames() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        State qPoczatkowy = nonDeterministicAutomat.addState();
        State qMar = nonDeterministicAutomat.addTransitionSequence(qPoczatkowy, "Mar");
        State qMarc = nonDeterministicAutomat.addTransition(qMar, new CharTransitionLabel('c'));
        State qMarcin = nonDeterministicAutomat.addTransitionSequence(qMarc, "in");
        State qMari = nonDeterministicAutomat.addTransition(qMar, new CharTransitionLabel('i'));
        State qMaria = nonDeterministicAutomat.addTransition(qMari, new CharTransitionLabel('a'));
        State qMarianna = nonDeterministicAutomat.addTransitionSequence(qMaria, "nna");
        State qMariusz = nonDeterministicAutomat.addTransitionSequence(qMari, "usz");
        State qMarek = nonDeterministicAutomat.addTransitionSequence(qMar, "ek");
        State qMarzena = nonDeterministicAutomat.addTransitionSequence(qMar, "zena");
        nonDeterministicAutomat.markAsInitial(qPoczatkowy);
        nonDeterministicAutomat.markAsFinal(qMarcin);
        nonDeterministicAutomat.markAsFinal(qMaria);
        nonDeterministicAutomat.markAsFinal(qMarianna);
        nonDeterministicAutomat.markAsFinal(qMariusz);
        nonDeterministicAutomat.markAsFinal(qMarek);
        nonDeterministicAutomat.markAsFinal(qMarzena);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(deterministicAutomat);

        assertFalse(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("Marcin"));
        assertTrue(zdeterminizowany.accepts("Maria"));
        assertTrue(zdeterminizowany.accepts("Marianna"));
        assertTrue(zdeterminizowany.accepts("Mariusz"));
        assertTrue(zdeterminizowany.accepts("Marek"));
        assertTrue(zdeterminizowany.accepts("Marzena"));
        assertFalse(zdeterminizowany.accepts("mariusz"));
    }

    /**
     * Test funkcji determinize2(). Automaty są niepoprawne dla tej metody, powinna ona
     * zwracać wyjątki.
     */
    public final void testDeterminize2WrongAutomatons() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            assertTrue(true);
        }

        State first = nonDeterministicAutomat.addState();
        nonDeterministicAutomat.addTransition(first, new EpsilonTransitionLabel());

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test funkcji determinize2(). Automat zawiera ComplementCharClassTransitionLabel.
     */
    public final void testDeterminize2AutomatonWithComplementCharClass() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        State qBegin = nonDeterministicAutomat.addState();
        State qFirstEnd = nonDeterministicAutomat
                .addTransition(qBegin, new ComplementCharClassTransitionLabel("a-gt"));
        State qSecondEnd = nonDeterministicAutomat
                .addTransition(qBegin, new CharTransitionLabel('z'));
        State qThirdArm = nonDeterministicAutomat
                .addTransition(qBegin, new ComplementCharClassTransitionLabel("f-t"));
        State qThirdEnd = nonDeterministicAutomat
                .addTransition(qThirdArm, new CharTransitionLabel('z'));
        nonDeterministicAutomat.markAsInitial(qBegin);
        nonDeterministicAutomat.markAsFinal(qFirstEnd);
        nonDeterministicAutomat.markAsFinal(qSecondEnd);
        nonDeterministicAutomat.markAsFinal(qThirdEnd);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(deterministicAutomat);

        assertFalse(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("z"));
        assertTrue(zdeterminizowany.accepts("ez"));
        assertTrue(zdeterminizowany.accepts("uz"));
        assertTrue(zdeterminizowany.accepts("h"));
        assertTrue(zdeterminizowany.accepts("k"));
        assertFalse(zdeterminizowany.accepts("f"));
    }

    /**
     * Test funkcji determinize2(). Automat zawiera CharSetTransitionLabel.
     */
    public final void testDeterminize2AutomatonWithCharSet() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        State qBegin = nonDeterministicAutomat.addState();
        HashSet<Character> mySet = new HashSet<Character>();
        mySet.add('a');
        mySet.add('b');
        mySet.add('d');
        mySet.add('f');
        mySet.add('h');
        mySet.add('z');
        State qFirstArm = nonDeterministicAutomat
                .addTransition(qBegin, new CharTransitionLabel('a'));
        State qFirstEnd = nonDeterministicAutomat
                .addTransition(qSecondArm, new AnyTransitionLabel());
        State qSecondEnd = nonDeterministicAutomat
                .addTransition(qBegin, new CharSetTransitionLabel(mySet));
        nonDeterministicAutomat.markAsInitial(qBegin);
        nonDeterministicAutomat.markAsFinal(qFirstEnd);
        nonDeterministicAutomat.markAsFinal(qSecondEnd);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(deterministicAutomat);

        assertFalse(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("a"));
        assertTrue(zdeterminizowany.accepts("b"));
        assertTrue(zdeterminizowany.accepts("h"));
        assertTrue(zdeterminizowany.accepts("z"));
        assertTrue(zdeterminizowany.accepts("az"));
        assertTrue(zdeterminizowany.accepts("ak"));
        assertFalse(zdeterminizowany.accepts("g"));
        assertFalse(zdeterminizowany.accepts("A"));
        assertFalse(zdeterminizowany.accepts("fz"));
        assertFalse(zdeterminizowany.accepts("c"));
    }

    /**
     * Test funkcji determinize2(). Automat zawiera CharRangeTransitionLabel.
     */
    public final void testDeterminize2AutomatonWithCharRange() {
        AutomatonSpecification nonDeterministicAutomat = new NaiveAutomatonSpecification();
        DeterministicAutomatonSpecification deterministicAutomat
                = new NaiveDeterministicAutomatonSpecification();

        State qBegin = nonDeterministicAutomat.addState();
        State qFirstEnd = nonDeterministicAutomat
                .addTransition(qBegin, new CharRangeTransitionLabel('a', 'f'));
        State qSecondArm = nonDeterministicAutomat
                .addTransition(qBegin, new CharTransitionLabel('a'));
        State qSecondEnd = nonDeterministicAutomat
                .addTransition(qSecondArm, new CharRangeTransitionLabel('g', 'j'));
        nonDeterministicAutomat.addLoop(qFirstEnd, new CharTransitionLabel('a'));
        nonDeterministicAutomat.markAsInitial(qBegin);
        nonDeterministicAutomat.markAsFinal(qFirstEnd);
        nonDeterministicAutomat.markAsFinal(qSecondEnd);

        try {
            AutomataOperations.determinize2(nonDeterministicAutomat, deterministicAutomat);
        } catch (Exception e) {
            fail();
        }

        AutomatonByRecursion zdeterminizowany = new AutomatonByRecursion(deterministicAutomat);

        assertFalse(zdeterminizowany.accepts(""));
        assertTrue(zdeterminizowany.accepts("a"));
        assertTrue(zdeterminizowany.accepts("b"));
        assertTrue(zdeterminizowany.accepts("c"));
        assertTrue(zdeterminizowany.accepts("d"));
        assertTrue(zdeterminizowany.accepts("e"));
        assertTrue(zdeterminizowany.accepts("f"));
        assertTrue(zdeterminizowany.accepts("aa"));
        assertTrue(zdeterminizowany.accepts("fa"));
        assertTrue(zdeterminizowany.accepts("ba"));
        assertTrue(zdeterminizowany.accepts("ag"));
        assertTrue(zdeterminizowany.accepts("aj"));
        assertFalse(zdeterminizowany.accepts("g"));
        assertFalse(zdeterminizowany.accepts("bb"));
        assertFalse(zdeterminizowany.accepts("fat"));
    }
}
