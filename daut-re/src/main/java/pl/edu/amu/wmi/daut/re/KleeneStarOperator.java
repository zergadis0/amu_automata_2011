package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

/**
 * Klasa reprezentująca operator '*' z wyrażeń regularnych
 * (dowolna liczba wystąpień, w szczególności 0).
 */
public class KleeneStarOperator extends UnaryRegexpOperator {

    @Override
    public final AutomatonSpecification createAutomatonFromOneAutomaton
            (AutomatonSpecification subautomaton) {
        //Odkomentować po opracowaniu poprawnej Gwiazdki Kleene'ego.
//        return AutomataOperations.getKleeneStar(subautomaton);
        //Usunąć po opracowaniu poprawnej Gwiazdki Kleene'ego.
        return subautomaton;
    }
    
}
