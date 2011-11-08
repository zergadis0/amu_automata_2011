package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.LinkedList;

class IndeterministicAutomaton implements Acceptor {

    IndeterministicAutomaton(final AutomatonSpecification specification) {
        automaton = specification;
    }

    @Override
    public boolean accepts(final String text) {
        accept = false;
        int limit = text.length();
        oStates = new LinkedList();
        aStates = new LinkedList();
        oStates.add(automaton.getInitialState());

        for (int i = 0; i < limit; i++) {

            for (State someState : oStates) {
                epStates = new LinkedList();
                epStates = epsilonClosure(someState);

                for (State eState : epStates) {
                    oStates.add(eState);
                }
            }

            for (State someState : oStates) {
                List<OutgoingTransition> someStateTransitions;
                someStateTransitions = automaton.allOutgoingTransitions(someState);

                for (OutgoingTransition transition : someStateTransitions) {
                    if (transition.getTransitionLabel().canAcceptCharacter(text.charAt(i))
                            && !aStates.contains(transition.getTargetState())) {
                        aStates.add(transition.getTargetState());
                    }
                }
            }
        }

        oStates = aStates;
        aStates.clear();

        return accept;
    }

    private List<State> epsilonClosure(State state) {
        List<State> eStates = new LinkedList();
        List<State> cStates = new LinkedList();
        boolean added;

        eStates.add(state);

        do {
            added = false;

            for (State someState : eStates) {
                List<OutgoingTransition> someStateTransitions;
                someStateTransitions = automaton.allOutgoingTransitions(someState);

                for (OutgoingTransition transition : someStateTransitions) {
                    if (transition.getTransitionLabel().canBeEpsilon()) {
                        cStates.add(transition.getTargetState());
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
        } while (added);

        return eStates;
    }
    private List<State> oStates;
    private List<State> aStates;
    private List<State> epStates;
    private final AutomatonSpecification automaton;
    private boolean accept;
};
