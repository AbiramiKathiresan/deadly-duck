
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Purpose of the Boss class is to create the boss duck that appears in the last level of the game
public class Boss extends Objects{
    Enemy enemy;
    ObjectsHandler handler;
    int width, height;
    public static int bossHealth;
    int frameCounter, maxFrames;
    int delayCounter, maxDelay;
    int ammoDelay, maxAmmoDelay;
    boolean bossKilled;

    //Purpose of this constructor is to initialize all variables for this class and call the parent class
    public Boss(int x, int y, String id, ObjectsHandler newHandler) {
        super (x, y, id);
        handler = newHandler;
        width = 400;
        height = 400;
        bossHealth = 20;
        frameCounter = 0;
        maxFrames = 15;
        delayCounter = 0;
        maxDelay = 15;
        ammoDelay = 0;
        maxAmmoDelay = 60;
        bossKilled = false;
    }

    //Purpose of tick() is to move and refresh the boss game object's movements 
    public void tick() {
        int randNum = (int)(Math.random() * 100);
        x += speedX;
        y += speedY;

        ammoDelay = (ammoDelay + 1) % maxAmmoDelay;

        if(ammoDelay == 0) {
            handler.addObject(new BossAmmo(x + 200, y + 200, GamePanel.BOSSAMMO_ID, handler));
        }

        for(int i = 0; i < handler.objects.size(); i++)
        {
            if(handler.objects.get(i).getID().equals(GamePanel.WALL_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    x += (speedX) * -1;
                    y += (speedY) * -1;
                    speedX *= -1;
                    speedY *= -1;
                }
                else if(randNum == 0) {
                    speedX = (int)(Math.random() * 6) - 3;
                    speedY = (int)(Math.random() * 6) - 3;
                }
            } else if(handler.objects.get(i).getID().equals(GamePanel.AMMO_ID)) {
                if(getBounds().intersects(handler.objects.get(i).getBounds())){
                    bossHealth -= 1;
                    handler.removeObject(handler.objects.get(i));
                }
            }
        }

        if(bossHealth <= 0)
        {
            handler.removeObject(this);
            GamePanel.coins += 50;
        } 
    }

    //Purpose of render() is to draw the boss duck image onto the game with animation
    public void render(Graphics g) {
        if(delayCounter == maxDelay - 1) {
            frameCounter = (frameCounter + 1) % maxFrames;
        }
        g.drawImage(ReadFromImage.getBossDuck(frameCounter), x, y, width, height, null);
        delayCounter = (delayCounter + 1) % maxDelay;

        if(bossHealth <= 0)
        {
            Font write = new Font("Franklin Gothic Heavy", Font.PLAIN, 20);
            g.setFont(write);
            g.setColor(Color.yellow);
            g.fillRect(330, 170, 300, 200);
            g.setColor(Color.black);
            g.drawString("Game Over You Won!", 420, 200);
            Font normal = new Font("ariel", Font.PLAIN, 16);
            g.setFont(normal);
            g.drawString("Press x to go back to the main menu", 350, 240);
            g.drawString("Press z to quit", 415, 280);
        } 
    }

    //Purpose of getBounds() is to return a rectangle with the location, width, and height of the boss duck object
    public Rectangle getBounds() {
        return new Rectangle (x, y, width, height);
    } 
}

