package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/
public class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator {

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

     /**
     * Fabryka operatora.
     */
    public static class Factory extends UnaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new FixedNumberOfOccurrencesOperator(Integer.parseInt(params.get(0)));
        }
    }
}
