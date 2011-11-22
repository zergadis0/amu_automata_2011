package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.List;

abstract class DeterministicAutomatonSpecification extends AutomatonSpecification {
    /**
     * Zwraca stan, jaki zostanie osiągnięty przy przejściu ze stanu
     * from przez znak c. Zwraca null, jeśli nie istnieje przejście
     * ze stanu from przez znak c.
     */
    public abstract State targetState(State from, char c);
    public List<State> findPreviousState(DeterministicAutomatonSpecification automaton,
            State nextState) {
        List<State> previousStates = new ArrayList<State>();
        for (State state : automaton.allStates()) {
            for (OutgoingTransition transition : automaton.allOutgoingTransitions(state)) {
                if (transition.getTargetState() == nextState) {
                    previousStates.add(state);
                }
            }
        }
        return previousStates;
    }
    public List<OutgoingTransition> findPreviousStateTransitions(
            DeterministicAutomatonSpecification automaton,
            State previousState, State nextState) {
        List<OutgoingTransition> needTransitions = new ArrayList<OutgoingTransition>();
        for (OutgoingTransition transition : automaton.allOutgoingTransitions(previousState)) {
            if (transition.getTargetState() == nextState)
                needTransitions.add(transition);
        }
        return needTransitions;
    }
    public void deleteUselessStates(DeterministicAutomatonSpecification automaton) {
        /*Sprawdzamy czy automat nie posiada zbednych stanow, to jest takich
                do ktorych nie mozna dojsc ze stanu poczatkowego, jesli tak usuwamy je*/
        State startState = automaton.getInitialState();
        List<State> startStates = new ArrayList<State>();
        List<State> uselessStates = new ArrayList<State>();
        uselessStates.addAll(automaton.allStates());
        uselessStates.remove(automaton.getInitialState());
        for (OutgoingTransition transition : automaton.allOutgoingTransitions(startState)) {
            startStates.add(transition.getTargetState());
            uselessStates.remove(transition.getTargetState());
        }
        while (!startStates.isEmpty()) {
            for (int i = 0; i < startStates.size(); i++) {
                for (OutgoingTransition transition : automaton
                        .allOutgoingTransitions(startStates.get(i))) {
                    if (!startStates.contains(transition.getTargetState())) {
                        startStates.add(transition.getTargetState());
                        uselessStates.remove(transition.getTargetState());
                    }
                }
                startStates.remove(startStates.get(i));
            }
        }
        if (!uselessStates.isEmpty())
            automaton.allStates().removeAll(uselessStates);
    }
    public DeterministicAutomatonSpecification makeMinimal(
            DeterministicAutomatonSpecification automaton) {
        DeterministicAutomatonSpecification returnAutomaton;
        deleteUselessStates(automaton);
        //Szukamy stanow rownowaznych
        int size = automaton.allStates().size() - 1;
        boolean[][] mark = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int a = 0; a < size; a++)
            mark[i][a] = true;
        }
        for (int i = 0; i < size; i++) {
            if (i + 1 < size) {
                for (int a = (i + 1); a < size; a++) {
                    if (automaton.allStates().get(i) == automaton.allStates().get(a)) {
                        break;
                    }
                    if (automaton.isFinal(automaton.allStates().get(i))
                            && !automaton.isFinal(automaton.allStates().get(a))) {
                        mark[i][a] = false;
                        break;
                    }
                    if (!automaton.isFinal(automaton.allStates().get(i))
                            && automaton.isFinal(automaton.allStates().get(a))) {
                        mark[i][a] = false;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (i + 1 < size) {
                for (int a = (i + 1); a < size; a++) {
                    for (OutgoingTransition itransition : automaton
                            .allOutgoingTransitions(automaton.allStates().get(i))) {
                        for (OutgoingTransition atransition : automaton
                                .allOutgoingTransitions(automaton.allStates().get(a))) {
                            State state1 = itransition.getTargetState();
                            State state2 = atransition.getTargetState();
                            if (state1 == state2)
                                break;
                            if (!mark[automaton.allStates().indexOf(state1) - 1]
                                    [automaton.allStates().indexOf(state2) - 1])
                                mark[i][a] = false;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < size; i++)
            mark[i][i] = false;
        returnAutomaton = automaton;
        for (int i = 0; i < size; i++) {
            if (i + 1 < size) {
                for (int a = (i + 1); a < size; a++) {
                    if (mark[i][a]) {
                        List<State> prevStates;
                        List<OutgoingTransition> prevTransitions;
                        prevStates = findPreviousState(returnAutomaton, returnAutomaton
                                .allStates().get(a));
                        for (State state : prevStates) {
                            prevTransitions = findPreviousStateTransitions(
                                    returnAutomaton, state, returnAutomaton.allStates().get(a));
                            for (OutgoingTransition transition : prevTransitions)
                                returnAutomaton.addTransition(
                                        state, returnAutomaton.allStates().get(i),
                                        transition.getTransitionLabel());
                            }
                        returnAutomaton.allStates().remove(a);
                    }
                }
            }
        }
        return returnAutomaton;
    }
}
