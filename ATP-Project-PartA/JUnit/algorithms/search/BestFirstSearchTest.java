package algorithms.search;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    @Test
    void testSolveNull() {
        ISearchingAlgorithm searcher = new BestFirstSearch();
        assertNull(searcher.solve(null));
    }

    @Test
   void testSolveWallsMaze() {
        Maze maze = new Maze(5, 5);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                maze.setCell(i, j,1);
        ISearchingAlgorithm searcher = new BestFirstSearch();
        assertNull(searcher.solve(new SearchableMaze(maze)));
    }



    @Test
    void testSolveZeroOnZeroMaze() {
        Maze maze = new EmptyMazeGenerator().generate(0, 0);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm searcher = new BestFirstSearch();
        assertNull(searcher.solve(searchableMaze));
    }
    @Test
    void testSolveZeroOnOneMaze() {
        Maze maze = new EmptyMazeGenerator().generate(0, 1);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm searcher = new BestFirstSearch();
        assertNull(searcher.solve(searchableMaze));
    }
/*
    @Test
    void testSolveOneOnOneMaze() {
        Maze maze = new EmptyMazeGenerator().generate(1, 1);
        ArrayList<AState> solutionPath = new ArrayList<>();
        solutionPath.add(new MazeState(new Position(0,0)));
        ISearchingAlgorithm searcher = new BestFirstSearch();
        assertEquals(searcher.solve(new SearchableMaze(maze)).getSolutionPath(), solutionPath);
    }

 */

}