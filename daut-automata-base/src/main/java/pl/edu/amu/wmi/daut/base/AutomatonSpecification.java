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

    public String toString()
    {
        String pilgrim = "Automaton:\n-States: ";
        List<State> link = this.AllStates();
        for (int i=0; i<link.length; i++) {
            pilgrim += "q" + i + " ";
        };
        pilgrim += "\nTransitions:\n";
        for (int i=0; i<link.length; i++) {
            List<OutgoingTransition> listOfTrans = this.allOutgoingTransitions(link[i]);
            for (int j=0; j<listOfTrans.length; j++){
                pilgrim += "q" + i + " -> " + "q";
                State target = listOfTrans[j].getTargetState();
                for (int m=0; m<link.length; m++) {
                    if (target == link[m]) {
                        pilgrim += m + " ";
                        break;
                    }
                }
                pilgrim += "\n";
            }
            pilgrim += "\n";
        };
        pilgrim += "Initial state: ";
        for (int i=0; i<link.length; i++) {
            if(link[i] == link.getInitialState()) {
                pilgrim += "q" + i + "\nFinalStates: ";
                break;
            }
        };
        for (int i=0; i<link.length; i++) {
            if (isFinal (link[i]) ) {
                pilgrim += "q" + i + " ";
            };
        };
        return pilgrim;
    };
};
