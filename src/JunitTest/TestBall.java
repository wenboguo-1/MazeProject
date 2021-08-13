package JunitTest;
import static org.junit.Assert.*;
import model.Ball;
import model.Maze;
import org.junit.Test;
/**
 * Junit test for Ball class
 */
public class TestBall {
    private Ball ball = new Ball();

    @Test
    public void testCoordinate(){
         ball.setBallDirection(2,3);
         assertEquals(ball.getX(),2);
         assertEquals(ball.getY(),3);
    }
    @Test
    public void testMeetWall(){
        Maze maze = new Maze(0,0);
        Maze maze1 = new Maze(1,0);
        maze1.setLeftWall(false);
        maze.setRightWall(false);
        assertTrue(ball.meetWall(maze,maze1) == false);
        maze1.setLeftWall(true);
        assertTrue(ball.meetWall(maze,maze1) == true);
    }
}
