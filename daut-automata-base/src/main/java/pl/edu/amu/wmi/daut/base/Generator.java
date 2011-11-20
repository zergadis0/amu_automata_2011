package pl.edu.amu.wmi.daut.base;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * klasa zawierająca metodę zwracającą listę wszystkich słów z automatu bez cykli.
 */
public class Generator {

    private List<String> acceptedWords = new ArrayList<String>();
    private TransitionLabel currentLabel;

    /**
     * Metoda zwracająca listę wszystkich słów akceptowanych przez automat bez cykli.
     */
    void acceptWords(AutomatonSpecification automaton, String alphabet, String word, State state) {
        StringBuffer buf = new StringBuffer(word);
        List<OutgoingTransition> allOutTransitions;
        allOutTransitions = automaton.allOutgoingTransitions(state);
            if (!allOutTransitions.isEmpty()) {
                String word2 = "";
                for (OutgoingTransition transition : allOutTransitions) {
                    currentLabel = transition.getTransitionLabel();
                    for (int i = 0; i < alphabet.length(); i++) {
                        if (currentLabel.canAcceptCharacter(alphabet.charAt(i))) {
                            if (!automaton.isFinal(state)) {
                                 acceptedWords.remove(word);
                            }
                        buf.append(alphabet.charAt(i));
                        word2 = buf.toString();
                        acceptedWords.add(word2);
                        acceptWords(automaton, alphabet, word2, transition.getTargetState());

                        break;
                        }
                    }
            buf.deleteCharAt(word.length());
            word2 = buf.toString();
                }
            } else {
                    if (!automaton.isFinal(state))
                        acceptedWords.remove(word);
            }
    }
    /**
     * Metoda zwracająca listę słów akceptowanych przez automat bez cykli.
     *
     */
    public List wordsFromAutomatonWithoutCycles(AutomatonSpecification automaton, String alphabet) {
        String word = "";
        acceptWords(automaton, alphabet, word, automaton.getInitialState());
        return acceptedWords;
    }
}

