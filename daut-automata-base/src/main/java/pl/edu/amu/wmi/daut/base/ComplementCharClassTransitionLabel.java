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

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return !(se_.contains(c));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

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

    protected Set getSet() {
        return se_;
    }

    public String getString() {
        return st_;
    }
    private String st_;
    private Set se_;
}
