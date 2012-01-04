package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;

public class TestEscapeOperator extends TestCase {

    public void TestCreateFixedAutomaton() {
        EscapeOperator operator = new EscapeOperator('a');
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        assertFalse(automaton.isEmpty());
        assertTrue(result.accepts("\7"));
        assertFalse(result.accepts("\\7"));
        assertFalse(result.accepts("\6"));
        assertFalse(result.accepts("c"));
    }

    public void TestCreateFixedAutomaton1() {

        EscapeOperator operator1 = new EscapeOperator('n');
        AutomatonSpecification automaton1 = operator1.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result1 =
                new NondeterministicAutomatonByThompsonApproach(automaton1);

        assertFalse(automaton1.isEmpty());
        assertTrue(result1.accepts("\n"));
        assertFalse(result1.accepts("\\nn"));
        assertFalse(result1.accepts("n"));

    }

    public void TestCreateFixedAutomaton2() {

        EscapeOperator operator2 = new EscapeOperator('t');
        AutomatonSpecification automaton2 = operator2.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result2 =
                new NondeterministicAutomatonByThompsonApproach(automaton2);

        assertFalse(automaton2.isEmpty());
        assertTrue(result2.accepts("\t"));
        assertFalse(result2.accepts("\\tt"));
        assertFalse(result2.accepts("t"));
    }

    public void TestCreateFixedAutomaton3() {

        EscapeOperator operator3 = new EscapeOperator('f');
        AutomatonSpecification automaton3 = operator3.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result3 =
                new NondeterministicAutomatonByThompsonApproach(automaton3);

        assertFalse(automaton3.isEmpty());
        assertTrue(result3.accepts("\f"));
        assertFalse(result3.accepts("\\ff"));
        assertFalse(result3.accepts("f"));
    }

    public void TestCreateFixedAutomaton4() {

        EscapeOperator operator4 = new EscapeOperator('r');
        AutomatonSpecification automaton4 = operator4.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result4 =
                new NondeterministicAutomatonByThompsonApproach(automaton4);

        assertFalse(automaton4.isEmpty());
        assertTrue(result4.accepts("\r"));
        assertFalse(result4.accepts("\\rr"));
        assertFalse(result4.accepts("r"));
    }

    public void TestCreateFixedAutomaton5() {

        EscapeOperator operator5 = new EscapeOperator('i');
        AutomatonSpecification automaton5 = operator5.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result5 =
                new NondeterministicAutomatonByThompsonApproach(automaton5);

        assertFalse(automaton5.isEmpty());
        assertTrue(result5.accepts("i"));
        assertFalse(result5.accepts("\\ii"));
        assertFalse(result5.accepts("\\i"));
    }

    public void TestCreateFixedAutomaton6() {

        EscapeOperator operator6 = new EscapeOperator('v');
        AutomatonSpecification automaton6 = operator6.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result6 =
                new NondeterministicAutomatonByThompsonApproach(automaton6);

        assertFalse(automaton6.isEmpty());
        assertTrue(result6.accepts("\13"));
        assertFalse(result6.accepts("\\1313"));
        assertFalse(result6.accepts("13"));
    }
}
