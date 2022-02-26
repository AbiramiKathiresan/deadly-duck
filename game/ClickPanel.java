import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

//Purpose of this class is to create the start panel that the other game buttons are on
public class ClickPanel extends JPanel {
    JLabel name;
    JButton instructions, leaderboard, play, options, quit;
    Image nightSky;
    Timer animation;
    Timer words;
    int xCoor, yCoor;
    int xDir, yDir;
    int changeNum;
    String base;
    String display;
    ArrayList<String> displayStrings;
    int increment;

    //Purpose of this constructor is to pass in the game buttons, add the buttons on to this panel, and initialize this class' variables
    public ClickPanel(JLabel title, JButton opt, JButton qt, JButton inst, JButton lead, JButton button){
        super(null);
        name = title;
        options = opt;
        quit = qt;
        instructions = inst;
        leaderboard = lead;
        play = button;
        animation = new Timer(40, new TimerListener());
        words = new Timer(60, new TimerListenerWords());

        this.add(name);
        this.add(play);
        this.add(leaderboard);
        this.add(instructions);
        this.add(options);
        this.add(quit);

        nightSky = new ImageIcon("sprites/nightsky1.png").getImage();
        xCoor = 50;
        yCoor = 50;
        xDir = 1;
        yDir = 1;
        changeNum = 3;
        ReadFromImage.readPlayerDuck("sprites/player_1", 2);
        animation.start();
        words.start();
        base = "Hello, welcome to Deadly Duck! Press the play game button to get started. Have fun and thank you for playing!";
        display = "";
        increment = 0;

        displayStrings = new ArrayList<>();
        displayStrings.add("");
    }
    
    //Purpose of paintComponent() is to draw the player duck and background on to the start panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(nightSky, 0, 0, 950, 510, null);
        g.drawImage(ReadFromImage.getPlayerDuck(), xCoor, yCoor, 140, 130, null);
        Font font = new Font("Franklin Gothic Heavy", Font.PLAIN, 20);
        g.setColor(Color.yellow);
        g.setFont(font);
        for(int i = 0; i < displayStrings.size(); i++) {
            g.drawString(displayStrings.get(i), 20, 20 + i * 20);
        }

    }

    //Purpose of the TimerListener class is to create animation for the character on the start panel
    class TimerListener implements ActionListener {

        //Purpose of actionPerformed() is to move the character around the screen in a specific boundary
        public void actionPerformed(ActionEvent e) {
            repaint();

            xCoor += changeNum * xDir;
            yCoor += changeNum * yDir;
            if(xCoor >= 475) {
                xCoor -= changeNum;
                xDir = -1;
            } else if(xCoor <= 10) {
                xCoor += changeNum;
                xDir = 1;
            }

            if(yCoor >= 370) {
                yCoor -= changeNum;
                yDir = -1;
            } else if(yCoor <= 35) {
                yCoor += changeNum;
                yDir = 1;
            }

        }
    }

    //Purpose of TimerListenerWords class is to create the timer that prompts the text to be drawn on the start panel
    class TimerListenerWords implements ActionListener {
        //Purpose of actionPerformed is to add characters to a new string that will be outputted based on the timer
        public void actionPerformed (ActionEvent e) {
            if(increment >= base.length()) {
                return;
            }

            char temp = base.charAt(increment);
            //tbase = base.substring(increment + 1);

            if(displayStrings.get(displayStrings.size() - 1).length() >= 58) {
                displayStrings.add("");
            }
            int ind = displayStrings.size() - 1;
            String str = displayStrings.get(ind);
            str += temp;
            displayStrings.set(ind, str);
            display += temp;
            increment++;
        }
    }
}