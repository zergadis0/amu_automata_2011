package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * Klasa reprezentująca operator '.' z wyrażeń regularnych (dowolny znak).
 */
public class AnyCharOperator extends NullaryRegexpOperator {
    private char character;
    public void setCharacter(char Character) {
        this.character = Character;
    }
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(character);
    }
}
