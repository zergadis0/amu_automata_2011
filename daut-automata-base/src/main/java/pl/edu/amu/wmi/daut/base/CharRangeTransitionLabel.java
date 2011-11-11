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

            char A1 = this.getFirstChar();
            char A2 = this.getSecondChar();

            char B1 = ((CharRangeTransitionLabel) label).getFirstChar();
            char B2 = ((CharRangeTransitionLabel) label).getSecondChar();

            //3
            if (A1 < B1 && A2 < B2) {
                return new CharRangeTransitionLabel(B1, A2);
                }

            //4
            else if (A1 > B1 && A2 > B2) {
                return new CharRangeTransitionLabel(A1, B2);
                }

            //5
            else if (A1 < B1 && A2 > B2) {
                return new CharRangeTransitionLabel(B1, B2);
                }

            //6
            else if(A1 > B1 && A2 < B2) {
                return new CharRangeTransitionLabel(A1, A2);
                
                } else { return null; }

            } else { throw new CannotDetermineIntersectionException(); }

    }
};
