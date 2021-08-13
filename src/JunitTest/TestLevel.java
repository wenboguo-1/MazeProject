package JunitTest;
import static org.junit.Assert.*;
import message.Level;
import org.junit.Test;


/**
 * Junit test for Level class
 */
public class TestLevel {
    private Level level = new Level(2);
    @Test
    public void testLevel(){
        assertEquals(level.getCurrentLevel(),30);
        level = new Level(3);
        assertEquals(level.getCurrentLevel(),60);
    }
}
