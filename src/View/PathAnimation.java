package View;

/**
 * The view of Path
 */
public class PathAnimation extends Thread {

    private MazeView mazeView;

    /**
     * Construct a  PathAnimation object
     * @param mazeView the Maze View
     */
    public PathAnimation(MazeView mazeView){

        this.mazeView = mazeView;
    }

    /**
     * keep check of the path
     */
    @Override
    public void run() {
        super.run();
        while (!this.interrupted()) {
            for (int i = 0; i < mazeView.getTrackBallLine().size() - 1; i++) {
                mazeView.getPath().setPathX(mazeView.getTrackBallLine().get(i).getPathX());
                mazeView.getPath().setPathY(mazeView.getTrackBallLine().get(i).getPathY());
                mazeView.getPath().setCounter();
                try {
                    Thread.sleep(30);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    mazeView.getPath().setPathY(0);
                    mazeView.getPath().setPathX(0);
                    mazeView.getPath().resetCounter();
                    break;
                }
                mazeView.repaint();
            }
            if ( mazeView.getPath().getCounter() ==  mazeView.getTrackBallLine().size() - 1 && mazeView.getHint()) {
                mazeView.setWin(true);
                mazeView.repaint();
                return;
            }
            mazeView.getPath().resetCounter();
        }

    }
}