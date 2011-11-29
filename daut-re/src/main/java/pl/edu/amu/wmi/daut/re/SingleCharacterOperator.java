
package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 *
 * @author Adam
 */
public class SingleCharacterOperator extends NullaryRegexpOperator {

    char character;

    /**
     * Konstruktor jaki jest każdy widzi. Ten akurat pobiera chara :)
     */
    public SingleCharacterOperator(char a) {
        character = a;
    }

    /**
     * Generuje automat składający się tylko z jednego znaku.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        automaton.makeOneTransitionAutomaton(character);

        return automaton;           
    }
}
