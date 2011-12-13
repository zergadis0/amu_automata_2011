package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.AnyTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
 * Klasa reprezentująca operator '.' z wyrażeń regularnych (dowolny znak).
 */
public class AnyCharOperator extends NullaryRegexpOperator {
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.addTransition(q0, q1, new AnyTransitionLabel());
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        
        return automaton;
    }
    
     /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 0;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new AnyCharOperator();
        }
    }
}
