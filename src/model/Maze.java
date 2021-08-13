package model;

/**
 * A class that contains the properties of the maze, such
 * as all walls and x , y coordinate;
 */
public class Maze {
    private int x; // x direction;
    private int y; // y direction;
    private boolean leftWall;
    private boolean rightWall;
    private boolean topWall;
    private boolean downWall;
    private boolean visited;
    private boolean isPath;

    /**
     * Generate a maze object with X and Y coordinates
     * @param x X  coordinates
     * @param y Y coordinates
     */
    public Maze(int x, int y){
        this.x = x;
        this.y = y;
        visited = true;
        leftWall = true;
        topWall = true;
        downWall = true;
        visited = false;
        isPath = false;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }
    public void setPath(boolean path) {
        isPath = path;
    }
    public void setLeftWall(boolean leftWall) { this.leftWall = leftWall; }
    public void setRightWall(boolean rightWall){
        this.rightWall = rightWall;
    }
    public void setTopWall(boolean topWall){
        this.topWall = topWall;
    }
    public void setDownWall(boolean downWall){
        this.downWall = downWall;
    }
    public boolean getLeftWall(){return leftWall;};
    public boolean getRightWall(){return rightWall;};
    public boolean getTopWall(){return topWall;};
    public boolean getDownWall(){return downWall;};
    public int getX(){return x;};
    public int getY(){return y;};
    public boolean getVisited(){return visited;};

}
