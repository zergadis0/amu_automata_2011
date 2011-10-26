package pl.edu.amu.wmi.daut.base;
import java.util.ArrayList;
/**
 * Klasa reprezentująca przejście po dowolnym znaku z podanego zakresu UTF-8.
 */
class CharRangeTransitionLabel {

    private char firstChar;
    private char secondChar;
    private ArrayList range = new ArrayList();

    public CharRangeTransitionLabel(char a, char z) {
        firstChar = a;
        secondChar = z;
    }

    public void makeArray() {
        while (firstChar < secondChar) {
            range.add(firstChar);
            firstChar++;
        }
    }
};
