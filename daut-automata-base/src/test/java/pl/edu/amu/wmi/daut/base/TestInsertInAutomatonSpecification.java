package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

public class TestInsertInAutomatonSpecification extends TestCase {

    public final void testAddSimpleAutomaton() {
        
        NaiveAutomatonSpecification baseAutomaton = new NaiveAutomatonSpecification();
        State baseState = baseAutomaton.addState();
        baseAutomaton.markAsInitial(baseState);
        baseAutomaton.markAsFinal(baseState);
        
        NaiveAutomatonSpecification secondAutomaton = new NaiveAutomatonSpecification();
        State[] testState = new State[15];
        for ( int i=0; i<14; ++i) {
            testState[i] = secondAutomaton.addState();
            secondAutomaton.addLoop(testState[i], new CharTransitionLabel((char)((int)('A')+i)));
        }
        testState[14] = secondAutomaton.addState();
        
        for (int i=0; i <13; ++i) {
            secondAutomaton.addTransition(testState[i+1], testState[i], 
                    new CharTransitionLabel( (char)((int)('a') + i)));
        }
        
        baseAutomaton.insert(baseState, secondAutomaton);
        System.out.print(baseAutomaton);
        assertEquals(baseAutomaton.countStates() , secondAutomaton.countStates());
    }
}
