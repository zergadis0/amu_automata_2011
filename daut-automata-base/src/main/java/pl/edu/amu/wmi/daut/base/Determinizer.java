package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
        

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
        /*
        public String myAlphabet() {
            
            if (stateSet.isEmpty())
                return null;
            String alpha = new String();
            for (State s: stateSet) {
                for (OutgoingTransition tl: spec.allOutgoingTransitions(s)) {
                    char a = tl.getTransitionLabel().canAcceptCharacter(c);
                    if (alpha.contains(a));
                }
            }
            return alpha;
        }*/
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
        
        if (nfa.isDeterministic()) {
            emptyDfa = (DeterministicAutomatonSpecification) nfa.clone();
        }
        
        Queue<StateSet> queueOfNewSets = new LinkedList<StateSet>();
        StateSet initialStSet = new StateSet(nfa.getInitialState());
        listOfSets.add(initialStSet );
        queueOfNewSets.offer(initialStSet );
        
        StateSet tmp;
        while ((tmp = queueOfNewSets.poll())!=null) {
            List<TransitionLabel> usedTrans = new LinkedList<TransitionLabel>();
            for (State stTmp : tmp.stateSet) {
                
            }
        }
    }
    
}
