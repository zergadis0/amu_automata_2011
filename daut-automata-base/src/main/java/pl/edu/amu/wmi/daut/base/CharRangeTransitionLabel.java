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

        return (c >= firstChar & c <= secondChar);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public char getFirstChar() {
        return firstChar;
    }

    public char getSecondChar() {
        return secondChar;
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {

        if (label instanceof CharRangeTransitionLabel) {

            char a1 = this.getFirstChar();
            char a2 = this.getSecondChar();

            char b1 = ((CharRangeTransitionLabel) label).getFirstChar();
            char b2 = ((CharRangeTransitionLabel) label).getSecondChar();

            //3
            if (a1 < b1 && a2 < b2) { return new CharRangeTransitionLabel(b1, a2); }

            //4
            else if (a1 > b1 && a2 > b2) { return new CharRangeTransitionLabel(a1, b2); }

            //5
            else if (a1 < b1 && a2 > b2) { return new CharRangeTransitionLabel(b1, b2); }

            //6
            else if (a1 > b1 && a2 < b2) {
                return new CharRangeTransitionLabel(a1, a2); } else { return null; }

        } else { throw new CannotDetermineIntersectionException(); }

    }
};
