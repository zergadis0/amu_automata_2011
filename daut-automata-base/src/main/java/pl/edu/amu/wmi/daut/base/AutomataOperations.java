package pl.edu.amu.wmi.daut.base;

import java.util.List;
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
            return "A"+String.valueOf(qA.hashCode())+"B"+String.valueOf(qB.hashCode());
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
     * Metoda tworzy przejscie od stanu stateC do nowego stanu utworzonego przez pare stateA i 
     * stateB po etykiecie transition. Sprawdzane jest czy stateA i stateB są stanami końcowymi oraz
     * dodanie nowo utworzonego stanu stateCn do listy 'newStates' wraz z wpisaniem jej oraz jej
     * kombinacji stanów do HashMap.
     */
    private boolean makeTransition(State stateA, State stateB, List newStates,
            TransitionLabel transition, HashMap statesC, HashMap statesCHandle,
            HashMap combinedStatesC, State stateC, AutomatonSpecification automatonC,
            AutomatonSpecification automatonB, AutomatonSpecification automatonA) {
        State stateCn;
        boolean empty = true;
        CombinedState combinedC = new CombinedState();
        combinedC.set(stateA, stateB);
        if (statesC.containsValue(combinedC.toString()))
            stateCn = (State)statesCHandle.get(combinedC.toString());
        else {
            stateCn = automatonC.addState();
            combinedStatesC.put(combinedC.toString(), combinedC);
            statesC.put(stateCn, combinedC.toString());
            statesCHandle.put(combinedC.toString(), stateCn);
            newStates.add(stateCn);
            empty = false;
        }
        automatonC.addTransition(stateC, stateCn, transition);
        if (automatonA.isFinal(stateB)
                    && automatonB.isFinal(stateA))
                automatonC.markAsFinal(stateCn);
        return empty;
    }
    /**
     * Metoda zwracająca automat akceptujący przecięcie języków akceptowanych przez
     * dwa podane automaty.
     */
    public AutomatonSpecification intersection(
            AutomatonSpecification automatonA, AutomatonSpecification automatonB) {

        boolean empty;
        CombinedState combinedC = new CombinedState();
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();

        State qA = automatonA.getInitialState();
        State qB = automatonB.getInitialState();
        State qC = automatonC.addState();
        automatonC.markAsInitial(qC);
        if(automatonA.isFinal(qA) && automatonB.isFinal(qB))
            automatonC.markAsFinal(qC);

        List<OutgoingTransition> lA;
        List<OutgoingTransition> lB;
        List<State> lC = new java.util.LinkedList<State>();
        List<State> temporary = new java.util.LinkedList<State>();
        temporary.add(qC);

        /*
         * combinedStatesC - zawiera łańcuch kontrolny odpowiadający kombinacji stanów A i B
         * statesC - zawiera stan C z łańcuchem kobminacji jego stanów A i B
         * statesCHandle - zawiera uchwyt do stanu C poprzez łańcuch kontrolny jego kombinacji
         * stanów A i B
         */
        HashMap<String, CombinedState> combinedStatesC = new HashMap<String, CombinedState>();
        HashMap<State, String> statesC = new HashMap<State, String>();
        HashMap<String, State> statesCHandle = new HashMap<String, State>();

        combinedC.set(qA, qB);
        combinedStatesC.put(combinedC.toString(), combinedC);
        statesC.put(qC, combinedC.toString());
        statesCHandle.put(combinedC.toString(), qC);

        do {
            lC.addAll(temporary);
            temporary.clear();
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
                            empty = makeTransition(qAn.getTargetState(), qBn.getTargetState(),
                                    temporary, tL, statesC, statesCHandle, combinedStatesC, stateC,
                                    automatonC, automatonB, automatonA);

                            break;
                        }
                    }
                }
                //Epsilon przejscia
                for (OutgoingTransition transitionToAn : lA) {
                    if (transitionToAn.getTransitionLabel().canBeEpsilon()) {
                        empty = makeTransition(transitionToAn.getTargetState(), qB, temporary,
                                new EpsilonTransitionLabel(), statesC, statesCHandle,
                                combinedStatesC, stateC, automatonC, automatonB, automatonA);

                        break;
                    }
                }
                for (OutgoingTransition transitionToBn : lB) {
                    if (transitionToBn.getTransitionLabel().canBeEpsilon()) {
                        empty = makeTransition(qA, transitionToBn.getTargetState(), temporary,
                                new EpsilonTransitionLabel(), statesC, statesCHandle,
                                combinedStatesC, stateC, automatonC, automatonB, automatonA);

                        break;
                    }
                }
            }
            lC.clear();
        } while (!empty);

        return automatonC;
    }
}
