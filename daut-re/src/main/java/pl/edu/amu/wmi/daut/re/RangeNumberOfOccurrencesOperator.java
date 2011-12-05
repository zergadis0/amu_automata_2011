package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 * Klasa reprezentująca operator '{n,m}'.
 */
public class RangeNumberOfOccurrencesOperator extends UnaryRegexpOperator {
    /**
    * Zmienne dla ilosci wystapen wyrazenia.
    */    
    private int max;
    private int min;

    /**
    * Konstruktor klasy.
    */
    public RangeNumberOfOccurrencesOperator(int n, int m) {
       this.max = m;
       this.min = n;
   }

    /**
    * Glowna metoda klasy.
    */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {
        
        List<AutomatonSpecification> automata = null;
        AutomatonSpecification previousAutomaton = new NaiveAutomatonSpecification();
        previousAutomaton = subautomaton.clone();
        AutomatonSpecification firstAutomaton = new NaiveAutomatonSpecification();
        firstAutomaton = subautomaton.clone();
        firstAutomaton.addTransition(firstAutomaton.getInitialState(),
                            previousAutomaton.getInitialState(),
                            new EpsilonTransitionLabel());
        
        AutomatonSpecification nextAutomaton = new NaiveAutomatonSpecification();
        nextAutomaton = subautomaton.clone();
        int counter = 1;
        for(int i = 1; i < this.max; i++) {
            automata.add(i, nextAutomaton);
            
            for (State state : previousAutomaton.allStates()) {
                if(previousAutomaton.isFinal(state)) {
                    previousAutomaton.addTransition(state,
                            automata.get(i).getInitialState(),
                            new EpsilonTransitionLabel());
                }
            }
            
            if(counter < this.min) {
                for (State state : previousAutomaton.allStates()) {
                    if(previousAutomaton.isFinal(state)) {
                        previousAutomaton.unmarkAsFinalState(state);
                    }
                }
            }
            counter++;
            previousAutomaton = nextAutomaton;
        }
        return firstAutomaton;
    }
};
