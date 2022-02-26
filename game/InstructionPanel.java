
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//Purpose of this class is to create the instructions panel that is displayed when the how to play button is clicked
public class InstructionPanel extends JPanel {
    JPanel mainPanel;
    JButton back;
    String fontName;
    int frameCounter, maxFrames, delayCounter, maxDelay;
    int frameCounter2, maxFrames2, delayCounter2, maxDelay2;
    ReadFromImage image;
    Image portal, coin, ammoChest, ammo, arrow, heart, cursor;

    //Purpose of this constructor is to initialize all the variables for this class and create a button to go back to the start panel
    public InstructionPanel(JPanel mp) {
        super(null);
        image = new ReadFromImage();
        frameCounter = 0;
        maxFrames = 2;
        delayCounter = 0;
        maxDelay2 = 10;
        frameCounter2 = 0;
        maxFrames2 = 4;
        delayCounter2 = 0;
        maxDelay = 15;
        mainPanel = mp;
        back = new JButton("Back");  
        back.setBackground(Color.yellow); 
        Font f = getFancyFont2();   
        back.setFont(f);
        back.setBounds(5, 5, 100, 40);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout temp = (CardLayout) mainPanel.getLayout();
                temp.show(mainPanel, "Click");
            }
        });

        this.add(back);

        Timer animation = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InstructionPanel.this.repaint();
            }
        });

        animation.start();

        fontName = "Franklin Gothic Book";
        ReadFromImage.readPlayerDuck("sprites/player_1", 2);
        ReadFromImage.readEnemyDuck("sprites/enemy_1", 4);

        arrow = new ImageIcon("sprites/arrowKeys.png").getImage();
        ammo = new ImageIcon("sprites/ammo.png").getImage();
        cursor  = new ImageIcon("sprites/cursor.png").getImage();
        ammoChest = new ImageIcon("sprites/ammo_chest.png").getImage();
        heart = new ImageIcon("sprites/heart.png").getImage();
        coin = new ImageIcon("sprites/goldCoin5.png").getImage();
        portal  = new ImageIcon("sprites/portal.png").getImage();
    }

    //Purpose of paintComponent() is to draw the instructions and its corresponding images on to the instructions panel 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Font f = new Font("Franklin Gothic Heavy", Font.BOLD, 26);
        g.setFont(f);
        g.drawString("How To Play", 400, 50);
        Font font = getFancyFont();
        g.setFont(font);
        g.drawString("Use up, down, right, left or a, w, s, d keys to", 450, 110);
        g.drawString("move the player duck up, down, right, or left", 450, 130);
        g.drawImage(arrow, 130, 55, 130, 90, null);

        g.drawString("Use your mouse or touchpad to shoot bullets", 450, 190);
        g.drawString("at an enemy duck to kill it", 450, 210);

        g.drawImage(ReadFromImage.getPlayerDuck(), 70, 165, 60, 55, null);

        if(delayCounter2 == maxDelay2 - 1) {
            frameCounter2 = (frameCounter2 + 1) % maxFrames2;
        }
        g.drawImage(ReadFromImage.getEnemyDuck(frameCounter2), 260, 169, 60, 50, null);
        delayCounter2 = (delayCounter2 + 1) % maxDelay2;

        g.drawImage(ammo, 140, 185, 13, 13, null);
        g.drawImage(ammo, 160, 185, 13, 13, null);
        g.drawImage(ammo, 180, 185, 13, 13, null);
        g.drawImage(ammo, 200, 185, 13, 13, null);
        g.drawImage(ammo, 220, 185, 13, 13, null);
        g.drawImage(cursor, 242, 185, 13, 19, null); 

        g.drawString("As you move through the game collect treasure", 450, 270);
        g.drawString("chests for more ammo, hearts for more health", 450, 290);     
        g.drawString("points, and coins", 450, 310); 
        g.drawImage(ammoChest, 110, 250, 50, 45, null);
        g.drawImage(heart, 185, 250, 50, 50, null);
        g.drawImage(coin, 255, 252, 50, 50, null);

        g.drawString("Find and enter portals in each game level to", 450, 370);
        g.drawString("go to the next level of the game", 450, 390);
        g.drawImage(portal, 185, 335, 50, 60, null);

        g.drawString("Everytime you collide with an enemy bird your", 450, 450);
        g.drawString("health decreases", 450, 470);

        if(delayCounter2 == maxDelay2 - 1) {
            frameCounter2 = (frameCounter2 + 1) % maxFrames2;
        }
        g.drawImage(ReadFromImage.getEnemyDuck(frameCounter2), 90, 426, 60, 50, null);
        delayCounter2 = (delayCounter2 + 1) % maxDelay2;

        g.drawImage(ReadFromImage.getPlayerDuck(), 60, 424, 60, 55, null);

        g.setColor(Color.green);
        g.fillRect(205, 438, 120, 30);
        g.setColor(Color.red);
        g.fillRect(260, 438, 75, 30);
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        g.drawRect(205, 438, 130, 30);
    }

    //Purpose of getFancyFont() is to return tje font to use for the titles
    public Font getFancyFont() {
        Font f = new Font(fontName, Font.PLAIN, 22);
        return f;
    }
        
    //Purpose of getFancyFont2() is to return the font to use for the instructions text
    public Font getFancyFont2() {
        Font f = new Font("Franklin Gothic Heavy", Font.BOLD, 18);
        return f;
    }

}