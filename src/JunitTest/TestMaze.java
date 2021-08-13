package JunitTest;
import static org.junit.Assert.*;

import model.Maze;
import org.junit.Test;

/**
 * Junit test for Maze class
 */
public class TestMaze {
    private Maze maze = new Maze(1,2);

    @Test
    public void testWall(){
        maze.setDownWall(false);
        maze.setLeftWall(false);
        maze.setRightWall(true);
        maze.setTopWall(true);
        assertTrue(maze.getDownWall() == false);
        assertTrue(maze.getLeftWall() == false);
        assertTrue(maze.getRightWall() == true);
        assertTrue(maze.getTopWall() == true);
    }

    @Test
    public void testCoordinate(){
       assertEquals(maze.getX(),1);
       assertEquals(maze.getY(),2);
    }
}
