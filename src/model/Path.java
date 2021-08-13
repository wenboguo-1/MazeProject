package model;

/**
 * The path of the ball move
 */
public class Path {
    private int pathX;
    private int pathY;
    private int moveX;
    private int moveY;
    private int moveNextX;
    private int moveNextY;
    private int counter;

    /**
     * Construct a path object
     */
    public Path(){
        pathX = 0;
        pathY = 0;
        moveY = 0;
        moveX = 0;
        moveNextX = 0;
        moveNextY = 0;
        counter = 0;
    }

    /**
     * Construct a path object with x and y coordinate
     */
    public Path(int x, int y){
        pathX = x;
        pathY = y;
        moveY = 0;
        moveX = 0;
        moveNextX = 0;
        moveNextY = 0;
        counter = 0;
    }
    public void setCounter(){
        counter++;
    }
    public void setPathX(int pathX) {
        this.pathX = pathX;
    }
    public void setPathY(int pathY) {
        this.pathY = pathY;
    }
    public void resetCounter(){
        counter = 0;
   }
    public int getPathY() {
        return pathY;
    }
    public int getPathX() {
        return pathX;
    }
    public int getCounter(){return counter;};
}
