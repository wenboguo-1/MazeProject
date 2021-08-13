package message;

/**
 * Message of the ball
 */
public class BallMessage extends Message{
    private int x;
    private int y;

    /**
     *Construct a BallMessage object
     * @param x x coordinate of the ball
     * @param y y coordinate of the ball
     */
    public BallMessage(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * get X coordinate of ball
     * @return X coordinate of ball
     */
    public int getBallX(){return this.x;};

    /**
     * get Y coordinate of ball
     * @return Y coordinate of ball
     */
    public int getBallY(){return this.y;};
}
