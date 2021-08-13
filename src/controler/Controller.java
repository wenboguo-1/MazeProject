package controler;
import View.MainPageView;
import View.MazeView;
import message.Level;
import message.Message;
import model.Ball;
import model.Model;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import message.BallMessage;

/**
 * Control the whole game, and get the construction from the queue when
 * the new action happened
 */
public class Controller {
    private BlockingQueue<Message> queue;
    private Model model;
    private MainPageView mainPageView;
    private controler.MazeGenerator mazeGenerator;
    private Ball ball;
    private MazeView mazeView;
    private List<Value> valves = new LinkedList<>();

    /**
     * construct a controller object
     * @param model the model object
     * @param mainPageView For user to go back to the mainpage
     * @param queue  Store the current action when button is clicked and then will be used in controller at later time;
     */
    public Controller(Model model, MainPageView mainPageView, BlockingQueue<Message> queue) {
           this.model = model;
           this.mainPageView = mainPageView;
           this.queue = queue;
           this.valves.add(new LevelValue());
           this.valves.add(new BallValue());
           ball = new Ball();
    }

    /**
     * main loop of the queue
     * @throws Exception
     */
    public void mainLoop() throws Exception {
        controler.ValveResponse response = controler.ValveResponse.EXECUTED;
        Message message = null;

           while (response != controler.ValveResponse.FINISH) {
                try {
                    message = (Message) queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Value valve : valves) {
                    response = valve.execute(message);
                    if (response != controler.ValveResponse.MISS)
                        break;
                }
            }


        }

    /**
     * The level value
     */
    private class LevelValue implements Value {
            @Override
            public controler.ValveResponse execute(Message message) {
                  if(message.getClass() != Level.class){
                      return controler.ValveResponse.MISS;
                  }
                  model = new Model();
                  Level level = (Level) message;
                  mazeGenerator = new controler.MazeGenerator(level.getCurrentLevel(),model);
                  mazeView = new MazeView(level.getCurrentLevel(),level,mazeGenerator,mainPageView,queue);
                  return controler.ValveResponse.EXECUTED;
            }
    }

    /**
     * the ball value
     */
    private class BallValue implements Value{
        @Override
        public controler.ValveResponse execute(Message message) {
              if(message.getClass() != BallMessage.class){
                  return controler.ValveResponse.MISS;
              }
              Ball ball = model.getBall();
              BallMessage ballMessage = (BallMessage) message;
              int currentX = ball.getX(); //Get the current x coordinate of the ball
              int currentY = ball.getY();// get the current Y coordinate of the ball
              int nextX = ballMessage.getBallX();
              int nextY = ballMessage.getBallY();
              if(!ball.isOutBorder(nextX,nextY)){ // check if the ball's position is out of the border
                  if(!ball.meetWall(model.getMaze()[currentX][currentY],model.getMaze()[nextX][nextY])){
                      ball.setBallDirection(nextX,nextY);// if ball did not meet the wall, update the new position
                      mazeView.updateBall(nextX,nextY);// update the maze view
                  }else
                      mazeView.updateBall(currentX,currentY);// otherwise,don't change the current position
              }else
                  mazeView.updateBall(currentX,currentY);

              return controler.ValveResponse.EXECUTED;
        }
    }


}
