package pl.edu.amu.wmi.daut.base;

import java.util.List;
// mała modyfikacja na potrzeby pierwszych ćwiczeń
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

    @Override
    public String toString() {
        String pilgrim = "Automaton:\n-States: ";
        List<State> link = allStates();
        for (int i = 0; i < link.size(); i++) {
            pilgrim += "q" + i + " ";
        }
        pilgrim += "\nTransitions:\n";
        for (int i = 0; i < link.size(); i++) {
            List<OutgoingTransition> listOfTrans = allOutgoingTransitions(link.get(i));
            for (int j = 0; j < listOfTrans.size(); j++) {
                pilgrim += "q" + i + " -> " + "q";
                State target = listOfTrans.get(j).getTargetState();
                for (int m = 0; m < link.size(); m++) {
                    if (target == link.get(m)) {
                        pilgrim += m + " ";
                        break;
                    }
                }
                pilgrim += "\n";
            }
            pilgrim += "\n";
        }
        pilgrim += "Initial state: ";
        for (int i = 0; i < link.size(); i++) {
            if (link.get(i) == getInitialState()) {
                pilgrim += "q" + i + "\nFinalStates: ";
                break;
            }
        }
        for (int i = 0; i < link.size(); i++) {
            if (isFinal(link.get(i))) {
                pilgrim += "q" + i + " ";
            }
        }
        return pilgrim;
    };
};
