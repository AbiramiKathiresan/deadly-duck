/*
Abirami Kathiresan
5/25/20
DeadlyDuck.java
*/

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//Purpose of DeadlyDuck class is to create the main menu with the JButtons for the game
public class DeadlyDuck {
    
    private JFrame frame;
    private JPanel wrapper;
    private JPanel gameWrapper;
    private String FONT_NAME;
    private JPanel howToPlay;

    //Main method to create an instance of this class
    public static void main(String[] args) {
        new DeadlyDuck().run();
    }

    //Purpose of run() is to create all the essential components for the start panel and to attach it to the game itself
    public void run() {
        FONT_NAME = "Franklin Gothic Heavy";
        frame = new JFrame("Deadly Duck");
       // Panel pan = new Panel();   
        //frame.setContentPane(pan);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        
        wrapper = new JPanel(new CardLayout());
        
        InstructionPanel howToPlay = new InstructionPanel(wrapper);
        howToPlay.setBackground(Color.WHITE);
        
        Leaderboard leader = new Leaderboard(wrapper);
        leader.setBackground(Color.white);
        leader.read();
        
        GamePanel gp = new GamePanel(wrapper, leader);
        
        JPanel gamePanel = new JPanel(new GridLayout(1, 1));
        gamePanel.setBackground(Color.BLUE);
        
        Options choose = new Options(wrapper, gp);
        choose.setBackground(Color.white);
        
        JLabel name = new JLabel("Deadly Duck");
        name.setForeground(Color.yellow);
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setVerticalAlignment(JLabel.CENTER);
        name.setVerticalTextPosition(SwingConstants.CENTER);
        name.setHorizontalTextPosition(SwingConstants.CENTER); 

        ImageIcon image = new ImageIcon("sprites/duckImage.jpg");

        JButton button = new JButton("Play Game");
        // button.setFocusable(false);
        button.setBackground(Color.YELLOW);

        JButton instructions = new JButton("How to Play");
        instructions.setBackground(Color.YELLOW);

        JButton leaderboard = new JButton("Leaderboard");
        leaderboard.setBackground(Color.yellow);

        JButton quit = new JButton("Quit");
        quit.setBackground(Color.yellow);
        
        JButton options = new JButton("Options");
        options.setBackground(Color.yellow);

        ClickPanel clickPanel = new ClickPanel(name, options, quit, instructions, leaderboard, button);
        clickPanel.setBackground(Color.WHITE);

        Font buttonFont = getFancyFont();
        button.setFont(buttonFont);
        quit.setFont(buttonFont);
        leaderboard.setFont(buttonFont);
        instructions.setFont(buttonFont);
        Font title = new Font("Franklin Gothic Heavy", Font.BOLD, 34);
        name.setFont(title);
        options.setFont(buttonFont);

        Border myBorder = BorderFactory.createRaisedBevelBorder();
        button.setBorder(myBorder);
        instructions.setBorder(myBorder);
        leaderboard.setBorder(myBorder);
        quit.setBorder(myBorder);
        options.setBorder(myBorder);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CardLayout temp = (CardLayout) wrapper.getLayout();
                gp.start();
                temp.show(wrapper, "Game");
                frame.revalidate();
                frame.repaint();
            }
        });


        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CardLayout temp = (CardLayout) wrapper.getLayout();
                temp.show(wrapper, "Inst");
                wrapper.repaint();
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CardLayout temp = (CardLayout) wrapper.getLayout();
                temp.show(wrapper, "Lead");
                wrapper.repaint();
            }
        });

        quit.addActionListener(new ActionListener() {;
            public void actionPerformed(ActionEvent evt){
                System.exit(0);
            }
        });

        options.addActionListener(new ActionListener() {;
            public void actionPerformed(ActionEvent evt){
                CardLayout temp = (CardLayout) wrapper.getLayout();
                temp.show(wrapper, "Choose");
                wrapper.repaint();
            }
        });
        
        wrapper.add(clickPanel, "Click");
        wrapper.add(gp, "Game");
        wrapper.add(howToPlay, "Inst");
        wrapper.add(leader, "Lead");
        wrapper.add(choose, "Choose");
        
        name.setBounds(637, 15, 250, 50);
        button.setBounds(640, 80, 250, 50);
        options.setBounds(640, 240, 250, 50);
        instructions.setBounds(640, 160, 250, 50);
        leaderboard.setBounds(640, 320, 250, 50);
        quit.setBounds(640, 400, 250, 50);
        gp.setMinimumSize(new Dimension(960, 540));
        
        // Leaderboard.write(GamePanel.coins);

        frame.add(wrapper);
        frame.setResizable(false);
        frame.setVisible(true);
        ((CardLayout)(wrapper.getLayout())).show(wrapper, "Click");
        frame.revalidate();
        frame.repaint();
    }

    //Purpose of getFancyFont() is to create the font used for all the text on the start panel
    public Font getFancyFont() {
        Font f = new Font(FONT_NAME, Font.BOLD, 26);
        return f;
    }
}