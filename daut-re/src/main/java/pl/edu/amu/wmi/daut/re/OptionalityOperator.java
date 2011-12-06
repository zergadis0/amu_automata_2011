package pl.edu.amu.wmi.daut.re;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 *
 * Klasa reprezentujaca operator ?
 */
public class OptionalityOperator extends UnaryRegexpOperator {

    public AutomatonSpecification createAutomatonFromOneAutomaton(AutomatonSpecification subautomaton) {   
    State q0 = subautomaton.addState();
    subautomaton.markAsInitial(q0);
    subautomaton.markAsFinal(q0);
    subautomaton.addTransition(q0, subautomaton.getInitialState(), new EpsilonTransitionLabel() );
    return subautomaton;
    }
}
