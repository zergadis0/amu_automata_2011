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
        st = s;
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


            return new ComplementCharClassTransitionLabel(str);
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
     * @return Zwraca podane wyrażenie regularne
     */
    public String getString() {
        return st;
    }
    private String st;
    private Set se;
}
