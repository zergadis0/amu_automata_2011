package pl.edu.amu.wmi.daut.base;

/*
 * @author cole1911
 */

class AnyTransitionLabel extends TransitionLabel {

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        ch = c;
        return true;
    }

    public char getChar() {
        return ch;
    }

    @Override
    public boolean isEmpty() {
         return false;
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
         return label.canAcceptCharacter(ch) ? this : new EmptyTransitionLabel();
    }
    private char ch;
}
