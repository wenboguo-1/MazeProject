package View;
import message.Message;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;

/**
 * visual page after won the game
 */
public class WinPageView extends JFrame{
    private JButton exitButton;
    private JLabel showWinMassage;
    private JButton continueButton;
    private MainPageView mainPage;
    private JLabel showImg;
    private JPanel jPanel;
    private MainPageView mainPageView;
    private MazeView mazeView;
    private BlockingQueue<Message> queue;

    /**
     * View after won the game
     * @param isCheating To check if the user gets the help from computer
     * @param mainPageView reference of the mainPageView
     */

    public WinPageView (boolean isCheating,MainPageView mainPageView){

        this.mazeView = mazeView;
        this.mainPageView = mainPageView;
        jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        jPanel.setBounds(0,0,850,800);
        showImg = new JLabel();
        jPanel.setLayout(null);
        exitButton = new JButton("Exit");
        exitButton.setBounds(500,400,150,90);
        continueButton = new JButton("Continue");
        exitButton.setForeground(Color.GRAY);
        continueButton.setBounds(50,400,150,90);
        continueButton.setForeground(Color.CYAN);
        showImg.setIcon(new ImageIcon("/Users/wenboguo/Desktop/images.jpeg"));
        showImg.setBounds(20,100,50,50);
        showImg.setOpaque(true);
        showImg.setBackground(Color.BLACK);
        //showImg.setBorderPainted(false);

        if(isCheating){
            showWinMassage = new JLabel("Please Do Not Get Help From The Computer");
            showWinMassage.setFont(new Font("Serif",Font.BOLD,22));
            showWinMassage.setBounds(10,0,500,500);
            showImg.setBounds(550,30,300,300);
            jPanel.add(showImg);
        }else {
            showWinMassage = new JLabel("Congratulations, You Have Won the Game");
            showWinMassage.setFont(new Font("Serif",Font.BOLD,22));
            showWinMassage.setBounds(10,0,500,500);
            showImg.setBounds(550,30,300,300);
            jPanel.add(showImg);
        }
        showWinMassage.setForeground(Color.RED);
        jPanel.add(continueButton);
        jPanel.add(showWinMassage);
        jPanel.add(exitButton);
        this.add(jPanel);
        this.setLayout(null);
        this.setSize(850,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle( "Maze Game");

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        continueButton.addActionListener(e-> {
            this.setVisible(false);
            mainPageView.setVisible(true);
        });
    }
}