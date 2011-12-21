package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import java.util.List;

public class HexSingleCharacterOperator extends NullaryRegexpOperator {

    private char character;

    //jako parametr przyjmuje char, jaki ma zaakceptowaæ
    public HexSingleCharacterOperator(char a) {
        character = a;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(character);
    }

    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }
       
        //jako parametr przyjmuje ci¹g "\x{ABC}", gdzie ABC = znaki 0..9, a..f
        protected RegexpOperator doCreateOperator(List<String> params) {
            String s = params.get(0);
            s = s.substring(s.indexOf('{') + 1);
            s = s.substring(0, s.indexOf('}'));
            int i = Integer.parseInt(s, 16);
            char c = (char) i; //wsparcie dla unicode <=3.0
            return new SingleCharacterOperator(c);
        }
    }
}
