package pl.edu.amu.wmi.daut.base;

import java.util.List;
/**
 *
 * @author iwanicki
 */
public class EpsilonUtilities {
    /**
     * Metoda sprawdzajaca, czy dany automat 
     * ma jakies epsilon-przejscie.
     */
    public boolean isEpsilonTransition(AutomatonSpecification automat) {
        List<State> states = automat.allStates();

        if (states.isEmpty())
            return false;

        for (State state : states) {
            List<OutgoingTransition> transitions = automat.allOutgoingTransitions(state);

            for (int i = 0; i < transitions.size(); ++i) {
                TransitionLabel label = transitions.get(i).getTransitionLabel();

                if (label.canBeEpsilon())
                    return true;
            }
        }
        return false;
    }
}
