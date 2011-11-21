package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;

/**
 * Klasa Testująca metodę zwracającą przykładowe słowo
 * akceprowane przez automat.
 * 
 * @author JakubS91
 */
public class TestGeneratorRandomWord extends TestCase {
    
    /**
     * Metoda zwraca deterministyczny automat sk. stanowy akceptujący słowa
     * z parzystą liczbą wystąpień 'a' i parzystą liczbą wystąpień 'b'
     * nad językiem {a,b}*
     * 
     * @return AutomatonSpecification 
     */
    private AutomatonSpecification getAutomatonA() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        
        State qpp = automaton.addState();
        State qnp = automaton.addState();
        State qnn = automaton.addState();
        State qpn = automaton.addState();
        
        automaton.addTransition(qpp, qnp, new CharTransitionLabel('a'));
        automaton.addTransition(qnp, qpp, new CharTransitionLabel('a'));
        
        automaton.addTransition(qnp, qnn, new CharTransitionLabel('b'));
        automaton.addTransition(qnn, qnp, new CharTransitionLabel('b'));
        
        automaton.addTransition(qnn, qpn, new CharTransitionLabel('a'));
        automaton.addTransition(qpn, qnn, new CharTransitionLabel('a'));
        
        automaton.addTransition(qpn, qpp, new CharTransitionLabel('b'));
        automaton.addTransition(qpp, qpn, new CharTransitionLabel('b'));
        
        automaton.markAsInitial(qpp);
        automaton.markAsFinal(qpp);
        
        return automaton;
    }
    
    /**
     * Metoda zwraca deterministyczny automat sk. stanowy akceptujący słowa
     * które zawierają ciąg "aba" nad językiem {a,b}*
     * 
     * @return AutomatonSpecification
     */
    private AutomatonSpecification getAutomatonB() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        State q3 = automaton.addState();
        
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addLoop(q0, new CharTransitionLabel('b'));
        
        automaton.addTransition(q1, q2, new CharTransitionLabel('b'));
        automaton.addLoop(q1, new CharTransitionLabel('a'));
        
        automaton.addTransition(q2, q3, new CharTransitionLabel('a'));
        automaton.addTransition(q2, q0, new CharTransitionLabel('b'));
        
        automaton.addLoop(q3, new CharTransitionLabel('a'));
        automaton.addLoop(q3, new CharTransitionLabel('b'));
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q3);
        
        return automaton;
    }
    
    /**
     * Metoda zwraca niedeterministyczny automat sk. stanowy akceptujący słowa
     * które zawierają ciąg literę 'a' na przedostatnim miejscu
     * nad językiem {a,b}*
     * 
     * @return AutomatonSpecification
     */
    private AutomatonSpecification getAutomatonC() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        
        automaton.addLoop(q0, new CharTransitionLabel('a'));
        automaton.addLoop(q0, new CharTransitionLabel('b'));
        automaton.addTransition(q0, q1, new CharTransitionLabel('b'));
        
        automaton.addTransition(q1, q2, new CharTransitionLabel('a'));
        automaton.addTransition(q1, q2, new CharTransitionLabel('b'));
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q2);
        
        return automaton;
    }
    
    /**
     * Metoda zwraca deterministyczny automat sk. stanowy akceptujący słowa
     * "a", "b" i "c" nad językiem {a, b, c}*
     * 
     * @return AutomatonSpecification
     */
    private AutomatonSpecification getAutomatonD() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        State q3 = automaton.addState();
        
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0, q2, new CharTransitionLabel('b'));
        automaton.addTransition(q0, q3, new CharTransitionLabel('c'));
        
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        automaton.markAsFinal(q2);
        automaton.markAsFinal(q3);
        
        return automaton;
    }
    
    /**
     * Sprawdza poprawność wygenerowanych słów. Sprawdza czy zwrócone słowo
     * przez metodę randomWord z klasy Generator jest akceptowane przez dany automat.
     * Test jest przeprowadzony określoną ilość razy.
     * 
     * @param automaton AutomatonSpecification
     * @param alphabet String
     * @param numberOfTests int
     */
    private void check(AutomatonSpecification automaton, String alphabet, int numberOfTests) {
        AutomatonByRecursion automatonCheck = new AutomatonByRecursion(automaton);
        Generator generator = new Generator();
        for (int i = 0; i < numberOfTests; i++) {
            String generatedWord = generator.randomWord(automaton, alphabet, automaton.getInitialState());
            assertTrue(automatonCheck.accepts(generatedWord));
        }
    }
    
    /**
     * Metoda sprawdza losowość wygenerowanych słów. Sprawdza czy zostały
     * wygenerowane wszystkie możliwe słowa przez metodę randomWord z klasy
     * Generator akceptowane przez dany automat.
     * Ilość wygenerowanych słów jest sprawdzana po wywołaniu metody
     * określoną ilość razy.
     * 
     * @param automaton AutomatonSpecification
     * @param alphabet String
     * @param numberOfTests int
     * @param allPossibilities int
     */
    private void checkAllPossibilities(AutomatonSpecification automaton, String alphabet, int numberOfTests, int allPossibilities) {
        List<String> words = new ArrayList<String>();
        Generator generator = new Generator();
        for (int i = 0; i < numberOfTests; i++) {
            String generatedWord = generator.randomWord(automaton, alphabet, automaton.getInitialState());
            if (Collections.binarySearch(words, generatedWord) != -1) {
                words.add(generatedWord);
                Collections.sort(words);
            }
        }
        if (words.size() == allPossibilities) {
            assertTrue(true);
        }
        else {
            assertTrue(false);
        }
    }
    
    /**
     * Główna metoda wywołująca metody testujące metodę randomWord z klasy
     * Generator.
     */
    public final void testGeneratorRandomWord() {
        check(getAutomatonA(), "ab", 100);
        check(getAutomatonB(), "ab", 100);
        check(getAutomatonC(), "ab", 100);
        
        checkAllPossibilities(getAutomatonD(), "abc", 1000, 3);
    }
}
