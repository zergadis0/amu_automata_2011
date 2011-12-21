package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;

/**
 * Klasa reprezentuj¹ca znak konca wiersza.
 */
public class NewlineOperator extends NullaryRegexpOperator {
    private char newLine;

    /**
     * Metoda, ustawia '\n'.
     */
    public void setNewLine() {
        this.newLine = "\n";
    }
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(newLine);
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
            return new NewlineOperator();
        }
    }
}
