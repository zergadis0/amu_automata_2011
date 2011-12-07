package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class AutomatonUtilities {

    public static Set<Character> getAlphabet(AutomatonSpecification automaton, Set<Character> superset) {

        Set<Character> alphabet = new HashSet<Character>();

        for (State s : automaton.allStates()) {
            for (OutgoingTransition ot : automaton.allOutgoingTransitions(s)) {
                for (Character c : superset) {

                        boolean isAlreadyIn = false;
                        for(Character ch : alphabet)
                        if(ch == c) {
                            isAlreadyIn = true;
                            break;
                        }

			if(!isAlreadyIn)
                            if (ot.getTransitionLabel().canAcceptCharacter(c)) {
				alphabet.add(c);
				continue;
                            }
		}
            }
        }
        return alphabet;
    }

}
