package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;

/**
 * Klasa reprezentująca operator '.' z wyrażeń regularnych (dowolny znak).
 */
public class AnyCharOperator extends NullaryRegexpOperator {
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0;
        State q1;
        automaton.addState(q0);
        automaton.addState(q1);
        automaton.addTransition(q0, q1, new AnyTransitionLabel());
        markAsInitial(q0);
        markAsFinal(q1);
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
