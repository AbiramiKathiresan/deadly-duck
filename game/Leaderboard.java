import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Purpose of this class is to create the leaderboard panel that is displayed when the user clicks the leaderboard button
public class Leaderboard extends JPanel {
    JPanel mainPanel;
    JButton back, submit;
    Image sky;
    public ArrayList<LeaderboardEntry> entries;
    JTextArea text;
    public String userName;

    // Purpose of this constructor is to create a back button and initialize all the variables for this class
    public Leaderboard(JPanel mp) {
        super(null);
        mainPanel = mp;

        text = new JTextArea("Default User");
        text.setBackground(Color.yellow);
        Font font = new Font("Franklin Gothic Heavy", Font.BOLD, 22);
        text.setFont(font);
        text.setBounds(385, 70, 200, 30);
        userName = "Default User";

        submit = new JButton("Submit");
        submit.setBackground(Color.yellow);
        Font f = new Font("Franklin Gothic Heavy", Font.BOLD, 18);
        submit.setFont(f);
        submit.setBounds(430, 105, 100, 30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = text.getText();
                submit.setBackground(Color.green);
            }
        });

        back = new JButton("Back");
        back.setBackground(Color.yellow);
        back.setFont(f);
        back.setBounds(5, 5, 100, 40);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout temp = (CardLayout) mainPanel.getLayout();
                temp.show(mainPanel, "Click");
                submit.setBackground(Color.yellow);
            }
        });

        this.add(back);
        this.add(submit);
        this.add(text);
        repaint();
        sky = new ImageIcon("sprites/sky.png").getImage();
        entries = new ArrayList<>();
    }

    // Purpose of paintComponent is to draw the leaderboard panel's text and background
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sky, 0, 0, 960, 505, null);
        Font f = new Font("Franklin Gothic Heavy", Font.PLAIN, 28);
        g.setFont(f);
        g.drawString("Leaderboard", 400, 50);
        Font font = new Font("Franklin Gothic Books", Font.BOLD, 18);
        g.setFont(font);
        g.drawString("Username", 200, 200);
        g.drawString("Number of Coins", 420, 200);
        g.drawString("Rank", 690, 200);
        ReadFromImage.readCoin("sprites/goldCoin5.png");
        g.drawImage(ReadFromImage.getCoin(), 390, 177, 32, 32, null);
        int yCoor = 230;
        int rank = 1;
        for (int i = 0; i < entries.size() && i < 10; i++) {
            String name = entries.get(i).getName();
            int num = entries.get(i).getCoin();
            if (num > 0) {
                if (num >= 100) {
                    g.drawString("" + num, 470, yCoor);
                } else if (num < 10) {
                    g.drawString("" + num, 490, yCoor);
                } else {
                    g.drawString("" + num, 480, yCoor);
                }
                if (rank == 10) {
                    g.drawString(rank + "", 700, yCoor);
                } else {
                    g.drawString(rank + "", 710, yCoor);
                }
                g.drawString(name, 200, yCoor);
                yCoor += 20;
                rank += 1;
            }
        }
    }

    // Purpose of write() is to append to the Leaderboard.txt file with the number of coins the player has collected
    public void write(int coins) {
        String outFileName = "levels/Leaderboard.txt";
        File outFile = new File(outFileName);
        PrintWriter makesOutput = null;
        try {
            makesOutput = new PrintWriter(new FileWriter(outFile, true));
            makesOutput.println(coins + "," + userName);
            makesOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Cannot append to " + outFile + " file.");
            System.exit(1);
        }
        read();
    }

    // Purpose of read() is to read into the Leaderboard.txt file in order to output the information on to the leaderboard panel
    public void read() {
        File inFile = new File("levels/Leaderboard.txt");
        String name = "levels/Leaderboard.txt";
        Scanner input = null;
        entries.clear();
        try {
            input = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find " + name + " file.");
            System.exit(1);
        }

        while (input.hasNext()) {
            String temp = input.nextLine().trim();
            String[] data = temp.split(",");
            LeaderboardEntry le = new LeaderboardEntry(data[0], data[1]);
            entries.add(le);
        }
        input.close();
        Collections.sort(entries);
    }

    //Purpose of LeaderboardEntry is to keep track of the username and coins the player has collected for the leaderboard
    class LeaderboardEntry implements Comparable<LeaderboardEntry> {
        String name;
        Integer num;

        /*//Purpose of this constructor is to initialize this class' variables
        public LeaderboardEntry(Integer intNum, String str) {
            name = str;
            num = intNum;
        } */
 
        //Purpose of this constructor is to initialize this class' variables 
        public LeaderboardEntry(String intNum2, String str2) {
            name = str2;
            if(name.length() >= 18) {
                name = name.substring(0, 19);
            }
            num = Integer.parseInt(intNum2);
        }

        //Purpose of getName() is to return the username string
        public String getName() {
            return name;
        }

        //Purpose of getCoin() is to return the number of coins the player has collected
        public Integer getCoin() {
            return num;
        }

        //Purpose of compareTo() is to return a int that has the greatest value after comparison
        public int compareTo(LeaderboardEntry lead) {
            return lead.getCoin().compareTo(num);
        }
    }
}