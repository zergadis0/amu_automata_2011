package pl.edu.amu.wmi.daut.base;

/**
 * Klasa reprezentująca przejście po dowolnym znaku z podanego zakresu UTF-8.
 */
class CharRangeTransitionLabel extends TransitionLabel {

    private char firstChar;
    private char secondChar;

    public CharRangeTransitionLabel(char a, char z) {
        firstChar = a;
        secondChar = z;
    }

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        if (c >= firstChar & c <= secondChar) { return true; }
        else { return false; }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) { 
        return this; 
    }
};
