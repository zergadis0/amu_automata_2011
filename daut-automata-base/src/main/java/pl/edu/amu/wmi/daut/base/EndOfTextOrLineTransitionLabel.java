package pl.edu.amu.wmi.daut.base;

class PositionOutOfStringBordersException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

/**
 * Klasa EndOfTextOrLineTransitionLabel.
 */
public class EndOfTextOrLineTransitionLabel extends ZeroLengthConditionalTransitionLabel {

    @Override
    protected boolean doCheckContext(String s, int position) {
        if (s.length() < position) {
            throw new PositionOutOfStringBordersException();
        }
        if (( s.charAt(position) == '\n') || (s.charAt(position) == '\u0003')) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "EndOfTextOrLine";
    }

}
