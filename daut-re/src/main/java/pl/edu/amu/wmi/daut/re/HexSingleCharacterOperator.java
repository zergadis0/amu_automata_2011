package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;
import java.text.ParseException;

/**
 * Klasa reprezentujaca pojedynczy konkretny znak o kodzie Unicode podanym w zapisie szesnastkowym.
 */
public class HexSingleCharacterOperator extends NullaryRegexpOperator {

    private char character;

    /**
     * Jako parametr przyjmuje char, jaki ma zaakceptowac.
     */
    public HexSingleCharacterOperator(char a) {
        character = a;
    }

    /**
     * Funkcja zwracajaca znak akceptowany przez automat.
     */
    public char getCharacter() {
        return character;
    }

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(character);
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

    static final int BASE = 16;
        @Override
        public int numberOfParams() {
            return 1;
        }

        /**
         * Jako parametr przyjmuje ciag "\x{ABC}", gdzie ABC = znaki 0..9, a..f
         */
        protected RegexpOperator doCreateOperator(List<String> params) {
            String s = params.get(0);
            int length = s.length();
            if (length <= 4)
                throw new ParseException("Argument was too short.", -1);
            if (s.charAt(0) != '\\')
                throw new ParseException("Backslash was expected here.", 0);
            if (s.charAt(1) != 'x' && s.charAt(1) != 'X')
                throw new ParseException("\"x\" was expected here.", 1);
            if (s.charAt(2) != '{')
                throw new ParseException("Left curly brace was expected here.", 2);
            if (s.charAt(length - 1) != '}')
                throw new ParseException("Right curly brace was expected here.", length - 1);
            s = s.substring(3, length - 1);
            int i = Integer.parseInt(s, BASE); //jesli bedzie zly format - rzuci NumberFormatException czyli to, co chcemy
            char c = (char) i;
            return new SingleCharacterOperator(c);
        }
    }
}
