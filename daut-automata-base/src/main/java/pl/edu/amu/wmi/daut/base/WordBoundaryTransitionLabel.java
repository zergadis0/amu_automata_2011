package pl.edu.amu.wmi.daut.base;

/**
 * @author KubaZ
 * Klasa przejścia, które odpowiada operatorowi \b z wyrażeń regularnych,
 * jeśli włączono tryb multiline.
 */
public class WordBoundaryTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    private String str;
    private boolean character, leftCharacter;

    @Override
    public boolean doCheckContext(String s, int position) {
        if (s.length() < position || position < 0)
             throw new PositionOutOfStringBordersException();
        if (s.length() < 3)
            return true;
        if (position == s.length()) {
            str = String.valueOf(s.charAt(position - 1)); 
            character = str.matches("\\w");
            if (character)
                return true;
        }
        if (position == 0) {
            str = String.valueOf(s.charAt(position));
            character = str.matches("\\w");
            if (character)
                return true;
        }
        str = String.valueOf(s.charAt(position));
        character = str.matches("\\w");
        if (character) {
            str = String.valueOf(s.charAt(position - 1));
            leftCharacter = str.matches("\\W");
            if (leftCharacter)
                return true;
        } else {
            str = String.valueOf(s.charAt(position - 1));
            leftCharacter = str.matches("\\w");
            if (leftCharacter)
                return true;
        }
        return false;
    };
    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    };
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public String toString() {
        return "WordBoundary";
    }
}
