package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.Stack;
/**
 * klasa decyduąjca czy automat zaakceptuje dany napis.
 */
public final class AutomatonByRecursion implements Acceptor {
    AutomatonByRecursion(final AutomatonSpecification  specification) {
        automaton = specification;
        actuallyState  = automaton.getInitialState();
        actuallyStates.push(actuallyState);
    }
    /**
     * metoda rozsztrzyga czy automat akceptuje dany napis.
     */
    @Override
    public boolean accepts(final String text) {
        final char[] characters = text.toCharArray();
        check(characters, 0, text.length() - 1);
        return accept;
    }
    /**
     * metoda, która będzie wywoływana rekurencyjnie dla aktualnych stanów.
     */
    public void check(final char[] sign, final int from, final int toEnd) {
        if (from < toEnd) {
            findTransition(sign , from);
            if (actuallyStates.size() > 0) {
                check(sign, from + 1 , toEnd);
            } else {
                accept = false;
            }
        }
        if (from == toEnd) {
            findTransition(sign , from);
            for (int i = 0; i < actuallyStates.size(); i++) {
                actuallyState = (State) actuallyStates.pop();
            if (automaton.isFinal(actuallyState)) {
                accept = true;
            }
            }
        }
    }
    /**
     * Metoda pobiera wszystkie przejscia z bieżącego stanu,
     * porównuje ich etykiety ze znakiem
     * (tzn.signs[index]) z wprowadzonego napisu,
     * jeśli sie zgadzają, odkłada stan na stos - targetStates.
     */
    public void findTransition(final char[] signs, final int index) {
    List<OutgoingTransition> allOutTransitions;
        final int stackSize = actuallyStates.size();
        for (int i = 0; i < stackSize; i++)  {
            actuallyState = (State) actuallyStates.pop();
            allOutTransitions = automaton.allOutgoingTransitions(actuallyState);
            if (!allOutTransitions.isEmpty()) {
                for (OutgoingTransition transition : allOutTransitions) {
                    if (transition.getTransitionLabel().canAcceptCharacter(signs[index])) {
                        targetStates.push(transition.getTargetState());
                    }
                }
            }
            if (allOutTransitions.isEmpty()) {
                continue;
            }
        }
        if (targetStates.size() > 0) {
        while (!targetStates.isEmpty()) {
            actuallyStates.push(targetStates.pop());
        }
        }
    }
    private transient State actuallyState;
    private final transient AutomatonSpecification automaton;
    private static Stack<State> actuallyStates = new Stack<State>();
    private static Stack<State> targetStates = new Stack<State>();
    private transient boolean accept;
}
