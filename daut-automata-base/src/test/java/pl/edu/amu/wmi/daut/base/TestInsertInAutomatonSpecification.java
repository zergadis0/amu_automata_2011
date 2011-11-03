package pl.edu.amu.wmi.daut.base;

import java.util.List;

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
        assertEquals(baseAutomaton.countStates() , secondAutomaton.countStates() );
        assertEquals(baseAutomaton.countTransitions(), secondAutomaton.countTransitions());
     
    }
    
    public final void testLabel() {
        NaiveAutomatonSpecification first = new NaiveAutomatonSpecification();
        State f1 = first.addState();
        first.markAsInitial( f1 );
        
        NaiveAutomatonSpecification second = new NaiveAutomatonSpecification();
        State s0 = second.addState();
        State s1 = second.addState();
        second.addTransition(s0, s1, new CharTransitionLabel('a'));
        first.insert(f1, second);
        assertEquals(first.allOutgoingTransitions(f1).get(0).getTransitionLabel(), 
                second.allOutgoingTransitions(s0).get(0).getTransitionLabel());
    }
    
    
}
