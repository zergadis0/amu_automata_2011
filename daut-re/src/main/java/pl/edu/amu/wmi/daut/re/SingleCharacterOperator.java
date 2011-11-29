/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.re;

import java.util.Set;
import java.util.HashSet;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.DeterministicUtilities;

/**
 *
 * @author Adam
 */
public class SingleCharacterOperator extends NullaryRegexpOperator {
    
    char character;
    
    /**
     * Generuje automat składający się tylko z jednego znaku.
     */
    public SingleCharacterOperator(char a) {
        character = a;
    }

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new DeterministicAutomatonSpecification();//error
        
        Set<String> singleCharLanguage = new HashSet<String>();
        singleCharLanguage.add(Character.toString(character));
        
        DeterministicUtilities utility = new DeterministicUtilities();
        utility.createAutomatonForFiniteLanguage(automaton, singleCharLanguage);//error
        /*State initial = automaton.addState();
        State characterState = automaton.addState();
        automaton.markAsInitial(initial);
        automaton.markAsFinal(characterState);
        TransitionLabel characterTransition = new CharTransitionLabel(a);
        automaton.addTransition(initial, characterState, characterTransition);*/
        
        return automaton;
                
    }
    
}
