

import java.util.List;

/**
 * Klasa zwierająca operacje na automatach.
 */
public class AutomataOperations {

    /**
     * Reprezentuje stan C powstały poprzez połączenie stanów A i B w wyniku operacji
     * intersection.
     */
    private class Structure
    {
        /**
         * Konstruktor
         */
        public Structure(State a, State b, State c)
        {
            qA = c;
            qB = b;
            qC = c;
        }
        protected State qA;
        protected State qB;
        protected State qC;
    }
    /**
     * Metoda zwracająca automat akceptujący przecięcie języków akceptowanych przez
     * dwa podane automaty.
     */
    public AutomatonSpecification intersection(AutomatonSpecification automatonA,
            AutomatonSpecification automatonB) {

        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
        State qA = automatonA.getInitialState();
        State qB = automatonB.getInitialState();
        State qC = automatonC.addState();
        automatonC.markAsInitial(qC);
        Structure stanQC = new Structure(qA, qB, qC);

        List<Structure> lC = null;
        List<OutgoingTransition> lA;
        List<OutgoingTransition> lB;
        lC.add(stanQC);

        for(Structure struct: lC)
        {
            lA = automatonA.allOutgoingTransitions(struct.qA);
            lB = automatonB.allOutgoingTransitions(struct.qB);

            for(OutgoingTransition qAn: lA) {
                for(OutgoingTransition qBn: lB) {
                    TransitionLabel tL = qAn.getTransitionLabel().intersect(qBn.getTransitionLabel());
                    if(!tL.isEmpty()) {
                        State qCn = automatonC.addState();
                        automatonC.addTransition(struct.qC, qCn, tL);
                        if(automatonA.isFinal(qAn.getTargetState()) && automatonB.isFinal(qBn.getTargetState()))
                            automatonC.markAsFinal(qCn);
                        stanQC = new Structure(qAn.getTargetState(), qBn.getTargetState(), qCn);
                        lC.add(stanQC);
                    }
                }
            }
        }
        return automatonC;
    }
}
