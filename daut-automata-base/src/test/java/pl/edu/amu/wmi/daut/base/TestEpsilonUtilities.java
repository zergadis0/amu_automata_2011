package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
/**
 *
 * @author cole1911
 */
public class TestEpsilonUtilities extends TestCase {

/**
*  Metoda sprawdza czy w automacie sa epsilon przejscia.
*/
    public final void testEpsilonUtilities() {
        /*
        *  Automat bez epsilon przejsc.
        */
        AutomatonSpecification test = new NaiveAutomatonSpecification();
        State koniec = test.addState();
        State pierwszy = test.addState();
        State drugi = test.addState();
        State trzeci = test.addState();
        test.markAsInitial(pierwszy);
        test.markAsFinal(drugi);
        test.addTransition(pierwszy, koniec, new AnyTransitionLabel());
        test.addTransition(drugi, koniec, new CharTransitionLabel('a'));
        test.addTransition(trzeci, koniec, new EmptyTransitionLabel());
        assertFalse(EpsilonUtilities.isEpsilonTransition(test));
        /*
        * Automat z epsilon przejsciem.
        */
        AutomatonSpecification test2 = new NaiveAutomatonSpecification();
        State koniec2 = test2.addState();
        State pierwszy2 = test2.addState();
        State drugi2 = test2.addState();
        test2.markAsInitial(pierwszy2);
        test2.markAsFinal(drugi2);
        test2.addTransition(pierwszy2, koniec2, new EpsilonTransitionLabel());
        test2.addTransition(drugi2, koniec2, new EpsilonTransitionLabel());
        assertTrue(EpsilonUtilities.isEpsilonTransition(test2));
        /*
        *  Pusty automat.
        */
        AutomatonSpecification test4 = new NaiveAutomatonSpecification();
        assertFalse(EpsilonUtilities.isEpsilonTransition(test4));

        AutomatonSpecification test3 = new NaiveAutomatonSpecification();
        State pierwszy3 = test3.addState();
        State drugi3 = test3.addState();
        test3.markAsInitial(pierwszy3);
        test3.markAsFinal(drugi3);
        test3.addTransition(pierwszy, drugi, new EmptyTransitionLabel());
        assertFalse(EpsilonUtilities.isEpsilonTransition(test3));

        AutomatonSpecification test5 = new NaiveAutomatonSpecification();
        State pierwszy5 = test5.addState();
        State drugi5 = test5.addState();
        State trzeci5 = test5.addState();
        State czwarty5 = test5.addState();
        State piaty5 = test5.addState();
        test5.markAsInitial(pierwszy5);
        test5.markAsFinal(trzeci5);
        test5.addTransition(pierwszy5, drugi5, new CharTransitionLabel('a'));
        test5.addTransition(drugi5, trzeci5, new AnyTransitionLabel());
        test5.addTransition(trzeci5, czwarty5, new EpsilonTransitionLabel());
        test5.addTransition(czwarty5, piaty5, new EmptyTransitionLabel());
        test5.addTransition(piaty5, trzeci5, new EpsilonTransitionLabel());
        assertTrue(EpsilonUtilities.isEpsilonTransition(test5));

        AutomatonSpecification test6 = new NaiveDeterministicAutomatonSpecification();
        assertFalse(EpsilonUtilities.isEpsilonTransition(test6));
    }
}
