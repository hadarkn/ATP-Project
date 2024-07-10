package algorithms.mazeGenerators;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Represents a Maze with a grid of cells, start position, and goal position.
 */
public class Maze {
    private int[][] maze;
    private int rows;
    private int cols;
    private Position startPosition;
    private Position goalPosition;

    /**
     * Constructs a Maze with the specified number of rows and columns.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     */
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
    }

    // Getters

    /**
     * Returns the 2D array representing the maze.
     *
     * @return The maze grid.
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * Returns the starting position of the maze.
     *
     * @return The starting position.
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * Returns the goal position of the maze.
     *
     * @return The goal position.
     */
    public Position getGoalPosition() {
        return goalPosition;
    }

    // Setters

    /**
     * Sets the value of a specific cell in the maze.
     *
     * @param row   The row index of the cell.
     * @param col   The column index of the cell.
     * @param value The value to set in the cell.
     */
    public void setCell(int row, int col, int value) {
        this.maze[row][col] = value;
    }

    /**
     * Returns the value of a specific cell in the maze.
     *
     * @param pos The position of the cell.
     * @return The value of the cell.
     */
    public int getCell(Position pos) {
        return maze[pos.getRowIndex()][pos.getColumnIndex()];
    }


    public int getRows(){
        return rows;
    }

    public int getCols() {
        return cols;
    }

    /**
     * Sets the starting position of the maze.
     *
     * @param startPosition The starting position to set.
     */
    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * Sets the goal position of the maze.
     *
     * @param goalPosition The goal position to set.
     */
    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    /**
     * Prints the maze to the console, marking the start position with 'S' and the goal position with 'E'.
     */
    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.print("S ");
                } else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()) {
                    System.out.print("E ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Checks if there is a path from the start position to the goal position using BFS.
     *
     * @return true if there is a path, false otherwise.
     */
    public boolean isSolvable() {
        boolean[][] visited = new boolean[rows][cols];
        Queue<Position> queue = new LinkedList<>();
        queue.add(startPosition);
        visited[startPosition.getRowIndex()][startPosition.getColumnIndex()] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int row = current.getRowIndex();
            int col = current.getColumnIndex();

            // Check if we reached the goal
            if (current.equals(goalPosition)) {
                return true;
            }

            // Explore neighbors
            Position[] neighbors = {
                    new Position(row - 1, col),
                    new Position(row + 1, col),
                    new Position(row, col - 1),
                    new Position(row, col + 1)
            };

            for (Position neighbor : neighbors) {
                int neighborRow = neighbor.getRowIndex();
                int neighborCol = neighbor.getColumnIndex();
                if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols &&
                        !visited[neighborRow][neighborCol] && maze[neighborRow][neighborCol] == 0) {
                    queue.add(neighbor);
                    visited[neighborRow][neighborCol] = true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if a given position is within the boundaries of the maze.
     *
     * @param position The position to check.
     * @return true if the position is within the maze boundaries, false otherwise.
     */
    public boolean inMaze(Position position) {
        if (position == null) {
            return false;
        }
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }

}
