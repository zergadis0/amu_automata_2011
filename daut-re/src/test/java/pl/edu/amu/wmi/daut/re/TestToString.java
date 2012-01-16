package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Testuje działanie metody toString().
 */
public class TestToString extends TestCase {

    /**
     * Test sprawdzający alternatywe i single_operator.
     */
    public final void testToString1() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new SingleCharacterOperator('a');
        RegexpOperator s2 = new BellCharacterOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
//        assertEquals(tree.getHumanReadableFormat(),
//                "ALTERNATIVE\n|_SINGLE_OPERATOR_ a\n|_BELL\n");
    }

    /**
     * Test sprawdzający konkatenacje, AnyCharOperator i AnyStringOperator.
     */
    public final void testToString2() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new ConcatenationOperator();
        RegexpOperator s1 = new AnyCharOperator();
        RegexpOperator s2 = new AnyStringOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
//        assertEquals(tree.getHumanReadableFormat(),
//                "CONCATENATION\n|_ANY_CHAR_\n|_ANY_STRING\n");
    }

    /**
     * Test sprawdzający DigitOperator i DoNothingOperator.
     */
    public final void testToString3() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new DigitOperator();
        RegexpOperator s2 = new NoDigitOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
//        assertEquals(tree.getHumanReadableFormat(),
//                "ALTERNATIVE\n|_DIGIT\n|_NO_DIGIT\n");
    }
}
