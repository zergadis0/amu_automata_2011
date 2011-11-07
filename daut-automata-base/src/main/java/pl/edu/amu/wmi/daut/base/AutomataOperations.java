package pl.edu.amu.wmi.daut.base;

import java.util.Set;

/**
 *
 */
public class AutomataOperations {

    protected AutomataOperations() {
        throw new UnsupportedOperationException(); // prevents calls from subclass
    }
    /**
     * Metoda zwracajaca Automat akceptujacy jezyk bedacy dopelnieniem jezyka
     * akceptowanego przez Automat otrzymywany "na wejsciu".
     * @return AutomatonSpecification
     */
    static AutomatonSpecification
            complementLanguageAutomaton(DeterministicAutomatonSpecification automaton,
            Set<Character> alfabet) {
        //NaiveDeterministicAutomatonSpecification returned = automaton.clone();
        //returned.makeFull(alfabet.toString());
        //return returned;
        return automaton;
    }
}
