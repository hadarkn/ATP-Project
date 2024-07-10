package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * SearchableMaze implements the ISearchable interface and provides functionality to retrieve
 * information about states within a maze. It includes methods to get the start state, goal state,
 * and all possible states from a given state in the maze.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState startState;
    private MazeState goalState;

    /**
     * Constructs a SearchableMaze object with the specified maze.
     *
     * @param maze The maze to be searched.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.startState = new MazeState(null, maze.getStartPosition(), 0);
        this.goalState = new MazeState(null, maze.getGoalPosition(), 0);
    }

    /**
     * Retrieves the maze associated with this searchable maze.
     *
     * @return The maze.
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Retrieves the goal state of the maze.
     *
     * @return The goal state.
     */
    @Override
    public AState getGoalState() {
        return this.goalState;
    }

    /**
     * Retrieves the start state of the maze.
     *
     * @return The start state.
     */
    @Override
    public AState getStartState() {
        return this.startState;
    }

    /**
     * Retrieves all possible states from a given state in the maze.
     *
     * @param state The current state.
     * @return An ArrayList of all possible states from the current state.
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        if (state == null) {
            return possibleStates;
        }
        Position currentPosition = (Position) state.getPosition();
        addStraightStates(state, possibleStates, currentPosition);
        addDiagonalStates(state, possibleStates, currentPosition);
        return possibleStates;
    }

    /**
     * Adds straight neighboring states (up, down, left, right) to the list of possible states.
     *
     * @param state            The current state.
     * @param possibleStates   The ArrayList to add possible states to.
     * @param currentPosition  The current position in the maze.
     */
    private void addStraightStates(AState state, ArrayList<AState> possibleStates, Position currentPosition) {
        addStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex()), 10);
        addStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex()), 10);
        addStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() - 1), 10);
        addStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() + 1), 10);
    }

    /**
     * Adds diagonal neighboring states (upper-right, upper-left, lower-right, lower-left) to the list of possible states.
     *
     * @param state            The current state.
     * @param possibleStates   The ArrayList to add possible states to.
     * @param currentPosition  The current position in the maze.
     */
    private void addDiagonalStates(AState state, ArrayList<AState> possibleStates, Position currentPosition) {
        addDiagonalStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() + 1), new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex()), new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() + 1), 15);
        addDiagonalStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() - 1), new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex()), new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() - 1), 15);
        addDiagonalStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() + 1), new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex()), new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() + 1), 15);
        addDiagonalStateIfValid(state, possibleStates, new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() - 1), new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex()), new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() - 1), 15);
    }

    /**
     * Adds a state to the list of possible states if the position is valid and accessible.
     *
     * @param state           The current state.
     * @param possibleStates  The ArrayList to add possible states to.
     * @param newPosition     The new position to check.
     * @param cost            The cost to move to the new position.
     */
    private void addStateIfValid(AState state, ArrayList<AState> possibleStates, Position newPosition, double cost) {
        if (maze.inMaze(newPosition) && maze.getCell(newPosition) == 0) {
            possibleStates.add(new MazeState(state, newPosition, state.getCost() + cost));
        }
    }

    /**
     * Adds a diagonal state to the list of possible states if the position is valid and accessible.
     *
     * @param state            The current state.
     * @param possibleStates   The ArrayList to add possible states to.
     * @param newPosition      The new position to check.
     * @param firstNeighbor    The first neighboring position for validation.
     * @param secondNeighbor   The second neighboring position for validation.
     * @param cost             The cost to move to the new position.
     */
    private void addDiagonalStateIfValid(AState state, ArrayList<AState> possibleStates, Position newPosition, Position firstNeighbor, Position secondNeighbor, double cost) {
        if (maze.inMaze(newPosition) && maze.getCell(newPosition) == 0 && (maze.getCell(firstNeighbor) == 0 || maze.getCell(secondNeighbor) == 0)) {
            possibleStates.add(new MazeState(state, newPosition, state.getCost() + cost));
        }
    }
}
