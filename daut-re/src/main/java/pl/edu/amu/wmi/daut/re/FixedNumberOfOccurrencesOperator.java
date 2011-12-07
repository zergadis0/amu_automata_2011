package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/
public abstract class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator {

    private int n;

    /**
     * Konstruktor klasy.
     */
    public FixedNumberOfOccurrencesOperator(int a) {
        this.n = a;
    }

    /**
     * Główna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {

        AutomatonSpecification automatwejsciowy = subautomaton.clone();

        AutomatonSpecification automatbudowany = subautomaton.clone();


        for (int i = 0; i < this.n - 1; i++) {

            for (State state : automatbudowany.allStates()) {
                if (automatbudowany.isFinal(state)) {
                            automatbudowany.addTransition(state,
                            automatwejsciowy.getInitialState(),
                            new EpsilonTransitionLabel());
                    automatbudowany.unmarkAsFinalState(state);
                }
            }
        }
        return automatbudowany;
    }
}
