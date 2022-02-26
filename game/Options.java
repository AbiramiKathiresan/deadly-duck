import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//Purpose of the Options class is to create the panel that shows the different player birds the user can select from 
public class Options extends JPanel{

    JPanel mainPanel;
    JButton back;
    JButton player1, player2, player3, player4;
    boolean player1Pressed, player2Pressed, player3Pressed, player4Pressed;
    GamePanel gamePanel;

    //Purpose of this constructor is to create the back button and the buttons with the different player birds
    public Options(JPanel mp, GamePanel gp){
        super(null);
        mainPanel = mp;
        back = new JButton ("Back");
        back.setBackground(Color.yellow); 
        Font f = new Font ("Franklin Gothic Heavy", Font.BOLD, 18);
        back.setFont(f);
        back.setBounds(5, 5, 100, 40);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout temp = (CardLayout) mainPanel.getLayout();
                temp.show(mainPanel, "Click");
            }
        });
        player1 = new JButton("");
        player1.setBackground(Color.yellow);
        player1.setBounds(60, 100, 150, 150);
        try{
            Image image1 = ImageIO.read(new File("sprites/player_1_1.png")).getScaledInstance(140, 135, Image.SCALE_DEFAULT);
            player1.setIcon(new ImageIcon(image1));
        } 
        catch (Exception e) {
        }

        player2 = new JButton("");
        player2.setBounds(480, 100, 150, 150);
        player2.setBackground(Color.gray);
        try{
            Image image2 = ImageIO.read(new File("sprites/playerCharacter2.png")).getScaledInstance(140, 120, Image.SCALE_DEFAULT);
            player2.setIcon(new ImageIcon(image2));
        } 
        catch (Exception e) {
        } 

        player3 = new JButton("");
        player3.setBounds(60, 300, 150, 150);
        player3.setBackground(Color.gray);
        try{
            Image image3 = ImageIO.read(new File("sprites/playerCharacter3.png")).getScaledInstance(140, 130, Image.SCALE_DEFAULT);
            player3.setIcon(new ImageIcon(image3));
        } 
        catch (Exception e) {
        } 

        player4 = new JButton("");
        player4.setBounds(480, 300, 150, 150);
        player4.setBackground(Color.gray);
        try{
            Image image4 = ImageIO.read(new File("sprites/playerCharacter4.png")).getScaledInstance(140, 130, Image.SCALE_DEFAULT);
            player4.setIcon(new ImageIcon(image4));
        } 
        catch (Exception e) {
        } 

        this.add(back);
        this.add(player1);
        this.add(player2);
        this.add(player3);
        this.add(player4);

        gamePanel = gp;
        buttons();
        repaint();
    }

    //Purpose of buttons() is to set the number of which player bird the user has selected and change the background color of the button
    public void buttons(){
        player1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setBirdType(1);
                player1.setBackground(Color.yellow);
                player2.setBackground(Color.gray);
                player3.setBackground(Color.gray);
                player4.setBackground(Color.gray);
            }
        });
        player2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setBirdType(2);
                player1.setBackground(Color.gray);
                player2.setBackground(Color.yellow);
                player3.setBackground(Color.gray);
                player4.setBackground(Color.gray);
            }
        });
        player3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setBirdType(3);
                player1.setBackground(Color.gray);
                player2.setBackground(Color.gray);
                player3.setBackground(Color.yellow);
                player4.setBackground(Color.gray);
            }
        });
        player4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setBirdType(4);
                player1.setBackground(Color.gray);
                player2.setBackground(Color.gray);
                player3.setBackground(Color.gray);
                player4.setBackground(Color.yellow);
            }
        });
    }

    //Purpose of paintComponent is to draw the information about each player bird onto the options panel
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        Font f = new Font("Franklin Gothic Books", Font.PLAIN, 18);
        Font font = new Font ("Franklin Gothic Heavy", Font.PLAIN, 26);
        g.setFont(font);
        g.drawString("Choose Your Player", 355, 50);
        g.setFont(f);
        g.drawString("Name: Dinkle", 220, 140);
        g.drawString("Description: Energetic, funny,", 220, 170);
        g.drawString("happy", 220, 190);
        g.drawString("Hobbies: Playing Fortnite", 220, 220);

        g.drawString("Name: Casper", 640, 140);
        g.drawString("Description: Shy, intelligent,", 640, 170);
        g.drawString("curious", 640, 190);
        g.drawString("Hobbies: Bird Watching", 640, 220);

        g.drawString("Name: Ozzie", 220, 340);
        g.drawString("Description: Brave, naughty,", 220, 370);
        g.drawString("cool", 220, 390);
        g.drawString("Hobbies: Horseback Riding", 220, 420);

        g.drawString("Name: Theo", 640, 340);
        g.drawString("Description: Grumpy, lazy,", 640, 370);
        g.drawString("honest", 640, 390);
        g.drawString("Hobbies: Sleeping/Eating", 640, 420);

    }
    
}