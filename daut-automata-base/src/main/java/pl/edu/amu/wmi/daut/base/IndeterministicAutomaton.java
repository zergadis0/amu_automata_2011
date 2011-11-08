package pl.edu.amu.wmi.daut.base;

import java.util.List;

class IndeterministicAutomaton implements Acceptor{
	
	IndeterministicAutomaton(final AutomatonSpecification specification) {
		automaton = specification;
	}
	
	@Override
	public boolean Accepts(final String text) {
		accept = false;
		int limit = text.length();
		oStates.clear();
		oStates.add(automaton.getInitialState());
		
		for (int i=0; i < limit; i++) {
			
			for (State someState : oStates) {
				List<State> eStates;
				eStates = EpsilonClosure(someState);
				
				for (State eState : eStates) 
					oStates.add(eState);
			}
			
			for (State someState : oStates) {
				List<OutgoingTransition> someStateTransitions;
				someStateTransitions = automaton.allOutgoingTransitions(someState);
				
				for (OutgoingTransition transition : someStateTransitions) 
					if (transition.getTransitionLabel().canAcceptCharacter(text.charAt(i))) 
						if (!aStates.contains(transition.getTargetState()))
							aStates.add(transition.getTargetState());
			}
			
			if (aStates.isEmpty())
				return false;
			
			oStates = aStates;
			aStates.clear();
		}
		
		for (State someState : oStates)
			if (automaton.isFinal(someState))
				accept = true;
					
		return accept;
	}
	
	private List<State> EpsilonClosure(State state) {
		List<State> eStates;
		List<State> cStates;
		boolean added;
		
		eStates.add(state);
		
		do {
			added = false;
			
			for (State someState : eStates) {
				List<OutgoingTransition> someStateTransitions;
				someStateTransitions = automaton.allOutgoingTransitions(someState);
					
				for (OutgoingTransition transition : someStateTransitions) {
					if (transition.getTransitionLabel().canBeEpsilon()) {
						cStates.add(transistion.getTargetState());
					}
				}
					
				for (State cState : cStates) {
					if (!eStates.contains(cState)) {
						eStates.add(cState);
						added = true;
					}
				}
				cStates.clear();
			}
		} while(added)
			
	return eStates;
	
	}
	
	private List<State> oStates;
	private List<State> aStates;
	private final AutomatonSpecification automaton;
	private boolean accept;
};