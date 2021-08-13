package model;
import java.util.ArrayList;

/**
 * This class has all other class references in model package
 * and then will be used when they are in controller class
 */
public class Model {
    Ball ball;
    private ArrayList<Path> showPath;
    private ArrayList<Path> trackBallLine;
    Maze [][]maze;

    /**
     * construct a model object
     */
    public Model (){
        maze = new Maze[0][0];
        showPath = new ArrayList<>();
        trackBallLine = new ArrayList<>();
    }

    /**
     * get the ball object
     * @return the ball
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * modify the ball
     * @param border border of the ball
     */
    public void setBall(int border){
        ball = new Ball(border);
    }

    /**
     * initialize the maze
     */
    public void setMaze(int cell){
        maze = new Maze[cell][cell];
    }

    /**
     * get the maze
     * @return the maze
     */
    public Maze[][] getMaze(){
        return maze;
    }

    /**
     *  get show path
     * @return show path
     */
    public ArrayList<Path> getShowPath(){return showPath;};

    /**
     * get track ball line
     * @return track ball line
     */
    public ArrayList<Path> getTrackBallLine(){return trackBallLine;};

}
