package algorithms.search;

import java.util.*;
/**
 * DepthFirstSearch class extends ASearchingAlgorithm and implements the Depth First Search algorithm for solving ISearchable problems.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * Constructor for DepthFirstSearch.
     */
    public DepthFirstSearch() {super();}

    /**
     * Solves the given ISearchable problem using Depth First Search algorithm.
     *
     * @param searchable The ISearchable problem to solve.
     * @return A Solution object containing the solution path, or null if no solution exists.
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if (isInvalidSearchable(searchable)) return null;

        Stack<AState> solStack = new Stack<>();
        ArrayList<AState> route = new ArrayList<>();
        int cols = ((SearchableMaze) searchable).getMaze().getCols();
        int rows = ((SearchableMaze) searchable).getMaze().getRows();

        int[][] visitedMatrix = new int[rows][cols];

        solStack.push(searchable.getStartState());
        searchable.getStartState().setPrevState(null);

        return performDepthFirstSearch(searchable, solStack, route, visitedMatrix);
    }

    /**
     * Checks if the given ISearchable problem is invalid.
     *
     * @param searchable The ISearchable problem to check.
     * @return True if the searchable is null or has invalid dimensions, otherwise false.
     */
    private boolean isInvalidSearchable(ISearchable searchable) {
        if (searchable == null) return true;
        int cols = ((SearchableMaze) searchable).getMaze().getCols();
        int rows = ((SearchableMaze) searchable).getMaze().getRows();
        return cols < 1 || rows < 1;
    }

    /**
     * Performs the Depth First Search algorithm to find the solution path.
     *
     * @param searchable The ISearchable problem to solve.
     * @param solStack The stack used to perform the DFS.
     * @param route The list to store the solution path.
     * @param visitedMatrix The matrix to track visited states.
     * @return A Solution object containing the solution path, or null if no solution exists.
     */
    private Solution performDepthFirstSearch(ISearchable searchable, Stack<AState> solStack, ArrayList<AState> route, int[][] visitedMatrix) {
        AState current;
        while (!solStack.isEmpty()) {
            nodesEvaluatedCount++;
            current = solStack.pop();
            if (current.equals(searchable.getGoalState())) {
                return buildSolution(route, current);
            }
            processCurrentState(searchable, solStack, visitedMatrix, current);
        }
        return null;
    }

    /**
     * Processes the current state by marking it as visited and pushing unvisited neighbors to the stack.
     *
     * @param searchable The ISearchable problem to solve.
     * @param solStack The stack used to perform the DFS.
     * @param visitedMatrix The matrix to track visited states.
     * @param current The current state being processed.
     */
    private void processCurrentState(ISearchable searchable, Stack<AState> solStack, int[][] visitedMatrix, AState current) {
        int currRow = ((MazeState) current).getPosition().getRowIndex();
        int currCol = ((MazeState) current).getPosition().getColumnIndex();
        if (visitedMatrix[currRow][currCol] == 0) {
            visitedMatrix[currRow][currCol] = 1;
            ArrayList<AState> neighbours = searchable.getAllPossibleStates(current);
            for (AState neighbour : neighbours) {
                if (isUnvisited(visitedMatrix, neighbour)) {
                    neighbour.setPrevState(current);
                    solStack.push(neighbour);
                }
            }
        }
    }

    /**
     * Checks if a neighbor state is unvisited.
     *
     * @param visitedMatrix The matrix to track visited states.
     * @param neighbour The neighbor state to check.
     * @return True if the neighbor is unvisited, otherwise false.
     */
    private boolean isUnvisited(int[][] visitedMatrix, AState neighbour) {
        int row = ((MazeState) neighbour).getPosition().getRowIndex();
        int col = ((MazeState) neighbour).getPosition().getColumnIndex();
        return visitedMatrix[row][col] == 0;
    }

    /**
     * Builds the solution path by tracing back from the goal state to the start state.
     *
     * @param route The list to store the solution path.
     * @param current The current state being processed.
     * @return A Solution object containing the solution path.
     */
    private Solution buildSolution(ArrayList<AState> route, AState current) {
        while (current != null) {
            route.add(current);
            current = current.getPrevState();
        }
        Collections.reverse(route);
        return new Solution(route);
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
