package pl.edu.amu.wmi.daut.re;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

public class RegexpUtilities {

	static AutomatonSpecification createAutopmatonFromOperatorTree (RegexpOperatorTree tree)
	{
		Stack<RegexpOperatorTree> stack = new Stack<RegexpOperatorTree>();
		Set<RegexpOperatorTree> visited = new HashSet<RegexpOperatorTree>();
		
		stack.push(tree);
		while (!stack.empty())
		{
			RegexpOperatorTree top = stack.pop();
			if (!visited.contains(top))
			{
				visited.add(top);
				List<RegexpOperatorTree> subTrees = top.getSubtrees();
				for (RegexpOperatorTree subTree : subTrees)
				{
					stack.push(subTree);
				}
				//tutaj zrób najwa¿niejsze
			}
		}
	}
}
