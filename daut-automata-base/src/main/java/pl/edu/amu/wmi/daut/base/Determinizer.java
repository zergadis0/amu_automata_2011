package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
        

public class Determinizer {
    
    /**
     * Klasa reprezentujaca zbior stanow wykorzystywana przy metodzie determinizacji.
     */
    public class StateSet {
        
        public Set<State> stateSet;
        
        public StateSet () {
            
            stateSet = new HashSet<State>();
        }
        
        public StateSet (State s) {
            
            stateSet = new HashSet<State>();
            stateSet.add(s);
        }
        
        public void add(State s) {
            
            stateSet.add(s);
        }
        
        public boolean contains(State s) {
            
            return stateSet.contains(s);
        }
        
        public boolean equals(StateSet sS) {
            
            return stateSet.equals(sS.stateSet);
        }
        
    }
    
    /**
     * Lista zbiorow stanow
     */
    public List<StateSet> listOfSets = new ArrayList<StateSet>();
    
    /**
     * Sprawdza, czy zbior sS juz istnieje i zwraca ten zbior; null jesli nie istnieje.
     */
    public StateSet checkExistingSets(StateSet sS) {
        
        for (StateSet tmpSS : listOfSets) {
            if (tmpSS.equals(sS)) {
                return tmpSS;
            }
        }
        return null;
    }
    
    /**
    * Dla nieterministycznego automatu skończenie stanowego nfa tworzy równoważny automat deterministyczny.
    * Automat deterministyczny będzie tworzony poprzez rozbudowywanie pustego automatu emptyDfa.
    */
    public void determinize(AutomatonSpecification nfa, DeterministicAutomatonSpecification emptyDfa) {
        
        Queue<StateSet> queueOfNewSets = new LinkedList<StateSet>();
        StateSet initialSS = new StateSet(nfa.getInitialState());
        listOfSets.add(initialSS );
        queueOfNewSets.offer(initialSS);
        
        
    }
    
}
