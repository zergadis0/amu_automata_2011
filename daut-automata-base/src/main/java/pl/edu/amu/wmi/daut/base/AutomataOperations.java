package pl.edu.amu.wmi.daut.base;

import java.util.List;

/**
 * Klasa zwierająca operacje na automatach.
 */
public class AutomataOperations {

    /**
     * Reprezentuje stan C powstały poprzez połączenie stanów A i B w wyniku operacji
     * intersection.
     */
    protected static class Structure {
        /**
         * Domyślny konstruktor.
         */
        private Structure(){
            
        }
        /**
         * Przypisuje stanowi C jego składowe stany A i B.
         */
        public void set(State a, State b, State c) {
            qA = a;
            qB = b;
            qC = c;
        }
        private State qA;
        private State qB;
        private State qC;
    }
    /**
     * Metoda zwracająca automat akceptujący przecięcie języków akceptowanych przez
     * dwa podane automaty.
     */
    public static AutomatonSpecification intersection(AutomatonSpecification automatonA,
            AutomatonSpecification automatonB) {
        boolean empty;
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
        State qA = automatonA.getInitialState();
        State qB = automatonB.getInitialState();
        State qC = automatonC.addState();
        automatonC.markAsInitial(qC);
        Structure stanQC = new Structure();
        stanQC.set(qA, qB, qC);

        List<Structure> lC = new java.util.LinkedList<Structure>();
        List<Structure> temporary = new java.util.LinkedList<Structure>();
        List<OutgoingTransition> lA;
        List<OutgoingTransition> lB;
        temporary.add(stanQC);

        do {
            lC.addAll(temporary);
            temporary.clear();
            empty = true;
            for (Structure struct : lC) {
                lA = automatonA.allOutgoingTransitions(struct.qA);
                lB = automatonB.allOutgoingTransitions(struct.qB);

                for (OutgoingTransition qAn : lA) {
                    for (OutgoingTransition qBn : lB) {
                        TransitionLabel tL = qAn.getTransitionLabel().intersect(
                                qBn.getTransitionLabel());
                        if (!tL.isEmpty()) {
                            State qCn = automatonC.addState();
                            automatonC.addTransition(struct.qC, qCn, tL);
                            if (automatonA.isFinal(qAn.getTargetState())
                                    && automatonB.isFinal(qBn.getTargetState()))
                                automatonC.markAsFinal(qCn);
                            stanQC = new Structure();
                            stanQC.set(qAn.getTargetState(), qBn.getTargetState(), qCn);
                            temporary.add(stanQC);
                            empty = false;
                        }
                    }
                }
            }
            lC.clear();
        } while (!empty);
        return automatonC;
    }
}
