package algorithms.search;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BFS algorithm work with LinkedHashSet and Queue. Algorithm for searching a tree data structure for a node that
 * satisfies a given property. It starts at the tree root and explores all nodes at the present depth prior to moving
 * on to the nodes at the next depth level.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s == null || ((SearchableMaze) s).getMaze().getRows() < 1 || ((SearchableMaze) s).getMaze().getCols() < 1)
            return null;
        LinkedHashSet<String> visited = new LinkedHashSet<>();
        Queue<AState> states = getDataStructure();
        states.add(s.getStartState());
        visited.add(s.getStartState().toString());
        while (!states.isEmpty()) {
            AState current = states.remove();
            if (current.equals(s.getGoalState())) {
                constructSolutionPath(current, solution);
                return solution;
            }
            List<AState> neighbors = s.getAllPossibleStates(current);
            nodesEvaluatedCount++;
            for (AState neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    neighbor.setPrevState(current);
                    states.add(neighbor);
                    visited.add(neighbor.toString());
                }
            }
        }

        return null;
    }

    public Queue<AState> getDataStructure() {
        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}