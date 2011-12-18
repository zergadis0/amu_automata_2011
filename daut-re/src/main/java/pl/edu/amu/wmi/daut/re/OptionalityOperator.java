package pl.edu.amu.wmi.daut.re;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 *
 * Klasa reprezentujaca operator ?
 */
public class OptionalityOperator extends UnaryRegexpOperator {

    /**
     * Główna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {
        State q = subautomaton.addState();
        subautomaton.markAsInitial(q);
        subautomaton.markAsFinal(q);
        subautomaton.addTransition(q, subautomaton.getInitialState(),
        new EpsilonTransitionLabel());
        return subautomaton;
    }
}
