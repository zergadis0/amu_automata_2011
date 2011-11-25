package pl.edu.amu.wmi.daut.base;
import java.util.Set;

/**
* Różne pomocnicze funkcje
* związane z automatami deterministycznymi.
*/
public final class DeterministicUtilities {
     /**
     * Tworzy automat deterministyczny, który akceptuje napisy ze zbioru `language`
     * i nie akceptuje żadnych innych napisów. Automat powstaje przez
     * rozbudowanie pustego automatu deterministycznego przekazanego jako argument `automaton`.
     */
    public static void createAutomatonForFiniteLanguage(DeterministicAutomatonSpecification
automaton, Set<String> language) {
    int symbolsCounter = 1;
    for (String s : language) {
        symbolsCounter += s.length();
    }

    State[] q;
    q = new State[symbolsCounter];
    q[0] = automaton.addState();
    automaton.markAsInitial(q[0]);
    int statesCounter = 0;

    for (String s : language) {
        if (s.equals("")) {
            automaton.markAsFinal(q[0]);
        } else {
            State activeState = q[0];
            int letter = 0;

            for ( ; letter < s.length(); letter++) {

                    if (automaton.targetState(activeState, s.charAt(letter)) != null) {
                        activeState = automaton.targetState(activeState, s.charAt(letter));

                    } else {
                    statesCounter++;
                    q[statesCounter] = automaton.addState();
                    automaton.addTransition(activeState, q[statesCounter],
new CharTransitionLabel(s.charAt(letter)));
                    activeState = q[statesCounter];
                    }

            }
            automaton.markAsFinal(activeState);
        }
    }
    }

/**
* Pusty konstruktor klasy DeterministicUtilities
* niebędący konstruktorem domyślnym.
*/
 //   private DeterministicUtilities() {
 //   }
}
