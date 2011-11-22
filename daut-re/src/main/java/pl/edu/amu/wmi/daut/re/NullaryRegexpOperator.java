package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

/**
 * Klasa abstrakcyjna reprezentująca operator zeroargumentowy,
 * tj. generująca ustalony automat.
 */
abstract class NullaryRegexpOperator extends RegexpOperator {
    
    public int arity() {
        return 0;
    }

    final protected AutomatonSpecification doCreateAutomaton(
        List<AutomatonSpecification> subautomata) {
 
        return createFixedAutomaton();
    }

    /**
     * Właściwa metoda budująca automat na podstawie podautomatu.
     */
    public abstract AutomatonSpecification createFixedAutomaton();

};
