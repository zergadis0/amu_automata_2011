package pl.edu.amu.wmi.daut.base;

/**
 * @author KubaZ
 * Klasa przejścia, które odpowiada operatorowi \b z wyrażeń regularnych,
 * jeśli włączono tryb multiline.
 */
public class WordBoundaryTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    public boolean doCheckContext(String s, int position) {
        if (s.length() < position || position < 0) {
             throw new PositionOutOfStringBordersException();
        }
        if (position == s.length())
            return true;
        String string = String.valueOf(s.charAt(position));
        boolean nonWordCharacter = string.matches("\\W");
        if (nonWordCharacter)
            return true;
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
