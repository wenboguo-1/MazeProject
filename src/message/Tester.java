package message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import View.MainPageView;
import model.Model;
import controler.Controller;

/**
 * Tester to run the game
 */
public class Tester {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
    private static MainPageView view;
    private static Model model;

    public static void main(String[] args) {
        view = MainPageView.init(queue);
        model = new Model();

        Controller game = new Controller(model, view, queue);
        try {
            game.mainLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.dispose();
        queue.clear();
    }
}
