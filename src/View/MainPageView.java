package View;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import message.Message;
import message.Level;

/**
 * This class is to show the main page which it has severl levels for users to choose
 */
public class MainPageView extends JFrame {
    private JButton levelHigh;
    private JButton levelLow;
    private JButton levelHell;
    private JButton levelMedium;
    private ImageIcon imgIcon;
    private JLabel mazeLabel;
    private JLabel chooseLabel;
    private JLabel backGround;
    private BlockingQueue<Message> queue;
    private boolean isNewGame = false;
    private int level;

    /**
     * View of the MainPage
     * @param queue Message queue
     */
    public MainPageView(BlockingQueue<Message> queue){
        imgIcon = new ImageIcon(this.getClass().getResource("/maze.jpg"));
        mazeLabel = new JLabel("Maze    Game");
        mazeLabel.setFont(new Font("Serif",Font.BOLD,50));
        mazeLabel.setForeground(Color.orange);
        mazeLabel.setBounds(300,100,500,100);
        chooseLabel = new JLabel("Choose Level ->");
        chooseLabel.setFont(new Font("Serif",Font.BOLD,40));
        chooseLabel.setForeground(Color.CYAN);
        chooseLabel.setBounds(100,300,500,100);

        Image img = imgIcon.getImage();
        Image temImg = img.getScaledInstance(900,950,Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(temImg);
        backGround = new JLabel("",imgIcon,JLabel.CENTER);
        backGround.add(chooseLabel);
        backGround.setBounds(0,0,900,950);
        levelHell = new JButton("Hell");
        levelHigh = new JButton("Hard");
        levelLow = new JButton("Easy");
        levelMedium = new JButton("Medium");
        levelHell.setBounds(600,300,200,100);
        levelHigh.setBounds(600,450,200,100);
        levelLow.setBounds(600,750,200,100);
        levelMedium.setBounds(600,600,200,100);
        levelLow.setForeground(Color.GREEN);
        levelHell.setForeground(Color.RED);
        levelHigh.setForeground(Color.YELLOW);
        levelMedium.setForeground(Color.ORANGE);
        setButtonColor(levelHell,Color.darkGray);
        setButtonColor(levelHigh,Color.darkGray);
        setButtonColor(levelLow,Color.darkGray);
        setButtonColor(levelMedium,Color.darkGray);

        this.setTitle("Maze created by Wenbo Guo");
        this.add(backGround);
        this.setSize(900,950);
        this.setLayout(null);
        backGround.add(levelHigh);
        backGround.add(levelMedium);
        backGround.add(levelLow);
        backGround.add(levelHell);
        backGround.add(mazeLabel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // use lambda expression to activate the button key
        levelLow.addActionListener(e -> {
              this.setVisible(false);
              this.level = 1;
              queue.add(new Level(1));
          }
        );
        levelHigh.addActionListener(e ->{
            this.setVisible(false);
            this.level = 3;
            queue.add(new Level(3));

        });
        levelHell.addActionListener(e ->{
            this.setVisible(false);
            this.level = 4;
            queue.add(new Level(4));
        });
        levelMedium.addActionListener(e->{
             this.setVisible(false);
             this.level = 2;
             queue.add(new Level(2));

        });
    }

    /**
     * Get the level of difficulties
     * @return level of difficulties
     */
    public int getLevel(){return this.level;};

    /**
     * set button color
     * @param button button want to set
     * @param color color being set
     */
    private void setButtonColor(JButton button,Color color ){
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    public static MainPageView init(BlockingQueue<Message> queue) {
        return new MainPageView(queue);
    }
}
