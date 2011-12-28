package pl.edu.amu.wmi.daut.re;

import java.util.List;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;
/**
 * Test klasy RegexpUtilities.
 */
public class TestRegexpUtilities extends TestCase {
    /**
     * Test metody createAutopmatonFromOperatorTree dla wyrażenia regularnego a.
     */
    public final void test1() throws Exception {
        //Tworzymy drzewo dla wyrażenia regularnego a.
        RegexpOperator root = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutopmatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("aa"));
        assertFalse(result.accepts("testujtest"));

    }
    /**
     * Test metody createAutopmatonFromOperatorTree
     *  dla wyrażenia regularnego a|b.
     */
    public final void test2() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego a|b
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree1);
        subtrees.add(subtree2);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutopmatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("ab"));
        assertFalse(result.accepts("aaaaa"));
        assertFalse(result.accepts("testujtest"));
    }

    /**
     * Test metody createAutopmatonFromOperatorTree
     *  dla wyrażenia regularnego a{3,6}.
     */
    public final void test3() throws Exception {
        //Tworzymy drzewo dla wyrażenia regularnego a{3,6}
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root = new RangeNumberOfOccurrencesOperator(3, 6);
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree1);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

         //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutopmatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("aaa"));
        assertTrue(result.accepts("aaaa"));
        assertTrue(result.accepts("aaaaaa"));
        assertFalse(result.accepts("a"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("testujtest"));

    }
    /**
     * Test metody createAutopmatonFromOperatorTree
     * dla wyrażenia regularnego a?|b|c{4}.
     */
    public final void test4() throws Exception {
        //Tworzymy drzewo dla wyrażenia regularnego a?|b|c{4}
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root3 = new SingleCharacterOperator('c');
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree3 = new RegexpOperatorTree(root3, subtrees3);

        RegexpOperator root4 = new OptionalityOperator();
        List<RegexpOperatorTree> subtrees4 = new ArrayList<RegexpOperatorTree>();
        subtrees4.add(subtree1);
        RegexpOperatorTree subtree4 = new RegexpOperatorTree(root4, subtrees4);

        RegexpOperator root5 = new FixedNumberOfOccurrencesOperator(4);
        List<RegexpOperatorTree> subtrees5 = new ArrayList<RegexpOperatorTree>();
        subtrees5.add(subtree3);
        RegexpOperatorTree subtree5 = new RegexpOperatorTree(root5, subtrees5);

        RegexpOperator root6 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees6 = new ArrayList<RegexpOperatorTree>();
        subtrees6.add(subtree4);
        subtrees6.add(subtree2);
        RegexpOperatorTree subtree6 = new RegexpOperatorTree(root6, subtrees6);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree6);
        subtrees.add(subtree5);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutopmatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("b"));
        assertTrue(result.accepts("cccc"));
        assertTrue(result.accepts(""));
        assertFalse(result.accepts("abcccc"));
        assertFalse(result.accepts("ccc"));
        assertFalse(result.accepts("abc"));
        assertFalse(result.accepts("testujtest"));
    }
    /**
     * Test metody createAutopmatonFromOperatorTree
     * dla wyrażenia regularnego (a|b)*|c+|d|(e{2})*.
     */
    public final void test5() throws Exception {
        //Tworzymy drzewo dla wyrażenia regularnego (a|b)*|c+|d|(e{2})*
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root3 = new SingleCharacterOperator('c');
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree3 = new RegexpOperatorTree(root3, subtrees3);

        RegexpOperator root4 = new SingleCharacterOperator('d');
        List<RegexpOperatorTree> subtrees4 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree4 = new RegexpOperatorTree(root4, subtrees4);

        RegexpOperator root5 = new SingleCharacterOperator('e');
        List<RegexpOperatorTree> subtrees5 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree5 = new RegexpOperatorTree(root5, subtrees5);

        RegexpOperator root6 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees6 = new ArrayList<RegexpOperatorTree>();
        subtrees6.add(subtree1);
        subtrees6.add(subtree2);
        RegexpOperatorTree subtree6 = new RegexpOperatorTree(root6, subtrees6);

        RegexpOperator root7 = new KleeneStarOperator();
        List<RegexpOperatorTree> subtrees7 = new ArrayList<RegexpOperatorTree>();
        subtrees7.add(subtree6);
        RegexpOperatorTree subtree7 = new RegexpOperatorTree(root7, subtrees7);

        RegexpOperator root8 = new AtLeastOneOperator();
        List<RegexpOperatorTree> subtrees8 = new ArrayList<RegexpOperatorTree>();
        subtrees8.add(subtree3);
        RegexpOperatorTree subtree8 = new RegexpOperatorTree(root8, subtrees8);

        RegexpOperator root9 = new FixedNumberOfOccurrencesOperator(2);
        List<RegexpOperatorTree> subtrees9 = new ArrayList<RegexpOperatorTree>();
        subtrees9.add(subtree5);
        RegexpOperatorTree subtree9 = new RegexpOperatorTree(root9, subtrees9);

        RegexpOperator root10 = new KleeneStarOperator();
        List<RegexpOperatorTree> subtrees10 = new ArrayList<RegexpOperatorTree>();
        subtrees10.add(subtree9);
        RegexpOperatorTree subtree10 = new RegexpOperatorTree(root10, subtrees10);

        RegexpOperator root11 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees11 = new ArrayList<RegexpOperatorTree>();
        subtrees11.add(subtree7);
        subtrees11.add(subtree8);
        RegexpOperatorTree subtree11 = new RegexpOperatorTree(root11, subtrees11);

        RegexpOperator root12 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees12 = new ArrayList<RegexpOperatorTree>();
        subtrees12.add(subtree4);
        subtrees12.add(subtree10);
        RegexpOperatorTree subtree12 = new RegexpOperatorTree(root12, subtrees12);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree11);
        subtrees.add(subtree12);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutopmatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("aaaaaaa"));
        assertTrue(result.accepts("b"));
        assertTrue(result.accepts("bbbbb"));
        assertTrue(result.accepts(""));
        assertTrue(result.accepts("ccc"));
        assertTrue(result.accepts("d"));
        assertTrue(result.accepts("ee"));
        assertTrue(result.accepts("eeeeeeee"));
        assertFalse(result.accepts("ab"));
        assertFalse(result.accepts("abbbb"));
        assertFalse(result.accepts("dddd"));
        assertFalse(result.accepts("eeeee"));
        assertFalse(result.accepts("testujtest"));
    }

}
