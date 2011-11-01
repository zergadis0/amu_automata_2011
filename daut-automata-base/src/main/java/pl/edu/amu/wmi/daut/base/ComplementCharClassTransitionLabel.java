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
        st_ = s;
        se_ = new HashSet();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '-') {
                for (char k = (char) (s.charAt(i - 1) + 1); k < (s.charAt(i + 1)); k++) {
                    se_.add(k);
                }
            } else {
                se_.add(s.charAt(i));
            }

        }
    }

    /**
     * 
     * @return Wynikiem jest wartość logiczna odpowiadająca na pytanie czy może
     * byc epsilon przejście
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
        return !(se_.contains(c));
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
            String str = "";
            Set set;
            set = ((ComplementCharClassTransitionLabel) label).getSet();
            for (Object o : se_) {
                set.add(o);
            }
            for (Object o : set) {
                str += o.toString();
            }
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
        return se_;
    }

    /**
     * 
     * @return Zwraca podane wyrażenie regularne
     */
    public String getString() {
        return st_;
    }
    private String st_;
    private Set se_;
}
