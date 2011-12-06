package pl.edu.amu.wmi.daut.re;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

public class RegexpUtilities {

	static AutomatonSpecification createAutopmatonFromOperatorTree (RegexpOperatorTree tree) throws RuntimeException
	{
            //przejdŸ przez drzewo stanów metod¹ post-order, przy pomocy dwóch stosów.
            Stack<RegexpOperatorTree> child = new Stack<RegexpOperatorTree>();
            Stack<RegexpOperatorTree> parent = new Stack<RegexpOperatorTree>();
            child.push(tree);
            while (!child.empty())
            {
                RegexpOperatorTree current = child.peek();
                parent.push(current);
                child.pop();
                
                for (RegexpOperatorTree subTree : current.getSubtrees())
                    child.push(subTree);
            }
            
            //na stosie "parent" mamy teraz wierzcho³ki w porz¹dku post-order!
            //w porz¹dku post-order chodzi o to, ¿e zawsze zaczynamy od nieodwiedzonych liœci
            //i idziemy powoli w kierunku korzenia drzewa.
            
            //utwórz mapê poddrzew na automaty przez nich utworzone.
            Map<RegexpOperatorTree, AutomatonSpecification> map = new HashMap<RegexpOperatorTree, AutomatonSpecification>();
            
            while (!parent.empty())
            {
                RegexpOperatorTree current = parent.peek();
                
                //utwórz listê automatów utworzonych przez synów wierzcho³ka.
                List<AutomatonSpecification> arguments = new ArrayList<AutomatonSpecification>();
                for (RegexpOperatorTree subTree : current.getSubtrees())
                {
                    //nie bêdzie tutaj odwo³ania do nieistniej¹cych kluczy ze
                    //wzgl. na charakter porz¹dku post-order. jeœli wyst¹pi tutaj
                    //exception, to znaczy, ¿e Ÿle zaimplementowaliœmy coœ wczeœniej.
                    AutomatonSpecification subTreeAutomaton = map.get(subTree);
                    arguments.add(subTreeAutomaton);
                }
                
                //utwórz automat, którego argumentami s¹ automaty wszystkich synów.
                AutomatonSpecification currentAutomaton = current.getRoot().createAutomaton(arguments);
                //zapamiêtaj automat dla danego wierzcho³ka. poniewa¿ liœcie siê
                //wykonaj¹ "najpierw", to nadchodz¹cy po tym rodzice tych liœci
                //bêd¹ mieli pe³n¹ informacjê o automatach utworzonych przez 
                //swoich synów...
                map.put(current, currentAutomaton);
                
                parent.pop();
                
                //usunêliœmy w³aœnie wierzcho³ek-korzeñ - zostaliœmy z pustym stosem, mo¿emy zwróciæ automat.
                if (parent.empty())
                    return currentAutomaton;
            }

            //ten kod nie powinien siê nigdy wykonaæ.
            throw new RuntimeException("");
    }
}
