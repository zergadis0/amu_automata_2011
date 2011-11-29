
package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 *
 * @author Adam
 */
public class SingleCharacterOperator extends NullaryRegexpOperator {

    char character;

    /**
     * Konstruktor jaki jest każdy widzi. Ten akurat pobiera chara :)
     */
    public SingleCharacterOperator(char a) {
        character = a;
    }

    /**
     * Generuje automat składający się tylko z jednego znaku.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {



        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        automaton.makeOneTransitionAutomaton(character);


        //generowanie automatu metodą z deterministic utilities dla jednoznakowego stringa.
        /*
        Set<String> singleCharLanguage = new HashSet<String>();
        singleCharLanguage.add(Character.toString(character));

        DeterministicUtilities utility = new DeterministicUtilities();
        utility.createAutomatonForFiniteLanguage(automaton, singleCharLanguage);//error*/

        //Wersja powielająca kod z "makeOneTransitionAutomaton napisana na poczatku
        /*State initial = automaton.addState();
        State characterState = automaton.addState();
        automaton.markAsInitial(initial);
        automaton.markAsFinal(characterState);
        TransitionLabel characterTransition = new CharTransitionLabel(a);
        automaton.addTransition(initial, characterState, characterTransition);*/

        return automaton;           
    }
}
