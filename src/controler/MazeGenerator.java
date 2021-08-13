package controler;
import model.Ball;
import model.Maze;
import model.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import model.Model;

/**
 * This class is to generate a maze
 */
public class MazeGenerator {
    private int vertices;
    private Ball ball;
    private ArrayList<Path> showPath;
    private Maze [][] maze;
    private ArrayList<Path> trackBallLine;
    private int [] direction = {1,0,-1,0,1};

    /**
     * Generate a maze
     * @param vertices Number of blocks, and each block has its left, right, top and down wall
     * @param model Get the reference of the model to have all references of all others model class and
     *              then update their proprieties later
     */
     public MazeGenerator (int vertices,Model model){

         model.setBall(vertices);
         trackBallLine = model.getTrackBallLine();
         showPath = model.getShowPath();
         ball = model.getBall();
         model.setMaze(vertices);
         maze = model.getMaze();
         this.vertices = vertices;
         this.setMaze();
         this.createMaze();
     }

    /**
     * get track ball line
     * @return track ball line
     */
     public ArrayList<Path> getTrackBallLine(){
         return trackBallLine;
     }

    /**
     * get the ball object
     * @return the ball
     */
    public Ball getBall(){
         return ball;
     }

     /**
      * To check which direction of the current location
      * has a path
      *
      * @Param x  x coordinate
      * @Param y  y coordinate
     */
    private ArrayList<Integer> checkRoad(int x, int y) {
        int [] tempDirection = direction;
        ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < tempDirection.length - 1; i++) {
            if (!outOfBorder(x + tempDirection[i], y + tempDirection[i + 1])) {
                if (!maze[x + tempDirection[i]][y + tempDirection[i + 1]].getVisited()) {
                    temp.add(x + tempDirection[i]);
                    temp.add(y + tempDirection[i + 1]);
                }
            }
        }
        return temp;
    }

    /**
     * get the maze object
     * @return maze
     */
    public Maze[][] getMaze(){
       return maze;
    }

    /**
     *  Initialize the maze
     */
    public void setMaze() {
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                Maze m = new Maze(i, j);
                maze[i][j] = m;
            }
        }
    }



    /**
     * Check the border of the map
     * @param x X coordinate
     * @param y Y coordinate
     * @return ture map if  out Of Border else false
     */
    public boolean outOfBorder(int x, int y) {
        if (x < 0 || x > this.vertices - 1 || y < 0 || y > this.vertices - 1)
            return true;
        return false;
    }


    /**
     * This method is to beak the wall of the current coordinate and the next
     * @param currentLocation To indicate where the current path is at
     * @param nextLocation To indicate which location it goes next
     */
    private void removeWall(Maze currentLocation,Maze nextLocation) { //
        if (currentLocation.getY() > nextLocation.getY()) {
            currentLocation.setTopWall(false);// break the wall
            nextLocation.setDownWall(false);
        }
        if (currentLocation.getY() < nextLocation.getY()) {
            currentLocation.setDownWall(false);
            nextLocation.setTopWall(false);
        }
        if (currentLocation.getX() > nextLocation.getX()) {
            currentLocation.setLeftWall(false);
            nextLocation.setRightWall(false);
        }
        if (currentLocation.getX() < nextLocation.getX()) {
            currentLocation.setRightWall(false);
            nextLocation.setLeftWall(false);
        }
    }

    /**
     * break some walls randomly
     */
    private void breakWallRandomly(){
        Random random = new Random();
        for( int i = 1; i <= 3; i ++){
            for(int j = 1; j<=3; j++){
                int tempNum = Math.abs(random.nextInt()) % 4;
                if(tempNum == 1)
                    maze[i][j].setLeftWall(false);
                if(tempNum==2)
                    maze[i][j].setRightWall(false);
                if(tempNum == 3)
                    maze[i][j].setTopWall(false);
                if(tempNum == 3)
                    maze[i][j].setDownWall(false);

            }
        }
    }

    /**
     *  None recursive DPS to randomly create a maze
     */
    public void createMaze() {
        this.setMaze();
        Random random = new Random();
        int i = 0;
        int j = 0;
        Stack<Maze> stack = new Stack<>();
        stack.push(maze[0][0]);
        maze[0][0].setVisited(true);// set current vertex as a visited

        while (!stack.empty()) { // start creating path
            Maze current = stack.peek();
            // check if there is a path in the top, bottom, left or right side of the current location
            ArrayList<Integer> temp = this.checkRoad(current.getX(), current.getY());
            if (temp.size() == 0) {// No path was found
                stack.pop();
                continue;
            }
            int tempIndex = Math.abs(random.nextInt()) % (temp.size()); // randomly find a path
            if (tempIndex % 2 == 0) {
                i = temp.get(tempIndex);
                j = temp.get(tempIndex + 1);
            } else {
                i = temp.get(tempIndex - 1);
                j = temp.get(tempIndex);
            }
            stack.push(maze[i][j]);
            this.removeWall(current, stack.peek());
            maze[i][j].setVisited(true); // mark current location as visited
        }
        breakWallRandomly();
        searchPath();
        setTrackBallLine();
    }

    /**
     * get all path of the ball line
     */
    private void setTrackBallLine(){
        for(int i = showPath.size()- 1; i >=0; i--){
            trackBallLine.add(showPath.get(i));
        }
    }

    /**
     * using DFS to search a path that will get out of the maze
     */
    private void searchPath() {
        Stack<Maze> stack = new Stack<>();
        stack.push(maze[0][0]);
        while (!stack.isEmpty()) {
            Maze current = stack.peek();
            current.setVisited(false);
            if (current.getY() == vertices - 1 && current.getX() == vertices - 1) { // Already found the end of the maze
                while (!stack.isEmpty()) {// Only paths to the end of the maze are in the stack
                    Maze temp = stack.peek();
                    showPath.add(new Path(temp.getX(), temp.getY()));//record the path with arrayList
                    stack.pop().setPath(true);
                }
                return;
            }
            checkPath(current, stack);
        }
    }


    /**
     * Check the path
      @param m A object of the current object that represents the current location of the ball
      @param stack If we found  no path around the ball, then pop the stack
   */
    private void checkPath(Maze m, Stack<Maze> stack){
        int x = m.getX();
        int y = m.getY();
        if(!outOfBorder(x+1,m.getY())){
            if(!m.getRightWall()&&!maze[x+1][y].getLeftWall() &&maze[x+1][y].getVisited()){
                stack.add(maze[x+1][y]);
                return;
            }
        }
        if(!outOfBorder(x-1,m.getY())){
            if(!m.getLeftWall()&&!maze[x-1][y].getRightWall()&&maze[x -1][y].getVisited()){
                stack.add(maze[x-1][y]);
                return;
            }
        }
        if(!outOfBorder(m.getX(),y - 1)){
            if(!m.getTopWall()&&!maze[x][y-1].getDownWall()&&maze[x][y-1].getVisited()){
                stack.add(maze[x][y-1]);
                return;
            }
        }
        if(!outOfBorder(m.getX(),y + 1)){
            if(!m.getDownWall()&&!maze[x][y+1].getTopWall()&&maze[x][y+1].getVisited()){
                stack.add(maze[x][y+1]);
                return;
            }
        }
        stack.pop();
    }

}
