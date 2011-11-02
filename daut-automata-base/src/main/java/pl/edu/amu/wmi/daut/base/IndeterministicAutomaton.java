package pl.edu.amu.wmi.daut.base;

import java.util.List;

import javax.smartcardio.CardTerminals.State;

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
				List<OutgoingTransition> someStateTransitions;
				someStateTransitions = automaton.allOutgoingTransitions(someState);
				for (OutgoingTransition transition : someStateTransitions) {
					if (transition.getTransitionLabel().canAcceptCharacter(text.charAt(i))) {
						aStates.add(transition.getTargetState());
					}
				}
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
	
	public List<State> GetStatesAfterAccepts() {
		return oStates;
	}
	
	private List<State> oStates;
	private List<State> aStates;
	private final AutomatonSpecification automaton;
	private boolean accept;
};