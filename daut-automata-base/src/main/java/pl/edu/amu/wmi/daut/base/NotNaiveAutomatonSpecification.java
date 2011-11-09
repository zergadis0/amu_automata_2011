package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.LinkedList;

/**
 * Ukonkretnienie klasy abstrakcyjnej AutomatonSpecification.
 */
class NotNaiveAutomatonSpecification extends AutomatonSpecification {

    static class NotNaiveState implements State {

        /**
         * Konstruuje stan.
         */
        public NotNaiveState() {
        }

        public List<OutgoingTransition> returnOutgoingTrasitions() {
            return outgoingTransitions;
        }
        
        private LinkedList<OutgoingTransition> outgoingTransitions
                = new LinkedList<OutgoingTransition>();
    }

    /**
     * Pomocnicza klasa reprezentujaca przejscie.
    
    private static class NotNaiveTransition {

        
        public NotNaiveTransition(NotNaiveState aFrom, NotNaiveState aTo,
                TransitionLabel aTransitionLabel) {
            from = aFrom;
            to = aTo;
            transitionLabel = aTransitionLabel;
        }

        /
        public NotNaiveState getSourceState() {
            return from;
        }

        
        public NotNaiveState getTargetState() {
            return to;
        }

        
        public TransitionLabel getTransitionLabel() {
            return transitionLabel;
        }
        private NotNaiveState from;
        private NotNaiveState to;
        private TransitionLabel transitionLabel;
    } */
    
    

    public NotNaiveState addState() {
        NotNaiveState newState = new NotNaiveState();
        allStates.add(newState);
        return newState;
    }

    public void addTransition(State from, State to, TransitionLabel transitionLabel) {
        ((NotNaiveState)from).outgoingTransitions.add(new OutgoingTransition((CharTransitionLabel)transitionLabel, (State) to));
    }
    /*
    public void addNaiveTransition(State from, State to, TransitionLabel transitionLabel) {
        naiveTransitions.add(new NotNaiveTransition((NotNaiveState) from, (NotNaiveState) to,
                transitionLabel));
    }*/
 
    public void markAsInitial(State state) {
        initialState = (NotNaiveState) state;
    }

    public void markAsFinal(State state) {
        finalStates.add((NotNaiveState) state);
    }

    public List<State> allStates() {
        return allStates;
    }

    public List<OutgoingTransition> allOutgoingTransitions(State from) {
        return ((NotNaiveState) from).outgoingTransitions;
    }

    public State getInitialState() {
        return initialState;
    }

    public boolean isFinal(State state) {
        for (NotNaiveState someState : finalStates) {
            if (someState == state) {
                return true;
            }
        }

        return false;
    }   
    
    public List<OutgoingTransition> getOutReturnOutgoingTransitions(State from) {
        return ((NotNaiveState)from).returnOutgoingTrasitions();
    }
    
    
            
            
    private LinkedList<State> allStates = new LinkedList<State>();
    private NotNaiveState initialState;
    private LinkedList<NotNaiveState> finalStates = new LinkedList<NotNaiveState>();
   /* private LinkedList<NotNaiveTransition> naiveTransitions = new LinkedList<NotNaiveTransition>();

     */
}
