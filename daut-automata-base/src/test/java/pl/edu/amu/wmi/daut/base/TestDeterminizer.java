package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy Determinizer
 */
public class TestDeterminizer extends TestCase {
    
    //Zestaw testow jakimi bedziemy poddawac zdeterminizowany automat
    public final void setOfTests(AutomatonSpecification nfa, int numberOfStates, int numberOfTransitions, String acceptedWord, String notAcceptedWord) {

        Determinizer determinizer =  new Determinizer();
        DeterministicAutomatonSpecification ndfa = new NaiveDeterministicAutomatonSpecification();
        
        determinizer.determinize(nfa, ndfa);

        if(ndfa.isEmpty()) {
            //assertFalse(ndfa.isNotEmpty());// dziwnie dziala??
            assertTrue(ndfa.isEmpty());
        } else {
            assertTrue(ndfa.isDeterministic());
            //assertTrue(ndfa.isNotEmpty());
            assertFalse(ndfa.isEmpty());
        }
        
        DeterministicAutomaton dfa = new DeterministicAutomaton(ndfa);

        if(acceptedWord != null & notAcceptedWord != null)
        {
            assertTrue(dfa.accepts(acceptedWord));
            assertFalse(dfa.accepts(notAcceptedWord));
        }

        //assertFalse(ndfa.uselessStates());  //metoda ma bledy z LinkedList outofBound

        assertEquals(ndfa.countTransitions(), numberOfTransitions);
        assertEquals(ndfa.countStates(), numberOfStates);
    }


    //Pusty automat
    public final void test_1() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();

        setOfTests(nfa, 0, 0, null, null);
    }
    
    //Deterministyczny automat
    public final void test_2() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();
        
        State q0 = nfa.addState();
        State q1 = nfa.addState();   

        nfa.markAsInitial(q0);
        nfa.markAsFinal(q1);

        nfa.addTransition(q0, q1, new CharTransitionLabel('a'));
        nfa.addLoop(q1, new CharTransitionLabel('b'));

        setOfTests(nfa, 2, 2, "abbb", "aba");
    }
    //Prosty nfa 3 stanowy (przyklad z wykladu 14.11.2011) 
    public final void test_3() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();
        
        State q0 = nfa.addState();
        State q1 = nfa.addState();   
        State q2 = nfa.addState();

        nfa.markAsInitial(q0);
        nfa.markAsFinal(q2);

        nfa.addLoop(q0, new CharTransitionLabel('0'));
        nfa.addLoop(q0, new CharTransitionLabel('1'));
        nfa.addTransition(q0, q1, new CharTransitionLabel('1'));
        nfa.addTransition(q1, q2, new CharTransitionLabel('0'));
        nfa.addTransition(q1, q2, new CharTransitionLabel('1'));
        
        setOfTests(nfa, 4, 8, "111010", "111101");
    }
    
    public final void test_4() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();
        
        State q0 = nfa.addState();
        State q1 = nfa.addState();   
        State q2 = nfa.addState();

        nfa.markAsInitial(q0);
        nfa.markAsFinal(q2);

        nfa.addLoop(q0, new CharTransitionLabel('b'));
        nfa.addLoop(q0, new CharTransitionLabel('c'));
        nfa.addTransition(q0, q1, new CharTransitionLabel('a'));
        nfa.addTransition(q1, q2, new CharTransitionLabel('a'));
        nfa.addTransition(q2, q1, new CharTransitionLabel('b'));
        nfa.addTransition(q2, q1, new CharTransitionLabel('c'));
        nfa.addLoop(q2, new CharTransitionLabel('b'));
        nfa.addLoop(q2, new CharTransitionLabel('c'));

        setOfTests(nfa, 4, 9, "bccbbcaabccccbcbcbcbcccbcabc", "bcbcbcabcbcbcbcaaccbcbc");
    }
    
    public final void test_5() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();
        
        State q0 = nfa.addState();
        State q1 = nfa.addState();   
        State q2 = nfa.addState();
        State q3 = nfa.addState();
        
        nfa.markAsInitial(q0);
        nfa.markAsFinal(q3);

        nfa.addTransition(q0, q1, new CharTransitionLabel('a'));
        nfa.addTransition(q0, q3, new CharTransitionLabel('a'));
        nfa.addLoop(q1, new CharTransitionLabel('a'));
        nfa.addTransition(q1, q2, new CharTransitionLabel('b'));
        nfa.addTransition(q2, q1, new CharTransitionLabel('a'));
        nfa.addTransition(q2, q3, new CharTransitionLabel('c'));
        nfa.addLoop(q3, new CharTransitionLabel('c'));

        setOfTests(nfa, 5, 9, "aabaaaabc", "abbcc");
    }
    /*
    //NFA akceptujacy sume dwóch jezyków (wykorzystano e-przejscia)
    public final void test_3() {
        AutomatonSpecification nfa = new NaiveAutomatonSpecification();
        
        State q0 = nfa.addState();
        State q1 = nfa.addState();   
        State q2 = nfa.addState();
        State q3 = nfa.addState();
        State q4 = nfa.addState();
        State q5 = nfa.addState();
        State q6 = nfa.addState();
        
        nfa.markAsInitial(q0);
        nfa.markAsFinal(q6);
        
        nfa.addTransition(q0, q1, new EpsilonTransitionLabel());
        nfa.addLoop(q1, new CharTransitionLabel('a'));
        nfa.addTransition(q1, q2, new CharTransitionLabel('b'));
        nfa.addLoop(q2, new CharTransitionLabel('a'));
        nfa.addLoop(q2, new CharTransitionLabel('b'));

        nfa.addTransition(q1, q6, new EpsilonTransitionLabel());

        nfa.addTransition(q0, q3, new EpsilonTransitionLabel());
        nfa.addTransition(q3, q4, new CharTransitionLabel('a'));
        nfa.addTransition(q4, q3, new CharTransitionLabel('b'));
        nfa.addTransition(q4, q5, new CharTransitionLabel('a'));
        nfa.addTransition(q3, q5, new CharTransitionLabel('b'));
        nfa.addLoop(q5, new CharTransitionLabel('a'));
        nfa.addLoop(q5, new CharTransitionLabel('b'));

        nfa.addTransition(q3, q6, new EpsilonTransitionLabel());

        setOfTests(nfa, 6, 12, "aaabbb", "aab");
    }
    */

    public final void testDeterminize() {
        
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
    }
    
}

