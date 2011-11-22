package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.List;

abstract class DeterministicAutomatonSpecification extends AutomatonSpecification {
    /**
     * Zwraca stan, jaki zostanie osiągnięty przy przejściu ze stanu
     * from przez znak c. Zwraca null, jeśli nie istnieje przejście
     * ze stanu from przez znak c.
     */
    public abstract State targetState(State from, char c);
    public List<State> findPreviousState(DeterministicAutomatonSpecification automaton, State nextState) {
        List<State> previousStates = new ArrayList<State>();
        for(State state : automaton.allStates()) {
            for(OutgoingTransition transition : automaton.allOutgoingTransitions(state)){
                if(transition.getTargetState() == nextState) {
                    previousStates.add(state);
                }
            }
        }
        return previousStates;
    }
    public List<OutgoingTransition> findPreviousStateTransitions(DeterministicAutomatonSpecification automaton, State previousState, State nextState) {
        List<OutgoingTransition> needTransitions = new ArrayList<OutgoingTransition>();
        for(OutgoingTransition transition : automaton.allOutgoingTransitions(previousState)) {
            if(transition.getTargetState() == nextState)
                needTransitions.add(transition);
        }
        return needTransitions;
    }
    
    public void deleteUselessStates(DeterministicAutomatonSpecification automaton) {
        /*Sprawdzamy czy automat nie posiada zbednych stanow, to jest takich
                do ktorych nie mozna dojsc ze stanu poczatkowego, jesli tak usuwamy je*/
        State startState = automaton.getInitialState(); //stan startowy
        List<State> startStates = new ArrayList<State>();
        List<State> uselessStates = new ArrayList<State>(); //stany zbedne
        uselessStates.addAll(automaton.allStates());
        uselessStates.remove(automaton.getInitialState());
        for (OutgoingTransition transition : automaton.allOutgoingTransitions(startState)) {
            startStates.add(transition.getTargetState());
            uselessStates.remove(transition.getTargetState());
        }
        while(!startStates.isEmpty()) { //przechodzimy po koleji od stanu poczatkowego po przejsciach, dochodzad w ten sposob do wszystkich stanow dostepnych z stanu poczatkowego
            for(int i = 0; i<startStates.size(); i++) { 
                for (OutgoingTransition transition : automaton.allOutgoingTransitions(startStates.get(i))) {
                    if(!startStates.contains(transition.getTargetState())){ 
                        startStates.add(transition.getTargetState());
                        uselessStates.remove(transition.getTargetState());
                    }
                }
                startStates.remove(startStates.get(i));
            }
        }
        if(!uselessStates.isEmpty())
            automaton.allStates().removeAll(uselessStates); //jesli sa jakies stany do ktorych nie doszlismy ze stanu poczatkowego, usuwamy je z automatu
    }
    
    public DeterministicAutomatonSpecification makeMinimal(DeterministicAutomatonSpecification automaton) {
        
        DeterministicAutomatonSpecification returnAutomaton = new NaiveDeterministicAutomatonSpecification();
        
        deleteUselessStates(automaton);
        
        //Szukamy stanow rownowaznych
        int size = automaton.allStates().size() - 1 ; 
        boolean mark[][] = new boolean[size][size]; //tworzymy tablice w ktorej bedziemy oznaczac stany rownowazne
        for(int i = 0; i<size; i++) {
            for(int a = 0; a<size; a++)
            mark[i][a] = true; // na poczatek oznaczamy ze kazdy z nich jest rownowazny
        }
        
        for(int i = 0; i<size; i++) {
            if( i + 1 < size)
            {
                for(int a = (i+1); a<size; a++) {
                    if(automaton.allStates().get(i) == automaton.allStates().get(a)) { // jesli sa to te same stany, nie bierzemy ich pod uwage
                        break;
                    }
                    if(automaton.isFinal(automaton.allStates().get(i)) &&
                            !automaton.isFinal(automaton.allStates().get(a))) { //jesli jeden ze stanow jest akceptujacy a drugi nie, nie sa to stany rownowazne
                        mark[i][a] = false;
                        break;
                    }
                    if(!automaton.isFinal(automaton.allStates().get(i)) &&
                            automaton.isFinal(automaton.allStates().get(a))) { //analogicznie do poprzedniej sytuacji
                        mark[i][a] = false;
                        break;
                    }
                }
            }
        }
        for(int i =0 ; i<size; i++) {
            if( i + 1 < size )
            {
                for(int a=(i+1); a<size; a++) {
                    for(OutgoingTransition itransition : automaton.allOutgoingTransitions(automaton.allStates().get(i))) {
                        for(OutgoingTransition atransition : automaton.allOutgoingTransitions(automaton.allStates().get(a))) {
                            State state1 = itransition.getTargetState();
                            State state2 = atransition.getTargetState();
                            if(state1 == state2)
                                break;
                            if(!mark[automaton.allStates().indexOf(state1) - 1][automaton.allStates().indexOf(state2) - 1]) //teraz jesli jakas para jest oznaczona jako nierownowazna i osiagamy ja jako target state z innej pary, to ta inna para takze jest nierownowazna
                                mark[i][a] = false;
                        }
                    }
                }
            }
        }
        for(int i = 0; i<size; i++)
            mark[i][i] = false;
        //Scalanie stanów równoważnych
        returnAutomaton = automaton;
        for(int i = 0; i<size; i++)
        {
            if( i + 1 < size )
            {
                for(int a = (i+1); a<size; a++)
                {
                    if(mark[i][a] == true) {
                        List<State> prevStates = new ArrayList<State>();
                        List<OutgoingTransition> prevTransitions = new ArrayList<OutgoingTransition>();
                        prevStates = findPreviousState(returnAutomaton, returnAutomaton.allStates().get(a));
                        for(State state : prevStates) {
                            prevTransitions = findPreviousStateTransitions(returnAutomaton, state, returnAutomaton.allStates().get(a));
                            for(OutgoingTransition transition : prevTransitions)
                                returnAutomaton.addTransition(state, returnAutomaton.allStates().get(i), transition.getTransitionLabel());
                            }
                        returnAutomaton.allStates().remove(a);
                    }
                }
            }
        }
        
        return returnAutomaton;
        
    }
}


