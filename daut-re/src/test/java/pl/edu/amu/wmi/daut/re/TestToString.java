package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Testuje działanie metody toString().
 */
public class TestToString extends TestCase {

    /*
     * {@inheritDoc} 
     */
    public final void testToString1() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new SingleCharacterOperator('a');
        RegexpOperator s2 = new SingleCharacterOperator('b');
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_SINGLE_OPERATOR_ a\n|_SINGLE_OPERATOR_ b\n");
    }
    
    /*
     * 
     */
    public final void testToString3() throws Exception {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s0 = new ConcatenationOperator();
        RegexpOperator s1 = new KleeneStarOperator();
        RegexpOperator s2 = new OptionalityOperator();
        RegexpOperator s5 = new SingleCharacterOperator('a');
        RegexpOperator s6 = new SingleCharacterOperator('b');
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s2, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s0, subtrees);
        RegexpOperatorTree tree2 = new RegexpOperatorTree(s5, subtrees);
        RegexpOperatorTree tree3 = new RegexpOperatorTree(s6, subtrees);
        RegexpOperatorTree tree4 = new RegexpOperatorTree(s1, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        subtrees.add(tree2);
        subtrees.add(tree3);
        subtrees.add(tree4);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(tree.getHumanReadableFormat(),
                "ALTERNATIVE\n|_CONCATENATION_ a\n|_SINGLE_OPERATOR_ b\n");
    }
}
