package pl.edu.amu.wmi.daut.base;

import java.util.List;

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
};
