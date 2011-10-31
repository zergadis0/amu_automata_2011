

package pl.edu.amu.wmi.daut.base;

import java.util.Set;

/**
 *
 * @author drzejzi
 */


public class DeterministicUtilities {
     /**
     * Tworzy automat deterministyczny, który akceptuje napisy ze zbioru `language` i nie akceptuje żadnych innych napisów.
     * Automat powinien powstać przez rozbudowanie pustego automatu deterministycznego przekazanego jako argument `automaton`.
     */
	
    public void createAutomatonForFiniteLanguage(DeterministicAutomatonSpecification automaton, Set<String> language) {
    int SYMBOLS_COUNTER=0;
    for (String s : language) {
    	SYMBOLS_COUNTER+=s.length();
    }
    State q[] = new State[SYMBOLS_COUNTER];
    automaton.markAsInitial(q[0]);
    int STATES_COUNTER = 0;
    for (String s : language) {
    	int ACTIVE_STATE = 0;
    	for(; ACTIVE_STATE < s.length(); ACTIVE_STATE++) {
    		boolean LEAVE = false;
    		for (int SEARCH=0; SEARCH <= STATES_COUNTER; SEARCH++) {
    			if (automaton.targetState(q[ACTIVE_STATE], s.charAt(ACTIVE_STATE)) != null) {
    				ACTIVE_STATE = SEARCH;
    				LEAVE = true;
    				break;
    			}
    		}
    	if (LEAVE == false) {
    		STATES_COUNTER++;
    		q[STATES_COUNTER] = automaton.addState();
    		automaton.addTransition(q[ACTIVE_STATE], q[STATES_COUNTER], new CharTransitionLabel(s.charAt(ACTIVE_STATE)));
    	}
    	}
    	automaton.markAsFinal(q[STATES_COUNTER]);
    }
    
    }

}
