package pl.edu.amu.wmi.daut.base;
import java.util.Vector;
import java.util.List;


class EffectiveDeterministicAutomaton extends DeterministicAutomatonSpecification
{
    static class MyState implements State {
        private static int TRANSITIONS_LENGTH = Character.MAX_VALUE + 1;
        private MyState[] m_characterTargetState;
        private MyState m_epsilonTargetState;
        private int m_hasCharacterTransition;
        private boolean m_isFinal;
        private Vector<OutgoingTransition> m_outgoingTransitions;
        private EffectiveDeterministicAutomaton m_owner;


        public MyState(EffectiveDeterministicAutomaton owner) {
            m_characterTargetState = new MyState[TRANSITIONS_LENGTH];
            for (int i = 0; i < TRANSITIONS_LENGTH; ++i) {
                m_characterTargetState[i] = null;
            }
            m_epsilonTargetState = null;
            m_hasCharacterTransition = 0;
            m_isFinal = false;
            m_outgoingTransitions = new Vector<OutgoingTransition>();
            m_owner = owner;
        }


        private void addOutgoingTransition(OutgoingTransition transition) {
            m_outgoingTransitions.addElement(transition);
        }


        public EffectiveDeterministicAutomaton getOwner() {
            return m_owner;
        }


        public MyState getEpsilonTargetState() {
            return m_epsilonTargetState;
        }


        public Vector<OutgoingTransition> getOutgoingTransitions() {
            return m_outgoingTransitions;
        }


        public MyState getTargetState(char c) {
            return m_characterTargetState[c];
        }


        public boolean hasCharacterTransition() {
            return (m_hasCharacterTransition > 0);
        }


        public boolean hasEpsilonTransition() {
            return (m_epsilonTargetState != null);
        }


        public boolean isFinal() {
            return m_isFinal;
        }


        public void setEpsilonTargetState(MyState state) {
            m_epsilonTargetState = state;
        }


        public void setFinal(boolean value) {
            m_isFinal = value;
        }


        public void setTargetState(char c, MyState state) {
            if (state != m_characterTargetState[c]) {
                if (m_characterTargetState[c] == null)
                    ++m_hasCharacterTransition;
                else if (state == null)
                    --m_hasCharacterTransition;
                m_characterTargetState[c] = state;
            }
        }
    }


    private MyState m_initialState;
    private Vector<State> m_states;


    @Override
    public State addState() {
        MyState myState = new MyState(this);
        m_states.addElement(myState);
        return myState;
    }


    @Override
    public void addTransition(State from, State to, TransitionLabel label) {
        if (label.isEmpty())
            return;

        MyState myFrom = assertStateValid(from);
        MyState myTo = assertStateValid(to);
        for (int i = 0; i <= Character.MAX_VALUE; ++i) {
            char c = (char) i;
            if (label.canAcceptCharacter(c)) {
                MyState currentTargetState = myFrom.getTargetState(c);
                if (currentTargetState == null)
                    myFrom.setTargetState(c, myTo);
                else if (currentTargetState != myTo)
                    throw new IllegalArgumentException();
            }
        }

        if (label.canBeEpsilon()) {
            MyState epsilonTargetState = myFrom.getEpsilonTargetState();
            if (epsilonTargetState == null)
                myFrom.setEpsilonTargetState(myTo);
            else if (epsilonTargetState != myTo)
                throw new IllegalArgumentException();
        }

        if (myFrom.hasCharacterTransition() && myFrom.hasEpsilonTransition())
            throw new IllegalArgumentException();

        myFrom.addOutgoingTransition(new OutgoingTransition(label, to));
    }


    @Override
    public List<OutgoingTransition> allOutgoingTransitions(State state) {
        MyState myState = assertStateValid(state);
        return myState.getOutgoingTransitions();
    }


    @Override
    public List<State> allStates() {
        return m_states;
    }


    private MyState assertStateValid(State state) {
        if (state != null) {
            if (state instanceof MyState) {
                MyState myState = (MyState) state;
                if (myState.getOwner() == this)
                    return myState;
            }
            throw new IllegalArgumentException();
        }
        throw new NullPointerException();
    }


    public EffectiveDeterministicAutomaton() {
        m_initialState = null;
        m_states = new Vector<State>();
    }


    @Override
    public State getInitialState() {
        return m_initialState;
    }


    @Override
    public boolean isFinal(State state) {
        MyState myState = assertStateValid(state);
        return myState.isFinal();
    }


    @Override
    public void markAsInitial(State state) {
        m_initialState = assertStateValid(state);
    }


    @Override
    public void markAsFinal(State state) {
        MyState myState = assertStateValid(state);
        myState.setFinal(true);
    }


    @Override
    public State targetState(State from, char c) {
        MyState myFrom = assertStateValid(from);
        return myFrom.getTargetState(c);
    }
}

