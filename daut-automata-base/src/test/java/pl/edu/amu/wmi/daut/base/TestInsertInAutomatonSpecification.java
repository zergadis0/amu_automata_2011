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
        secondAutomaton.markAsInitial(testState[0]);
        testState[14] = secondAutomaton.addState();
        for (int i=0; i <13; ++i) {
            secondAutomaton.addTransition(testState[i+1], testState[i], 
                    new CharTransitionLabel( (char)((int)('a') + i)));
        }
        
        baseAutomaton.insert(baseState, secondAutomaton);
        //System.out.print(baseAutomaton.getDotGraph());
        assertEquals(baseAutomaton.countStates() , secondAutomaton.countStates() );
        assertEquals(baseAutomaton.countTransitions(), secondAutomaton.countTransitions() );
        List<State> states= baseAutomaton.allStates();
        for (int i=1;i<14;++i) {
            List<OutgoingTransition> edges = baseAutomaton.allOutgoingTransitions(states.get(i));
            assertEquals(edges.size(), 2);
        }
     
    }
    
    public final void testFinalState() {
        NaiveAutomatonSpecification first = new NaiveAutomatonSpecification();
        State f1 = first.addState();
        first.markAsInitial( f1 );
        
        NaiveAutomatonSpecification second = new NaiveAutomatonSpecification();
        State s1 = second.addState();
        State s2 = second.addState();
        second.markAsInitial(s1);
        second.markAsFinal(s2);
        second.addTransition(s1, s2, new CharTransitionLabel('a'));
        first.insert(f1, second);
        List<OutgoingTransition> edges = first.allOutgoingTransitions(f1);
        assertEquals(edges.size(), 1);
        assertEquals(first.isFinal(edges.get(0).getTargetState()), true);
        
    }
    
    
}
