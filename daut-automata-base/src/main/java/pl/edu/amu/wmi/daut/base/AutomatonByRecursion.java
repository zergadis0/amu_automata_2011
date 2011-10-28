package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.ArrayList;
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
    /**
     * Metoda zwracająca listę wszystkich słów akceptowanych przez automat bez cykli.
     * Pobiera wszystkie przejscia z bieżącego stanu,
     * jeżeli takie istnieją to każdą po kolei etykietę porównuje z zadanym alfabetem
     * Jeżeli etykieta akceptuje znak z alfabetu,  obecny stan nie jest stanem akceptującym
     * dodaje do słowa symbol a poprzednie słowo usuwa, w przeciwnym wypadku tylko
     * dodaje symbol. Jeżeli z danego stanu nie wychodzą żadne etykiety a stan ten nie jest
     * akceptującu to napis jest usuwany
     */
    private List<String> acceptedWords = new ArrayList<String>();
    public void acceptedWords(String alphabet, String word, State state) {
        int i = 0;
        StringBuffer buf = new StringBuffer();
              List<OutgoingTransition> allOutTransitions;
              allOutTransitions = automaton.allOutgoingTransitions(state);
                  if (!allOutTransitions.isEmpty()) {
                      for (OutgoingTransition transition : allOutTransitions) {
                          currentLabel = transition.getTransitionLabel();
                          for (i = 0; i < alphabet.length(); i++) {
                            if (currentLabel.canAcceptCharacter(alphabet.charAt(i))) {
                   if (!(automaton.isFinal(state)) && (automaton.getInitialState() != state)) {
                                        acceptedWords.remove(word);
                   }
                                    buf.append(alphabet.charAt(i));
                                    word = buf.toString();
                                    acceptedWords.add(word);
                                    acceptedWords(alphabet, word, transition.getTargetState());
                            } else {
                                if (!automaton.isFinal(state)) {
                                    acceptedWords.remove(word);
                                }
                          }
                      }
                      }
                  } else {
                      if (!automaton.isFinal(state)) {
                          acceptedWords.remove(word);
                      }
                      }
    }
}
