package pl.edu.amu.wmi.daut.re;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.WhitespaceOperator.Factory;

/**
 * Test klasy WhitespaceOperator.
 */
public class TestWhitespaceOperator extends TestCase {

    public TestWhitespaceOperator() {
    }

    /**
     * Test metody createFixedAutomaton.
     */
    public void testCreateFixedAutomaton() {

        WhitespaceOperator operator = new WhitespaceOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());
        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("\t"));
        assertTrue(automaton.accepts("\n"));
        assertTrue(automaton.accepts("\f"));
        assertTrue(automaton.accepts("\r"));
        assertTrue(automaton.accepts("\u000B"));
        assertFalse(automaton.accepts("'"));
        assertFalse(automaton.accepts("%"));
        assertFalse(automaton.accepts("4"));
        assertFalse(automaton.accepts("0"));
    }

    /**
     * Test fabryki.
     */
    public void testFactory() {

        Factory factory = new Factory();
        assertEquals(factory.numberOfParams(), 0);
        RegexpOperator operator2 = factory.doCreateOperator(null);
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}
