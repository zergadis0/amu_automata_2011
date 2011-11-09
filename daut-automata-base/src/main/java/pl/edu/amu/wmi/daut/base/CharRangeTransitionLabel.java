package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.List;

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

    protected TransitionLabel intersectWith(TransitionLabel label, TransitionLabel label2) {

        if (label instanceof CharRangeTransitionLabel 
                && label2 instanceof CharRangeTransitionLabel) {

            List<Character> labelList = new ArrayList<Character>();
            List<Character> label2List = new ArrayList<Character>();

            for (char i = ((CharRangeTransitionLabel) label).getFirstChar();
                    i == ((CharRangeTransitionLabel) label).getSecondChar(); i++) {
                labelList.add(i);
            }

            for (char i = ((CharRangeTransitionLabel) label2).getFirstChar();
                    i == ((CharRangeTransitionLabel) label2).getSecondChar(); i++) {
                label2List.add(i);
            }

            labelList.retainAll(label2List);

            return new CharRangeTransitionLabel(labelList.get(0),
                    labelList.get(labelList.size() - 1));

        } else { throw new CannotDetermineIntersectionException(); }

    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
        // TODO Auto-generated method stub
        return null;
    }
};
