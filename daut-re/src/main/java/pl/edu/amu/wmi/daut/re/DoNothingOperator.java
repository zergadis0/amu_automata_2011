package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

/**
 * Klasa dziedzicząca po UnaryRegexpOperator,
 * która nic nie robi.
 */
public class DoNothingOperator extends UnaryRegexpOperator {

    @Override
    public AutomatonSpecification createAutomatonFromOneAutomaton(
        AutomatonSpecification subautomaton) {
        return subautomaton;
    }

}
