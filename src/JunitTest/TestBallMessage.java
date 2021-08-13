package JunitTest;
import static org.junit.Assert.*;

import message.BallMessage;
import org.junit.Test;

/**
 * Junit test for BallMessage class
 */
public class TestBallMessage {
     private BallMessage ballMessage = new BallMessage(2,3);

     @Test
    public void testCoordinate(){
         assertEquals(ballMessage.getBallX(),2);
         assertEquals(ballMessage.getBallY(),3);
     }

}
