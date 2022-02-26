import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Purpose of Ammo class is to create the bullets the player shoots at enemies during the game
public class Ammo extends Objects {

    ObjectsHandler handler;
    int width = 10, height = 10;

    //Purpose of this constructor is to initialize the variables of this class and call the parent class' constructor
    public Ammo(int x, int y, String id, ObjectsHandler newHandler, int xCoor, int yCoor){
        super(x, y, id);
        handler = newHandler;

        speedX = (xCoor - x) / 10;
        speedY = (yCoor - y) / 10;
    }

    //Purpose of tick() is to move the bullet objects to the enemy and remove the bullets if it collides with the wall objects
    public void tick(){
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

    //Purpose of render() is to draw the bullet image on to the game
    public void render (Graphics g){
        g.drawImage(ReadFromImage.getAmmo(), x, y, width, height, null);
    }

    //Purpose of getBounds() is to return a rectangle with the height, width, and location of the bullet objects for collision detection
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}