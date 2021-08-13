package model;

/**
 * a ball object that the user can  control
 */
public class Ball {

    private int x;
    private int y;
    private int border;

    /**
     * construct a ball object
     */
    public Ball (){
        x = 0;
        y = 0;
        border = 0;
    }

    /**
     * construct a ball object with border
     * @param border the border
     */
    public Ball(int border){
        this.border = border;
        this.x = 0;
        this.y = 0;
    }
    public int getX(){return x;};
    public int getY(){return y;};
    public void setBallDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *Check if any x or y is out of the maze border
     * @param x next position of the x
     * @param y next position of the y
     * @return if any x or y is out of the maze border, then return false;
     */
    public boolean isOutBorder(int x, int y){
        return (x < 0 || x > this.border - 1 || y < 0 || y > border - 1) ? true :false;
    }

    /**
     * Check if the ball object meet a wall
     * @param currentMaze To show that position of the current object is not changing
     * @param nextMaze To show that position of the current object is  changing
     * @return true if current object meet a wall, return false;
     */
    public boolean meetWall(Maze currentMaze, Maze nextMaze){

           if (currentMaze.getY() > nextMaze.getY()) {
                if (!nextMaze.getDownWall() && !currentMaze.getTopWall()) {
                    return false;
                }
            }
            if(currentMaze.getY() < nextMaze.getY()) {
                if (!nextMaze.getTopWall() && !currentMaze.getDownWall()) {
                    return false;
                }
            }
            if (currentMaze.getX() < nextMaze.getX()) {
                if (!nextMaze.getLeftWall() && !currentMaze.getRightWall()) {
                    return false;
                }
            }
            if (currentMaze.getX() > nextMaze.getX()) {
                if (!nextMaze.getRightWall() && !currentMaze.getLeftWall()) {
                    return false;
                }
            }

            return true;
        }
    }

