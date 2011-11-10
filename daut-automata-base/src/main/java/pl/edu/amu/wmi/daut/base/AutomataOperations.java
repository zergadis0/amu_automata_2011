package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * Klasa zwierająca operacje na automatach.
 */
public class AutomataOperations {

    /**
     * Klasa reprezentuje stan C powstały poprzez połączenie stanów A i B w wyniku operacji
     * intersection.
     */
    private static final class CombinedState {

        /**
         * Przypisuje stanowi C jego składowe stany A i B.
         */
        public void set(State a, State b) {
            qA = a;
            qB = b;
        }
        @Override
        public String toString() {
            return "A" + String.valueOf(qA.hashCode()) + "B" + String.valueOf(qB.hashCode());
        }
        public State getB() {
            return qB;
        }
        public State getA() {
            return qA;
        }
        private State qA;
        private State qB;
    }

    /**
     * Metoda tworzy przejscie od stanu stateC do nowego stanu utworzonego przez pare A i B w
     * combinedC po etykiecie transition. Dodanie nowo utworzonego stanu stateCn do listy newStates
     * wraz z wpisaniem jej oraz jej kombinacji stanów do HashMap.
     * hashMaps - 0 - statesC, 1 - statesCHandle, 2 - combinedStatesC
     */
    private static boolean makeTransition(CombinedState combinedC, List newStates,
            TransitionLabel transition, List<HashMap> hashMaps, State stateC,
            AutomatonSpecification automatonC, boolean isFinal) {
        State stateCn;
        boolean empty = true;
        if (hashMaps.get(0).containsValue(combinedC.toString()))
            stateCn = (State) hashMaps.get(1).get(hashMaps.get(2).toString());
        else {
            stateCn = automatonC.addState();
            hashMaps.get(2).put(combinedC.toString(), combinedC);
            hashMaps.get(0).put(stateCn, combinedC.toString());
            hashMaps.get(1).put(combinedC.toString(), stateCn);
            newStates.add(stateCn);
            empty = false;
        }
        automatonC.addTransition(stateC, stateCn, transition);
        if (isFinal)
                automatonC.markAsFinal(stateCn);
        return empty;
    }
    /**
     * Metoda zwracająca automat akceptujący przecięcie języków akceptowanych przez
     * dwa podane automaty.
     */
    public static AutomatonSpecification intersection(
            AutomatonSpecification automatonA, AutomatonSpecification automatonB) {

        boolean empty, isFinal = false;
        CombinedState combinedC = new CombinedState();
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();

        State qA = automatonA.getInitialState();
        State qB = automatonB.getInitialState();
        State qC = automatonC.addState();
        automatonC.markAsInitial(qC);
        if (automatonA.isFinal(qA) && automatonB.isFinal(qB))
            automatonC.markAsFinal(qC);

        List<OutgoingTransition> lA;
        List<OutgoingTransition> lB;
        List<State> lC = new java.util.LinkedList<State>();
        List<State> newStates = new java.util.LinkedList<State>();
        newStates.add(qC);

        /*
         * combinedStatesC - zawiera łańcuch kontrolny odpowiadający kombinacji stanów A i B
         * statesC - zawiera stan C z łańcuchem kobminacji jego stanów A i B
         * statesCHandle - zawiera uchwyt do stanu C poprzez łańcuch kontrolny jego kombinacji
         * stanów A i B
         */
        HashMap<String, CombinedState> combinedStatesC = new HashMap<String, CombinedState>();
        HashMap<State, String> statesC = new HashMap<State, String>();
        HashMap<String, State> statesCHandle = new HashMap<String, State>();
        List<HashMap> hashMaps = new LinkedList<HashMap>();
        hashMaps.add(statesC);
        hashMaps.add(statesCHandle);
        hashMaps.add(combinedStatesC);

        combinedC.set(qA, qB);
        combinedStatesC.put(combinedC.toString(), combinedC);
        statesC.put(qC, combinedC.toString());
        statesCHandle.put(combinedC.toString(), qC);

        do {
            lC.addAll(newStates);
            newStates.clear();
            empty = true;

            for (State stateC : lC) {
                combinedC = combinedStatesC.get(statesC.get(stateC));
                qA = combinedC.getA();
                qB = combinedC.getB();
                lA = automatonA.allOutgoingTransitions(qA);
                lB = automatonB.allOutgoingTransitions(qB);

                for (OutgoingTransition qAn : lA) {
                    for (OutgoingTransition qBn : lB) {

                        TransitionLabel tL = qAn.getTransitionLabel().intersect(
                                qBn.getTransitionLabel());

                        if (!tL.isEmpty()) {
                            combinedC = new CombinedState();
                            combinedC.set(qAn.getTargetState(), qBn.getTargetState());
                            if (automatonA.isFinal(qAn.getTargetState())
                                    && automatonB.isFinal(qBn.getTargetState()))
                                isFinal = true;
                            else
                                isFinal = false;
                            empty = makeTransition(combinedC,
                                    newStates, tL, hashMaps, stateC,
                                    automatonC, isFinal);

                            break;
                        }
                    }
                }
                //Epsilon przejscia
                for (OutgoingTransition transitionToAn : lA) {
                    if (transitionToAn.getTransitionLabel().canBeEpsilon()) {
                        combinedC = new CombinedState();
                        combinedC.set(transitionToAn.getTargetState(), qB);
                        if (automatonA.isFinal(transitionToAn.getTargetState())
                                && automatonB.isFinal(qB))
                            isFinal = true;
                        else
                            isFinal = false;
                        empty = makeTransition(combinedC, newStates,
                                new EpsilonTransitionLabel(), hashMaps, stateC, automatonC,
                                isFinal);

                        break;
                    }
                }
                for (OutgoingTransition transitionToBn : lB) {
                    if (transitionToBn.getTransitionLabel().canBeEpsilon()) {
                        combinedC = new CombinedState();
                        combinedC.set(qA, transitionToBn.getTargetState());
                        if (automatonA.isFinal(qA)
                                && automatonB.isFinal(transitionToBn.getTargetState()))
                            isFinal = true;
                        else
                            isFinal = false;
                        empty = makeTransition(combinedC, newStates,
                                new EpsilonTransitionLabel(), hashMaps, stateC, automatonC,
                                isFinal);

                        break;
                    }
                }
            }
            lC.clear();
        } while (!empty);

        return automatonC;
    }
    /**
     * Zwraca automat akceptujący domknięcie Kleene'ego
     * języka akceptowanego przez dany automat.
     */
    public AutomatonSpecification getKleeneStar(AutomatonSpecification automaton) {
        AutomatonSpecification kleeneautomaton = new NaiveAutomatonSpecification();
        State state1 = kleeneautomaton.addState();
        kleeneautomaton.markAsInitial(state1);
        kleeneautomaton.markAsFinal(state1);
        if (!automaton.isEmpty()) {
            State state2 = kleeneautomaton.addState();
            kleeneautomaton.addTransition(state1, state2, new EpsilonTransitionLabel());
            kleeneautomaton.insert(state2, automaton);
            for (State state : automaton.allStates()) {
                if (automaton.isFinal(state)) {
                    kleeneautomaton.addTransition(state, state1, new EpsilonTransitionLabel());
                }
            }
        }
        return kleeneautomaton;
    }
}
