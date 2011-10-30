package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Klasa abstrakcyjna reprezentująca specyfikację (opis) automatu
 * (jakie są stany, przejścia, który stan jest stanem początkowym,
 * które stany są stanami akceptującymi).
 *
 * Uwaga: klasa ta nie reprezentuje działającego automatu (nie ma tu funkcji
 * odpowiadających na pytanie, czy automat akceptuje napis, czy nie),
 * tylko "zawartość" automatu.
 */
abstract class AutomatonSpecification {

    // metody "budujące" automat

    /**
     * Dodaje nowy stan do automatu.
     *
     * Zwraca dodany stan.
     */
    public abstract State addState();

    /**
     * Dodaje przejście od stanu 'from' do stanu 'to' etykietowane etykietą transitionLabel.
     */
    public abstract void addTransition(State from, State to, TransitionLabel transitionLabel);

    /**
     * Dodaje przejście od stanu 'from' do nowo utworzonego stanu 'to' etykietowane etykietą
     * transitionLabel, a następnie zwraca utworzony stan.
     */
    public State addTransition(State from, TransitionLabel transitionLabel) {

        State to = addState();
        addTransition(from, to, transitionLabel);

        return to;
    }

    /**
     * Tworzy "gałąź" w automacie.
     * Metoda dodaje ciąg przejść od stanu początkowego automatu,
     * dla podanej listy etykiet przejść.
     * Metoda zwraca (nowo utworzony) stan docelowy ostatniego przejścia.
     */
    public State addBranch(State from, List<TransitionLabel> oTransition) {
        State prev = from;
        State next = prev;

         for (TransitionLabel transition : oTransition) {
             prev = addTransition(next, transition);
             next = prev;
         }
        return prev;
    }

    /**
     * Oznacza stan jako początkowy.
     */
    public abstract void markAsInitial(State state);

    /**
     * Oznacza stan jako końcowy (akceptujący).
     */
    public abstract void markAsFinal(State state);

    // metody zwracające informacje o automacie

    /**
     * Zwraca listę wszystkich stanów.
     *
     * Stany niekoniecznie muszą być zwrócone w identycznej
     * kolejności jak były dodane.
     */
    public abstract List<State> allStates();

    /**
     * Zwraca listę wszystkich przejść wychodzących ze stanu 'from'.
     *
     * Przejścia niekoniecznie muszą być zwrócone w identycznej
     * kolejności jak były dodane.
     */
    public abstract List<OutgoingTransition> allOutgoingTransitions(State from);

    /**
     * Zwraca stan początkowy.
     */
    public abstract State getInitialState();

    /**
     * Zwraca true wgdy stan jest stanem końcowym.
     */
    public abstract boolean isFinal(State state);

    /**
     * Metoda sprawdza czy automat jest pusty.
     */
    public boolean isEmpty() {

        List<State> states = allStates();
        if (states.isEmpty())
            return true;
        return false;
    }

