package pl.edu.amu.wmi.daut.base;

/**
 *
 */
public class AutomataOperations {

    protected AutomataOperations() {
        throw new UnsupportedOperationException(); // prevents calls from subclass
    }
    /**
     * Metoda zwracajaca Automat akceptujacy jezyk bedacy dopelnieniem jezyka
     * akceptowanego przez Automat otrzymywany "na wejsciu".
     */
    public static AutomatonSpecification metodaKarola(AutomatonSpecification pierwszy) {
        return pierwszy;
    }
}
