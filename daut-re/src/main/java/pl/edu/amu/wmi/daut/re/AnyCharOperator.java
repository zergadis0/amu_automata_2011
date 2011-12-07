package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * Klasa reprezentująca operator '.' z wyrażeń regularnych (dowolny znak).
 */
public class AnyCharOperator extends NullaryRegexpOperator {
    private char character;

    /**
     * Metoda, ustawia pożądany znak.
     */
    public void setCharacter(char c) {
        this.character = c;
    }
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(character);
    }
}
