
package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class implements the Best First Search algorithm, which is an informed search algorithm that evaluates nodes
 * based on their estimated distance to the goal state. It extends the BreadthFirstSearch class and overrides its
 * getDataStructure method to return a PriorityQueue instead of a LinkedList, which is used to order the nodes based on their
 * heuristic values. The algorithm iteratively selects the node with the lowest heuristic value and expands its children,
 * updating their heuristic values and adding them to the priority queue. This process continues until the goal state is found
 * or the priority queue is empty, indicating that there is no solution.
 */
public class BestFirstSearch extends BreadthFirstSearch {
    @Override
    public Queue<AState> getDataStructure() {
        return new PriorityQueue<>(new costComparator());
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}

class costComparator implements Comparator<AState> {
    @Override
    public int compare(AState o1, AState o2) {
        return Double.compare(o1.getCost(), o2.getCost());
    }
}