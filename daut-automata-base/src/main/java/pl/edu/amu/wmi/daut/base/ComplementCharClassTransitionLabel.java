/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author dawidss7
 */
public class ComplementCharClassTransitionLabel extends TransitionLabel {

    /**
     * 
     * @param s Przyjmuje Stringa będącego wyrażeniem regularnym
     */
    ComplementCharClassTransitionLabel(String s) {
        int l = s.length();
        se = new HashSet();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '-') {
                if (i == 0 || i == l - 1) {
                    se.add('-');
                } else {
                    for (char k = (char) (s.charAt(i - 1) + 1); k < (s.charAt(i + 1)); k++) {
                        se.add(k);
                    }
                }
            } else {
                se.add(s.charAt(i));
            }

        }
    }

    /**
     * 
     * @return Wynikiem jest wartość logiczna odpowiadająca na pytanie czy może
     * być epsilon przejście
     */
    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    /**
     * 
     * @param c Przyjmuje znak do sprawdzenia
     * @return Wynikiem jest wartość logiczna czy znak jest akceptowany
     */
    @Override
    public boolean canAcceptCharacter(char c) {
        return !(se.contains(c));
    }

    /**
     * 
     * @return Zwraca wartość logiczną czy jest puste przejście
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 
     * @param label Przyjmuje TransitionLabel
     * @return Zwraca etykietę przejścia będącą przecięciem label i danej
     * etykiety
     */
    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
        if (label instanceof ComplementCharClassTransitionLabel) {

            return new ComplementCharClassTransitionLabel(
                    getString((ComplementCharClassTransitionLabel) label));
        } else {
            throw new CannotDetermineIntersectionException();
        }
    }

    /**
     * 
     * @return Zwraca set przechowujący spełniające podane wyrażenie regularne
     */
    protected Set getSet() {
        return se;
    }

    /**
     * 
     * @return Zwraca wyrażenie regularne jako String
     */
    private String getString(ComplementCharClassTransitionLabel label) {
        Set set;
        set = ((ComplementCharClassTransitionLabel) label).getSet();
        for (Object o : se) {
            set.add(o);
        }
        StringBuilder buf = new StringBuilder();
        boolean f = false;
        for (Object o : set) {
            if (o.toString().equals("-")) {
                f = true;
                continue;
            }
            buf.append(o);
        }
        if (f) {
            buf.append('-');
        }
        String str = buf.toString();
        char p = 0, k = 0;
        if (str.length() == 0) {
            return "";
        }
        StringBuilder n = new StringBuilder();
        p = str.charAt(1);
        k = p;
        for (int i = 0; i < str.length(); i++) {
            if (k + 1 != str.charAt(i)) {
                if (p != k) {
                    n.append(p);
                    n.append('-');
                    n.append(k);

                } else {
                    n.append(p);
                }
                p = str.charAt(i);
                k = p;
            } else {
                k++;
            }
        }
        if (p != k) {
            n.append(p);
            n.append('-');
            n.append(k);

        } else {
            n.append(p);
        }

        return n.toString();
    }

    /**
     * 
     * @return Zwraca wyrażenie regularne
     */
    @Override
    public String toString() {
        String q = "";
        q += "[";
        q += getString(new ComplementCharClassTransitionLabel(""));
        q += "]";
        return q;

    }
    private Set se;
}
