package pl.edu.amu.wmi.daut.base;

/**
 * Klasa Epsilon Przejścia
 */
public class EpsilonTransitionLabel extends TransitionLabel {

    public EpsilonTransitionLabel(){ };
    /**
     * Zwraca true gdy przejście o danej etykiecie może nastąpić
     * bez "zjedzenia" znaku z wejścia.
     */
    @Override
    public boolean canBeEpsilon() {
        return true;
    };

    /**
     * Zwraca true gdy przejście może nastąpić po znaku 'c'.
     */
    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    };

    /**
     * Zwraca true gdy przejście jest puste.
     *
     * Puste przejście ma specjalny charakter (nie jest to epsilon-przejście!),
     * jest używane przez metody zwracające TransitionLabel do zaznaczenia, że
     * nie udało się wygenerować/znaleźć żądanego przejścia.
     */
    @Override
    public boolean isEmpty() {
        return false;
    };
    
    @Override
    public String toString() {
        return "ε";
    }

    @Override
    protected TransitionLabel intersectWith(TransitionLabel label) {
        if(label.canBeEpsilon()) {
            return this;
        } else {
            return new EmptyTransitionLabel();
        }
    };
}
