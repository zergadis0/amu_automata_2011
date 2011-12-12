package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * Klasa reprezentująca pojedynczy konkretny znak o kodzie Unicode podany w zapisie ósemkowym.
 */
public class OctSingleCharacterOperator extends NullaryRegexpOperator {

    private int value;

    /**
     * Konstruktor.
     */
    public OctSingleCharacterOperator(int a) throws Exception {
       this.setValue(a);
    }

    /**
     * Metoda, ustawia nową wartość ( sprawdza czy jest w kodzie ósemkowym).
     */
    public void setValue(int i) throws Exception {
        if (isOctal(i))
            this.value = i;
        else throw new Exception();
    }

    /**
     * Metoda zwraca aktualną wartość (w kodzie ósemkowym).
     */
    public int getValue() {
        return value;
    }

    /**
     * Metoda sprawdza, czy wartość jest w kodzie ósemkowym.
     */
    public boolean isOctal(int number) {
        if (new Integer(number).toString().equals(Integer.toOctalString(number)))
            return true;
        else return false;
    }


    @Override
    public AutomatonSpecification createFixedAutomaton() {

        int octValue = Integer.parseInt(Integer.toString(value),8);

        return new NaiveAutomatonSpecification()
                .makeOneTransitionAutomaton((char)octValue);
    }
}
