package pl.edu.amu.wmi.daut.base;

import java.util.List;

/**
 * Klasa zwierająca operacje na automatach.
 */
public class AutomataOperations {

    /**
     * Metoda zwracająca automat akceptujący przecięcie języków akceptowanych przez
     * dwa podane automaty.
     */
    public AutomatonSpecification intersection(AutomatonSpecification automatonA,
            AutomatonSpecification automatonB) {
        AutomatonSpecification automatonC = new NaiveAutomatonSpecification();
        
        State qA = automatonA.getInitialState();
        State qB = automatonB.getInitialState();
        List<OutgoingTransition> lA = allOutgoingTransitions(qA);
        List<OutgoingTransition> lB = allOutgoingTransitions(qB);
        List<OutgoingTransition> lC;

        for(State qAn: LA.()) {
            for(State qBn: LB.()) {
                if(qAn.intersect(qBn)) {
                    State qC = qA...qB
                    if(qAn.isFinal() && qBn.isFinal())
                        qC.markAsFinal();
                    lC.add(qC);
                }
            }
        }
        if (isFinal(qAn) && isFinal(qBn))
            markAsFinal(qC);
        if(lA.isEmpty() || lB.isEmpty())
            ;
        //koniec

        return automatonC;
    }
}
