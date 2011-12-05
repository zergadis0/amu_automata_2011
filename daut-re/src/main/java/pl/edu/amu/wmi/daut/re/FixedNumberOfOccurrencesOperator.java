package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/

abstract class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator{

    private int n;

    public FixedNumberOfOccurrencesOperator(int a) {
        this.n = a;
    }

    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton, List<AutomatonSpecification> subautomata) {

        List<AutomatonSpecification> lista = null;
        lista = subautomata;
        AutomatonSpecification automatwejsciowy = subautomaton.clone();

        AutomatonSpecification automatbudowany = subautomaton.clone();

        for(int i=0;i<this.n-1;i++) {
            lista.add(i, automatbudowany);
            
            for (State state : automatbudowany.allStates()) {
                if(automatbudowany.isFinal(state)) {
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
