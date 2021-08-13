package View;
import controler.MazeGenerator;
import message.BallMessage;
import message.Message;
import model.Maze;
import model.Model;
import model.Path;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import message.Level;

/**
 * The visual view of the Maze
 */
public class MazeView extends JPanel implements KeyListener {

    private final int WIDTH = 960;
    private JFrame frame;
    private ArrayList<Path> trackBallLine;
    private Maze[][] maze;
    private Path path;
    private PathAnimation pathAnimation;
    private int vertices;
    private boolean isWin;
    private boolean hint;
    private boolean hint2;
    private MazeGenerator mazeGenerator;
    private Level level;
    BlockingQueue<Message> queue;
    private MainPageView mainPageView;
    private int moveX;
    private int moveY;

    /**
     *The view of the maze
     * @param border Total number of positions in the maze
     * @param level Reference of the Level class in which it will be used to update the current level of the game
     * @param mazeGenerator Reference of the MazeGenerator in which it will be used to send the path and positon to the view class
     * @param mainPageView For user to go back to the mainpage
     * @param queue Store the current action when button is clicked and then will be used in controller at later time;
     */
    public MazeView(int border,Level level, MazeGenerator mazeGenerator,MainPageView mainPageView, BlockingQueue<Message> queue) {

        moveX = 0;
        moveY = 0;
        this.mainPageView = mainPageView;
        this.mazeGenerator = mazeGenerator;
        maze = mazeGenerator.getMaze();
        trackBallLine = mazeGenerator.getTrackBallLine();
        this.level = level;
        path = new Path();
        frame = new JFrame();
        this.vertices = border;
        frame.setLayout(null);
        pathAnimation = new PathAnimation(this);
        super.setBackground(Color.gray);
        this.setLayout(null);
        this.setSize(960, 960);
        frame.addKeyListener(this);
        frame.setTitle("Computer Does Maze(Press Space key) -- Show The Path(Press Enter Key) -- Change Map(Press 1)");
        frame.add(this);
        frame.setSize(960, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hint = false;
        hint2 = false;
        isWin = false;
        this.queue = queue;
    }

    /**
     * Get the Path
     * @return the path
     */
    public Path getPath(){return path;};

    /**
     * get track ball line
     * @return track ball line
     */
    public ArrayList<Path> getTrackBallLine(){return trackBallLine;};

    /**
     * draw the maze
     * @param g
     */
    private void drawMaze(Graphics g) { // Draw the Maze
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                int size = WIDTH / (vertices);
                int x = i * size;
                int y = j * size;
                if (maze[i][j].getLeftWall()) {
                    g.drawLine(x, y, x, y + size);
                }
                if (maze[i][j].getRightWall()) {
                    g.drawLine(x + size, y, x + size, y + size);
                }
                if (maze[i][j].getTopWall()) {
                    g.drawLine(x, y, x + size, y);
                }
                if (maze[i][j].getDownWall()) {
                    g.drawLine(x, y + size, x + size, y + size);
                }
            }
        }
        int temp = WIDTH / vertices * vertices;
        g.drawLine(temp, 0, temp, temp - (WIDTH / vertices));
    }

    /**
     * draw the path
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        addBall(g);
        g.setColor(Color.BLACK);
        drawMaze(g);
        if (hint) {
            int size = WIDTH / vertices;
            int x = path.getPathX() * (WIDTH / vertices);
            int y = path.getPathY() * (WIDTH / vertices);
            g.setColor(Color.RED);
            g.fillOval(x, y, WIDTH / vertices - 3, WIDTH / vertices);
            for (int i = 1; i < path.getCounter() - 1; i++) {
                int k = trackBallLine.get(i).getPathX() * size + size / 2;
                int s = trackBallLine.get(i).getPathY() * size + size / 2;
                int nextK = trackBallLine.get(i - 1).getPathX() * size + size / 2;
                int nextS = trackBallLine.get(i - 1).getPathY() * size + size / 2;
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(WIDTH / (vertices + 10)));
                g.setColor(Color.cyan);
                g.drawLine(k, s, nextK, nextS);
            }
        }
        if (isWin) {
            if(hint) {
                frame.setVisible(false);
                new WinPageView(true,mainPageView); // if user get the help from the computer, set true
            }else {
                 frame.setVisible(false);
                 new WinPageView(false,mainPageView); // if user get the help from the computer, set true
            }

        }
        if (hint2) {
            showPath(g);
        }

    }

    /**
     * When the space key is pressed, then the algorithm will automatically
     * generate a path that will lead users to get out of the maze
     * @param g
     */
    private void showPath(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.setColor(Color.ORANGE);
        int size = WIDTH / vertices;
        for (int i = trackBallLine.size() - 1; i > 0; i--) {
            int x = trackBallLine.get(i).getPathX() * size + size / 2;
            int y = trackBallLine.get(i).getPathY() * size + size / 2;
            int nextX = trackBallLine.get(i - 1).getPathX() * size + size / 2;
            int nextY = trackBallLine.get(i - 1).getPathY() * size + size / 2;
            g.drawLine(x, y, nextX, nextY);
        }

    }

    /**
     * show the hint
     * @return hint
     */
    public boolean getHint(){return hint;};

    /**
     * set condition to win
     * @param win
     */
    public void setWin(boolean win) {
        isWin = win;
    }

    /**
     * control the key board
     * @param e key action
     */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                this.moveY += 1;
                queue.add(new BallMessage(this.moveX,this.moveY));
                repaint();
                break;
            case KeyEvent.VK_UP:
                this.moveY -= 1;
                this.queue.add(new BallMessage(this.moveX,this.moveY));
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                this.moveX -= 1;
                this.queue.add(new BallMessage(this.moveX,this.moveY));
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                if (this.moveX + 1 == vertices && this.moveY + 1 == vertices) {//User got of the maze
                    isWin = true;
                    repaint();
                    return;
                }
                this.moveX += 1;
                this.queue.add(new BallMessage(this.moveX,this.moveY));
                repaint();
                break;

            case KeyEvent.VK_SPACE:
                if (hint) {
                    pathAnimation.interrupt();
                    hint = false;
                } else {
                    hint = true;
                    pathAnimation.start();
                }
                break;
            case KeyEvent.VK_ENTER://show the path to the end of the maze
                if (hint2) {
                    hint2 = false;
                    repaint();
                    break;
                }
                hint2 = true;
                repaint();
                break;

            case KeyEvent.VK_ESCAPE://exit the maze
                frame.setVisible(false);
                mainPageView.setVisible(true);
                break;
            case KeyEvent.VK_1:// change maze
                if(hint){
                   pathAnimation.interrupt();
                    hint = false;
                    JOptionPane.showMessageDialog(null, "Error, can't change the map while computer is processing the maze", "Maze Game",
                            JOptionPane.PLAIN_MESSAGE);
                    break;
                }
                queue.add(new Level(mainPageView.getLevel()));
                this.repaint();
                break;
        }
    }


    /**
     * Add ball object to the maze
     * @param g
     */
    private void addBall(Graphics g) {
        int x = WIDTH / (vertices) * (vertices - 1);
        int y = WIDTH / (vertices) * (vertices - 1);
        int moveX = this.moveX * (WIDTH/vertices);
        int moveY = this.moveY *(WIDTH/vertices);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g.setColor(Color.BLUE);
        g.fillOval(moveX, moveY, WIDTH / vertices - 3, WIDTH / vertices - 3);
        g.fillOval(x, y, WIDTH / vertices - 3, WIDTH / vertices - 3);
    }

    /**
     * update ball object in the maze
     * @param x x coordinate want to update
     * @param y y coordinate want to update
     */
    public void updateBall(int x, int y){
         this.moveX  = x;
         this.moveY = y;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
