package pl.edu.amu.wmi.daut.base;

/**
 * Klasa implementująca interfejs: java.util.Iterator
 * Iterator umożliwia iterowanie po kolekcjach. Iteratory tworzone są metodą iterator() odpowiedniej klasy kolekcji.
 */

public class AllAcceptedWords implements java.util.Iterator {

    @Override
    /**
     * Do sprawdzenia, czy odwiedzono wszystkie elementy kolekcji stosuje się metodę hasNext()
     */
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Metoda next() przesuwa iterator i zwraca wartość, na którą wskazuje iterator. Zaraz po utworzeniu iterator wskazuje na specjalną wartość przed pierwszym elementem, tak by pierwszy element był pobrany przy pierwszym wywołaniu next().
     * 
     * Każde wywołanie metody `next` zwraca/podaje kolejne napisy akceptowane przez automat.
     */
    public Object next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * Dla kolekcji, które obsługują tę funkcjonalność, ostatnio odwiedzony element można usunąć z kolekcji metodą remove() iteratora.
     */
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
