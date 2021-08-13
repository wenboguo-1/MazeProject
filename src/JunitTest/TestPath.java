package JunitTest;
import static org.junit.Assert.*;
import model.Path;
import org.junit.Test;

/**
 * Junit test for Path class
 */
public class TestPath {
    private Path path = new Path();

    @Test
    public void testX(){
        path.setPathX(2);
        assertEquals(path.getPathX(),2);
        path.setPathX(3);
        assertEquals(path.getPathX(),3);
    }
    @Test
    public void testY(){
        path.setPathY(2);
        assertEquals(path.getPathY(),2);
        path.setPathY(3);
        assertEquals(path.getPathY(),3);
    }
    @Test
    public void TestCounter(){
        path.setCounter();
        assertEquals(path.getCounter(),1);
        path.setCounter();
        assertEquals(path.getCounter(),2);
        path.resetCounter();
        assertEquals(path.getCounter(),0);
    }
}
