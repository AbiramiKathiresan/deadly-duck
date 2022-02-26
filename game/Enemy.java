import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Purpose of Enemy class is to create the enemy objects for the game
public class Enemy extends Objects {

    ObjectsHandler handler;
    int randNum;
    int height, width;
    int enemyHealth;
    int frameCounter = 0, maxFrames = 4;
    int delayCounter = 0, maxDelay = 15;

    //Purpose of this constructor is to initialize this class' variables and call the parent class' constructor
    public Enemy(int x, int y, ObjectsHandler newHandler)
    {
        super(x, y, GamePanel.ENEMY_ID);
        handler = newHandler;
        height = 35;
        width = 35;
        enemyHealth = 100;
        speedX = 1;
        speedY = 1;
    }

    //Purpose of tick() is to change the direction of the enemy objects when they collide into a wall object and keep track of each enemy's health
    public void tick() {
        x += speedX;
        y += speedY;
        randNum = (int)(Math.random() * 100);
        
        for(int i = 0; i < handler.objects.size(); i++)
        {
            if(handler.objects.get(i).getID().equals(GamePanel.WALL_ID)){
                if(getBoundsOutside().intersects(handler.objects.get(i).getBounds()))
                {
                    x += (speedX ) * -1;
                    y += (speedY ) * -1;
                    speedX *= -1;
                    speedY *= -1;
                }
                else if(randNum == 0) {
                    speedX = (int)(Math.random() * 2) - 1;
                    speedY = (int)(Math.random() * 2) - 1;
                    if(speedX <= 0)
                        speedX -= 1;
                    else
                        speedX += 1;

                    if(speedY <= 0)
                        speedY -= 1;
                    else
                        speedY += 1;
                    
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.AMMO_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds())){
                    enemyHealth -= 25;
                }
            }
            
        }

        if(enemyHealth <= 0)
        {
            handler.removeObject(this);
            GamePanel.coins += 2;
        }
    }

    //Purpose of render() is to draw the enemy ducks and its animatiom on to the game
    public void render (Graphics g){
        if(delayCounter == maxDelay - 1) {
            frameCounter = (frameCounter + 1) % maxFrames;
        }
        g.drawImage(ReadFromImage.getEnemyDuck(frameCounter), x, y, width, height, null);
        delayCounter = (delayCounter + 1) % maxDelay;
    }

    //Purpose of getBounds() is to return a rectangle with the width, height, and location of the enemy objects
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    //Purpose of getBoundsOutside is to return a rectangle that surrounds the enemy duck to detect collision
    public Rectangle getBoundsOutside(){
        return new Rectangle(x - width / 2, y - height / 2, width * 2, height * 2);
        //return new Rectangle(x - width/4, y - height / 2, width * 2, height * 2);
    }
    
}