package pl.edu.amu.wmi.daut.base;

import java.util.List;

public class AutomataOperations {

    public AutomatonSpecification sum(AutomatonSpecification A,AutomatonSpecification B) {
		AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        {
            	State q0 = automaton.addState();
        	State qk = automaton.addState();
		automaton.markAsInitial(q0);
        	automaton.markAsFinal(qk);
        Map<State, State> statesA = new HashMap<State,State>();
        Map<State, State> statesB = new HashMap<State,State>();
    for(State s:A.allStates()) {
        State tmp = automaton.addState();
        statesA.put(s,tmp);
        }
    for(State s:B.allStates()) {
        State tmp = automaton.addState();
        statesB.put(s,tmp);
        }
    for(State s: A.allStates()) {
	for(OutgoingTransition ot : A.allOutgoingTransitions(s)) {
            automaton.addTransition(statesA.get(ot), ot.getTargetState(), ot.getTransitionLabel());
	}
}
    for(State s: B.allStates()){
	for(OutgoingTransition ot : B.allOutgoingTransitions(s)) {
            automaton.addTransition(statesB.get(ot),ot.getTargetState(), ot.getTransitionLabel());
	}
}
                /* brak przejsc epsilonowych i brak pomyslu jak to zrobic */
		automaton.addTransition(q0, statesA.get(A.getInitialState()) /*stanpoczatkowy automatu A*/, new TestTransition(''));
		automaton.addTransition(q0, statesB.get(B.getInitialState()) /*stanpoczatkowy automatu B*/, new TestTransition(''));
                for (State s : A.allStates()) {
                    if(A.isFinal(s))
                    automaton.addTransition(s /*stan koncowyautomatu A*/, qk, new TestTransition(''));
                }
                for (State s : B.allStates()) {
                    if(A.isFinal(s))
                    automaton.addTransition(s /*stan koncowyautomatu B*/, qk, new TestTransition(''));
                }	
                //automaton.addTransition(statesA.get(A.getFinalState()) /*stan koncowyautomatu A*/,qk, new TestTransition(''));
		//automaton.addTransition(statesB.get(B.getFinalState()) /*stan koncowyautomatu B*/,qk, new TestTransition(''));
        }	
        return automaton;
    }
}