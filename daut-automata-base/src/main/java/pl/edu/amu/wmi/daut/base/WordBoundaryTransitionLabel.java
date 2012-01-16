package pl.edu.amu.wmi.daut.base;

/**
 * @author KubaZ
 * Klasa przejścia, które odpowiada operatorowi \b z wyrażeń regularnych,
 * jeśli włączono tryb multiline.
 */
public class WordBoundaryTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    private String str;
    private boolean wordCharacter, nonWordCharacter;

    @Override
    public boolean doCheckContext(String s, int position) {
        if (s.length() < position || position - 1 < 0)
             throw new PositionOutOfStringBordersException();
        str = String.valueOf(s.charAt(position - 1));
        wordCharacter = str.matches("\\w");
        if (position == s.length() && wordCharacter)
            return true;
        str = String.valueOf(s.charAt(position));
        nonWordCharacter = str.matches("\\W");
        if (nonWordCharacter && wordCharacter)
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
