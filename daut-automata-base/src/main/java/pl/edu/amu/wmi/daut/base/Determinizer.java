package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Klasa do determinizacji NFA.
 */
public class Determinizer {

    /**
     * Lista zbiorow stanow.
     */
    private List<StateSet> listOfSets = new LinkedList<StateSet>();

    /**
     * Porownanie dwoch TransitioLabel; aktualnie prymitywne, bo moze byc tylko CharTransitionLabel.
     * @return
     * Jedna z TransitionLabel jesli przechodza po tym samym lub null.
     */
    public TransitionLabel equalTrLabel(TransitionLabel trA, TransitionLabel trB) {
        if (trA != trB) {
            return trA.intersect(trB);
        }
        return trA;
    }

    /**
     * Sprawdza, czy zbior sS juz istnieje i zwraca ten zbior; null jesli nie istnieje.
     */
    public StateSet existsSet(StateSet sS) {
        for (StateSet tmpSS : listOfSets) {
            if (tmpSS.isEqual(sS)) {
                return tmpSS;
            }
        }
        return null;
    }

    /**
     * Dla nieterministycznego automatu skończenie stanowego nfa
     * tworzy równoważny automat deterministyczny.
     * Automat deterministyczny będzie tworzony poprzez rozbudowywanie pustego automatu emptyDfa.
     */
    public void determinize(AutomatonSpecification nfa,
            DeterministicAutomatonSpecification emptyDfa) {
        if (nfa.isDeterministic()) {
            emptyDfa = (DeterministicAutomatonSpecification) nfa.clone();
        } else {

            Queue<StateSet> queueOfNewSets = new LinkedList<StateSet>();
            StateSet initialSet = new StateSet(nfa.getInitialState());
            initialSet.setThatState(emptyDfa.addState());
            emptyDfa.markAsInitial(initialSet.getThatState());
            if (nfa.isFinal(nfa.getInitialState())) {
                emptyDfa.markAsFinal(initialSet.getThatState());
            }

            listOfSets.add(initialSet);
            queueOfNewSets.offer(initialSet);

            StateSet currentStSet;

            while ((currentStSet = queueOfNewSets.poll()) != null) {

                Set<TransitionLabel> setsTrLabels = new HashSet<TransitionLabel>();

                for (State tmpSt : currentStSet.getStateSet()) {
                    for (OutgoingTransition tmpOuTra : nfa.allOutgoingTransitions(tmpSt)) {
                        setsTrLabels.add(tmpOuTra.getTransitionLabel());
                    }
                }

                StateSet newStSet = new StateSet();
                for (TransitionLabel currentTrLabel : setsTrLabels) {
                    for (State tmpState : currentStSet.getStateSet()) {
                        for (OutgoingTransition tmpOuTr : nfa.allOutgoingTransitions(tmpState)) {
                            if (equalTrLabel(currentTrLabel, tmpOuTr.getTransitionLabel())
                                    != null) {
                                newStSet.add(tmpOuTr.getTargetState());
                            }
                        }
                    }

                    StateSet foundStSet = existsSet(newStSet);
                    if (foundStSet != null) {
                        newStSet = foundStSet;
                    } else {
                        listOfSets.add(newStSet);
                        queueOfNewSets.offer(newStSet);
                        newStSet.setThatState(emptyDfa.addState());
                        for (State finalState : newStSet.getStateSet()) {
                            if (nfa.isFinal(finalState)) {
                                emptyDfa.markAsFinal(newStSet.getThatState());
                                break;
                            }
                        }
                    }
                    emptyDfa.addTransition(currentStSet.getThatState(), newStSet.getThatState(),
                            currentTrLabel);
                }
            }
        }
    }

    /**
     * Klasa reprezentujaca zbior stanow wykorzystywana przy determinizacji.
     */
    public class StateSet {

        private Set<State> stateSet;
        private State thatState;

        public State getThatState() {
            return thatState;
        }

        public void setThatState(State s) {
            thatState = s;
        }

        public StateSet() {
            stateSet = new HashSet<State>();
        }

        public StateSet(State s) {
            stateSet = new HashSet<State>();
            stateSet.add(s);
        }

        public StateSet(StateSet sS) {
            stateSet = new HashSet<State>();
            stateSet.addAll(sS.stateSet);
        }

        public Set<State> getStateSet() {
            return stateSet;
        }

        public boolean add(State s) {
            return stateSet.add(s);
        }

        public boolean remove(State s) {
            return stateSet.remove(s);
        }

        public boolean contains(State s) {
            return stateSet.contains(s);
        }

        public boolean isEqual(StateSet sS) {
            if (sS.stateSet.isEmpty() && this.stateSet.isEmpty()) {
                return true;
            }
            return stateSet.equals(sS.stateSet);
        }
    }
}
