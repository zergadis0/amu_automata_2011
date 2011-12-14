package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.CharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;

class UnknownAsciiCharacterClassException extends Exception {  
}

/**
 * ASCII character classes.
 */
public class AsciiCharacterClassOperator extends NullaryRegexpOperator {
    private String str;
    /**
     * konstruktor ASCII character classes.
     */
    public AsciiCharacterClassOperator(String a)
            throws UnknownAsciiCharacterClassException {
        if (a.equals("[:alnum:]")) {
            str = "0-9A-Za-z";
            }
        else if (a.equals("[:alpha:]")) {
            str = "A-Za-z";
            }
        else if (a.equals("[:ascii:]")) {
            str = "\u0000-\u007F";
            }
        else if (a.equals("[:blank:]")) {
            str = "\t ";
            }
        else if (a.equals("[:cntrl:]")) {
            str = "\u0000-\u001F\u007F";
            }
        else if (a.equals("[:digit:]")) {
            str = "0-9";
            }
        else if (a.equals("[:graph:]")) {
            str = "!~-";
            }
        else if (a.equals("[:lower:]")) {
            str = "a-z";
            }
        else if (a.equals("[:print:]")) {
            str = " -~";
            }
        else if (a.equals("[:punct:]")) {
            str = "!-/:-@[-`{-~";
            }
        else if (a.equals("[:space:]")) {
            str = "\t\n\f\r \u000B";
            }
        else if (a.equals("[:upper:]")) {
            str = "A-Z";
            }
        else if (a.equals("[:word:]")) {
            str = "0-9A-Za-z_";
            }
        else if (a.equals("[:xdigit:]")) {
            str = "0-9A-Fa-f";
            }
       else throw new UnknownAsciiCharacterClassException();
    }
     /**
     * Generuje automat.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        automaton.addTransition(q0, q1, new CharClassTransitionLabel(str));

        return automaton;
    }
}
