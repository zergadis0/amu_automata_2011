/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author kacper
 */
public class CharClassTransitionLabel extends TransitionLabel {

    private SortedSet<Character> ssc = new TreeSet<Character>();
    private String charClass;

    /**
     * poniższy konstruktor przekształca dany łańcuch na zbiór charów.
     * @param s jest zbiorem klas wyrażeń regularnych do których należy dany char
     */
    public CharClassTransitionLabel(String s) {
        charClass = new String(s);
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '-') {
                addChar(s.charAt(i-1), s.charAt(i+1));
                i++;
            }
            else if (i+1 < s.length() && s.charAt(i+1) == '-') {
                addChar(s.charAt(i), s.charAt(i+2));
                i += 2;
            }
            else {
                addChar(s.charAt(i));
            }
        }
    }

    private final void addChar(Character c1, Character c2) {
        for (Character d = c1; d <= c2; ++d) {
            ssc.add(d);
        }
    }
    private final void addChar(Character c) {
        ssc.add(c);
    }

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(final char c) {
        return ssc.contains((Character) c);

    }

    @Override
    public boolean isEmpty() {
        return !charClass.isEmpty();
    }

    @Override
    public String toString() {
        return "[" + charClass + "]";
    }

    /**
     *
     * @param label
     * @return
     */
    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
        SortedSet<Character> tmp = new TreeSet<Character>();
        StringBuilder sb = new StringBuilder();
        for (Character c : ssc) {
            if (label.canAcceptCharacter(c)) {
                tmp.add(c);
            }
        }
        for (Character c : tmp) {
            sb.append(c);
        }
        return new CharClassTransitionLabel(sb.toString());

    }


}
