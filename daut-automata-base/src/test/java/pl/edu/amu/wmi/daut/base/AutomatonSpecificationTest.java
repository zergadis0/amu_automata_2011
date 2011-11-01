/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Irminka
 */
public class AutomatonSpecificationTest {
    
    public AutomatonSpecificationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addState method, of class AutomatonSpecification.
     */
    @Test
    public void testAddState() {
        System.out.println("addState");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        State expResult = null;
        State result = instance.addState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTransition method, of class AutomatonSpecification.
     */
    @Test
    public void testAddTransition_3args() {
        System.out.println("addTransition");
        State from = null;
        State to = null;
        TransitionLabel transitionLabel = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.addTransition(from, to, transitionLabel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTransition method, of class AutomatonSpecification.
     */
    @Test
    public void testAddTransition_State_TransitionLabel() {
        System.out.println("addTransition");
        State from = null;
        TransitionLabel transitionLabel = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        State expResult = null;
        State result = instance.addTransition(from, transitionLabel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBranch method, of class AutomatonSpecification.
     */
    @Test
    public void testAddBranch() {
        System.out.println("addBranch");
        State from = null;
        List<TransitionLabel> oTransition = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        State expResult = null;
        State result = instance.addBranch(from, oTransition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markAsInitial method, of class AutomatonSpecification.
     */
    @Test
    public void testMarkAsInitial() {
        System.out.println("markAsInitial");
        State state = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.markAsInitial(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markAsFinal method, of class AutomatonSpecification.
     */
    @Test
    public void testMarkAsFinal() {
        System.out.println("markAsFinal");
        State state = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.markAsFinal(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allStates method, of class AutomatonSpecification.
     */
    @Test
    public void testAllStates() {
        System.out.println("allStates");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        List expResult = null;
        List result = instance.allStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allOutgoingTransitions method, of class AutomatonSpecification.
     */
    @Test
    public void testAllOutgoingTransitions() {
        System.out.println("allOutgoingTransitions");
        State from = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        List expResult = null;
        List result = instance.allOutgoingTransitions(from);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialState method, of class AutomatonSpecification.
     */
    @Test
    public void testGetInitialState() {
        System.out.println("getInitialState");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        State expResult = null;
        State result = instance.getInitialState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFinal method, of class AutomatonSpecification.
     */
    @Test
    public void testIsFinal() {
        System.out.println("isFinal");
        State state = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        boolean expResult = false;
        boolean result = instance.isFinal(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class AutomatonSpecification.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AutomatonSpecification.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDeterministic method, of class AutomatonSpecification.
     */
    @Test
    public void testIsDeterministic() {
        System.out.println("isDeterministic");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        boolean expResult = false;
        boolean result = instance.isDeterministic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLoop method, of class AutomatonSpecification.
     */
    @Test
    public void testAddLoop() {
        System.out.println("addLoop");
        State state = null;
        TransitionLabel transitionLabel = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.addLoop(state, transitionLabel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDotGraph method, of class AutomatonSpecification.
     */
    @Test
    public void testGetDotGraph() {
        System.out.println("getDotGraph");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        String expResult = "";
        String result = instance.getDotGraph();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countStates method, of class AutomatonSpecification.
     */
    @Test
    public void testCountStates() {
        System.out.println("countStates");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        int expResult = 0;
        int result = instance.countStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countTransitions method, of class AutomatonSpecification.
     */
    @Test
    public void testCountTransitions() {
        System.out.println("countTransitions");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        int expResult = 0;
        int result = instance.countTransitions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class AutomatonSpecification.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        State state = null;
        AutomatonSpecification automaton = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.insert(state, automaton);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFull method, of class AutomatonSpecification.
     */
    @Test
    public void testIsFull() {
        System.out.println("isFull");
        String alphabet = "";
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        boolean expResult = false;
        boolean result = instance.isFull(alphabet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeFull method, of class AutomatonSpecification.
     */
    @Test
    public void testMakeFull() {
        System.out.println("makeFull");
        String alphabet = "";
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        instance.makeFull(alphabet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prefixChecker method, of class AutomatonSpecification.
     */
    @Test
    public void testPrefixChecker() {
        System.out.println("prefixChecker");
        State state = null;
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        boolean expResult = false;
        boolean result = instance.prefixChecker(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class AutomatonSpecification.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        AutomatonSpecification instance = new AutomatonSpecificationImpl();
        AutomatonSpecification expResult = null;
        AutomatonSpecification result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AutomatonSpecificationImpl extends AutomatonSpecification {

        public State addState() {
            return null;
        }

        public void addTransition(State from, State to, TransitionLabel transitionLabel) {
        }

        public void markAsInitial(State state) {
        }

        public void markAsFinal(State state) {
        }

        public List<State> allStates() {
            return null;
        }

        public List<OutgoingTransition> allOutgoingTransitions(State from) {
            return null;
        }

        public State getInitialState() {
            return null;
        }

        public boolean isFinal(State state) {
            return false;
        }
    }
}