    /**
     * Zwraca zawartość automatu w czytelnej dla człowieka postaci String'a.
     */
    @Override
    public String toString() {
        StringBuffer pilgrim = new StringBuffer("Automaton:\n-States: ");
        List<State> link = allStates();
        for (int i = 0; i < link.size(); i++) {
            pilgrim.append("q" + i + " ");
        }
        pilgrim.append("\n-Transitions:\n");
        for (int i = 0; i < link.size(); i++) {
            List<OutgoingTransition> listOfTrans = allOutgoingTransitions(link.get(i));
            for (int j = 0; j < listOfTrans.size(); j++) {
                pilgrim.append("  q" + i + " -" + listOfTrans.get(j).getTransitionLabel() + "-> q");
                State target = listOfTrans.get(j).getTargetState();
                for (int m = 0; m < link.size(); m++) {
                    if (target == link.get(m)) {
                        pilgrim.append(m);
                        break;
                    }
                }
                pilgrim.append("\n");
            }
        }
        pilgrim.append("-Initial state: ");
        for (int i = 0; i < link.size(); i++) {
            if (link.get(i) == getInitialState()) {
                pilgrim.append("q" + i + "\n-Final states: ");
                break;
            }
        }
        for (int i = 0; i < link.size(); i++) {
            if (isFinal(link.get(i))) {
                pilgrim.append("q" + i + " ");
            }
        }
        return pilgrim.toString();
    };
   /**
     * Sprawdza, czy automat jest deterministyczny (to znaczy, czy ma
     * przynajmniej jeden stan, czy nie zawiera epsilon-przejść (za wyjątkiem
     * sytuacji, gdy epsilon-przejście jest jedynym sposobem wyjścia ze stanu)
     * oraz czy przejścia z danego stanu do innych stanów odbywają się po
     * różnych znakach).
     */
    public boolean isDeterministic() {
        List<State> states = allStates();

        if (states.isEmpty())
            return false;

        for (State state : states) {
            List<OutgoingTransition> transitions = allOutgoingTransitions(state);

            if (transitions.size() <= 1)
                continue;

            for (int i = 0; i < transitions.size(); ++i) {
                TransitionLabel label = transitions.get(i).getTransitionLabel();

                if (label.canBeEpsilon())
                    return false;

                for (int j = i + 1; j < transitions.size(); ++j) {
                    TransitionLabel label2 = transitions.get(j).getTransitionLabel();
                    if (!label2.intersect(label).isEmpty())
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Dodaje przejście od stanu state z powrotem do tego samego stanu
     * po etykiecie transitionLabel.
     */
    public void addLoop(State state, TransitionLabel transitionLabel) {

        addTransition(state, state, transitionLabel);
    }

    /**
     * Zwraca obiekt typu String, który zawiera gotowy kod w języku DOT służący do
     * przedstawienia automatu w formie graficznej, (w ubuntu pakiet
     * graphviz). Z konsoli wywołuje się przykładowo w następujący sposób: dot
     * -Tpng -O plik_zkodem.dot który tworzy plik-schemat zapisany w formacie
     * png. Więcej w: man dot.
     *
     * @return Kod źródłowy schematu w języku DOT.
     */
    public String getDotGraph() {

        class DotGraph {
            private StringBuffer dotCode;
            private List<State> states;

            public DotGraph() {
                dotCode = new StringBuffer();
                states = allStates();
            }

            private void getDotGraphIntro() {
                dotCode.append(
                        "digraph finite_state_machine {\n"
                         + "    rankdir=LR;\n"
                         + "    size=\"8,5\"\n"
                         + "    node [style=filled fillcolor=\"#00ff005f\" shape = ");
                if (isFinal(getInitialState())) dotCode.append("double");
                dotCode.append("circle];\n"
                               + "    \"State #" + states.indexOf(getInitialState()) + "\";\n"
                               + "    node [shape = doublecircle style=filled "
                               + "fillcolor=\"#00000000\"];\n    ");
            }

            private void getDotGraphFinalStates() {
                for (State it : states) {
                    if (isFinal(it)) {
                        dotCode.append("\"State #" + states.indexOf(it) + "\" ");
                    }
                }
            }

            private void getEdgeLabel(State state, int target, String label) {
                if (label.length() != 0) {
                    dotCode.append("    \"State #");
                    dotCode.append(states.indexOf(state) + "\"");
                    dotCode.append(" -> ");
                    dotCode.append("\"State #");
                    dotCode.append(target + "\"");
                    dotCode.append(" [ label = \"" + label
                            + "\" ]");
                    dotCode.append(";\n");
                }

            }

            private void getDotGraphEdges() {
                for (State it : states) {
                    final StringBuffer[] labelList = new StringBuffer[states.size()];
                    for (int i = 0; i < labelList.length; ++i) {
                        labelList[i] = new StringBuffer();
                    }

                    final List<OutgoingTransition> edges = allOutgoingTransitions(it);

                    for (OutgoingTransition edgeIt : edges) {
                        if (labelList[states.indexOf(edgeIt.getTargetState())].length() == 0) {
                            labelList[states.indexOf(edgeIt.getTargetState())]
                                    .append(edgeIt.getTransitionLabel());
                        } else {
                            labelList[states.indexOf(edgeIt.getTargetState())]
                                    .append(", " + edgeIt.getTransitionLabel());
                        }
                    }

                    for (int i = 0; i < labelList.length; ++i) {
                        getEdgeLabel(it, i, labelList[i].toString());
                    }
                }
            }

            public String  getDotGraph() {
                getDotGraphIntro();
                getDotGraphFinalStates();
                dotCode.append(";\n" + "    node [shape = circle];\n" + "");
                getDotGraphEdges();
                dotCode.append("\n}\n");
                return dotCode.toString();
            }
        }

        DotGraph tmp = new DotGraph();
        return tmp.getDotGraph();
    }

    public int countStates() {
        return allStates().size();
    }

    public int countTransitions() {
        int sum = 0;
        for (State state : allStates()) {
            sum += allOutgoingTransitions(state).size();
        }
        return sum;
    }

    /**
     * Wstawia począwszy od stanu state kopię automatu automaton.
     * Stan state będzie utożsamiony ze stanem
     * początkowym automatu automaton.
     */
    void insert(State state, AutomatonSpecification automaton) {
      List<State> loadedStates = automaton.allStates();
      HashMap<State, State> connectedStates = new HashMap<State, State>();
      State automatonInitialState = automaton.getInitialState();
      for (State currentState : loadedStates) {
        if (currentState == automatonInitialState)
          connectedStates.put(currentState, state);
        else
          connectedStates.put(currentState, this.addState());
      }
      for (State currentState : loadedStates) {
        List<OutgoingTransition> list = automaton.allOutgoingTransitions(currentState);
        for (OutgoingTransition transition : list) {
          this.addTransition(connectedStates.get(currentState),
          connectedStates.get(transition.getTargetState()), transition.getTransitionLabel());
        }
      }
    }

    public boolean isFull(String alphabet) {
        int index;
        for (State state : allStates()) {
            for (int i = 0; i < alphabet.length(); i++) {
                for (OutgoingTransition transition : allOutgoingTransitions(state)) {
                    index = allOutgoingTransitions(state).indexOf(transition);
                    if (transition.getTransitionLabel().canAcceptCharacter(alphabet.charAt(i)))
                        break;
                    else if (index == allOutgoingTransitions(state).size()
                            && !transition.getTransitionLabel()
                            .canAcceptCharacter(alphabet.charAt(i)))
                        return false;
                }
            }
        }
        return true;
    }

    public void makeFull(String alphabet) {
        if (!isFull(alphabet)) {
            State trash = addState();
            int indeks;
            for (State state : allStates()) {
                for (int i = 0; i < alphabet.length(); i++) {
                    for (OutgoingTransition transition1 : allOutgoingTransitions(state)) {
                        indeks = allOutgoingTransitions(state).indexOf(transition1);
                        if (transition1.getTransitionLabel().canAcceptCharacter(alphabet.charAt(i)))
                            break;
                        else if (indeks == allOutgoingTransitions(state).size()
                                && !transition1.getTransitionLabel()
                                .canAcceptCharacter(alphabet.charAt(i)))
                            addTransition(state, trash,
                                    new CharTransitionLabel(alphabet.charAt(i)));
                    }
                }
            }
        }
    }

    /**
     * Sprawdza, czy akceptowany język jest niesko�?czony.
     */
    public boolean isInfinite() {
            return  findFinals(getInitialState(), new ArrayList<State>());
    }

    private boolean findFinals(State state, List<State> history){
    boolean result = false;
    
    if (isFinal(state))
        return checkForLoop(state, new ArrayList<State>());
    
    if (allOutgoingTransitions(state).size() == 0)
                return false;
    
    for (State his : history)
            if (his == state)
                return false;
       history.add(state);
    
    for (OutgoingTransition child : allOutgoingTransitions(state)) {
                result = result || findFinals(child.getTargetState(), history);
                if(result)
                break;
        }
            return result;
    }

    private boolean checkForLoop(State state, List<State> history) { 
    for (State his : history)
        if (his == state)
            if(isFinal(state))
                return true;
            else return false;
    if (allOutgoingTransitions(state).size() == 0)
            return false;
        history.add(state);
        boolean result = false;
        for (OutgoingTransition child : allOutgoingTransitions(state)) {
              result = result || checkForLoop(child.getTargetState(), history);
              if(result)
             break;
        }
        return result;
    }
};
