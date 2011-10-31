package pl.edu.amu.wmi.daut.base;
/**
 * Tworzy klasę która ma reprezentować "działający" deterministyczny automat skończenie stanowy.
 */
public class DeterministicAutomaton implements Acceptor {

    private DeterministicAutomatonSpecification automaton;
    private State actuallyState;
    /**
     * Tworzy "działający" automat na podstawie podanej specyfikacji automatu deterministycznego.
     */
    public DeterministicAutomaton(DeterministicAutomatonSpecification specification) {
        automaton = specification;
    }
    /**
     * Metoda sprawdzająca, czy automat akceptuje podany napis,
     * wykorzystując pętle po kolejnych znakach napisu.
     * Jeśli ostatni stan jest końcowy, zwraca 'true'.
     * Korzystam z metody zwracającej przejście 'targetState' która,
     * powinna być zaimplementowana w NaiveAutomatonSpecification.
     */
    public boolean accepts(String text) {


        actuallyState = automaton.getInitialState();

        for (int i = 0; i < text.length(); i++) {
            if (automaton.targetState(actuallyState, text.charAt(i)) != null) {
                actuallyState = automaton.targetState(actuallyState, text.charAt(i));
            } else {
                break;
            }

        }
        return automaton.isFinal(actuallyState);
    }
}
