import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Purpose of the BossAmmo class is to create the bullets that the boss duck shoots 
public class BossAmmo extends Objects{
    ObjectsHandler handler;

    //Purpose of this constructor is to initialize variables of this class and generate random numbers to decide the direction and speed of the boss duck's bullets 
    public BossAmmo(int x, int y, String id, ObjectsHandler newHandler) {
        super(x, y, id);
        handler = newHandler;

        int randNum = (int)(Math.random() * 4);
        int randSpeed = (int)(Math.random() * 5);
        int upOrDown = -1;
        if((int)(Math.random() * 2) == 0) {
            upOrDown = 1;
        }

        int leftOrRight = -1;
        if((int)(Math.random() * 2) == 0) {
            leftOrRight = 1;
        }

        speedX = upOrDown * randSpeed;
        speedY = (leftOrRight) * (5 - randSpeed);
    }

    //Purpose of tick() is to refresh and move the boss duck's bullets and remove bullets if it hits a wall
    public void tick() {
        x += speedX;
        y += speedY;

        for(int i = 0; i < handler.objects.size(); i++)
        {
            if(handler.objects.get(i).getID().equals(GamePanel.WALL_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    handler.removeObject(this);
                }
            }
        } 
    }

    //Purpose of render() is to draw the boss duck's bullets 
    public void render(Graphics g){
        g.drawImage(ReadFromImage.getBossAmmo(), x, y, 24, 24, null);

    }

    //Purpose of getBounds() is to return a rectangle with the location, width, and height of the boss duck's bullets 
    public Rectangle getBounds() {
        return new Rectangle(x, y, 24, 24);
    }
}