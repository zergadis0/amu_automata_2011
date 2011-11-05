package pl.edu.amu.wmi.daut.base;

import java.util.List;

/**
 * klasa która decyduje czy automat zaakceptuje dany napis.
 */
public final class AutomatonByRecursion implements Acceptor {
    AutomatonByRecursion(final AutomatonSpecification specification) {
        automaton = specification;
    }
    @Override
    public boolean accepts(final String text) {
        accept = false;
        check(text, 0, text.length() - 1, automaton.getInitialState());
        return accept;
    }
    /**
     * Metoda, która będzie wywoływana rekurencyjnie dla aktualnych stanów,
     * pobiera wszystkie przejscia z bieżącego stanu,
     * porównuje ich etykiety ze znakiem
     * (tzn.characters.charAt(from)) z wprowadzonego napisu,
     * jeśli sie zgadzają, porównuje stan docelowy przejścia.
     */
    private void check(final String text, final int from, final int toEnd, final State state) {
        if (from > toEnd) {
            if (automaton.isFinal(state)) {
                accept =  true;
            }
        } else {
              List<OutgoingTransition> allOutTransitions;
              allOutTransitions = automaton.allOutgoingTransitions(state);
                  if (!allOutTransitions.isEmpty()) {
                      for (OutgoingTransition transition : allOutTransitions) {
                          currentLabel = transition.getTransitionLabel();
                          if (currentLabel.canAcceptCharacter(text.charAt(from))) {
                              check(text, from + 1, toEnd, transition.getTargetState());
                          }
                      }
                  }
          }
    }
    private TransitionLabel currentLabel;
    private final AutomatonSpecification automaton;
    private boolean accept;
}
