import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Purpose of the Mouse class is to detect the player's mouse movements to create bullets for the player object
public class Mouse implements MouseListener {
 
     ObjectsHandler handler;
     PlayerCamera camera;
     int xCoor, yCoor;
     GamePanel game;

    //Purpose of this constructor is to initialize this class' variables 
    public Mouse(ObjectsHandler newHandler, PlayerCamera newCamera, GamePanel newGame)
    {
        handler = newHandler;
        camera = newCamera;
        game = newGame;
    }

    //Purpose of mousePressed() is to know whether the user has pressed down on their mouse and if so decrease the amount of bullets they have
    public void mousePressed(MouseEvent e){

        xCoor = (int)(e.getX() + camera.x);
        yCoor = (int)(e.getY() + camera.y);

        for(int i = 0; i < handler.objects.size(); i++){
            Objects current = handler.objects.get(i);
            if(current.getID().equals(GamePanel.PLAYER_ID) && game.bullets >= 1 && game.playerHealth > 0)
            {
                if(handler.getBoss() == null || Boss.bossHealth >= 0) {
                    handler.addObject(new Ammo(current.getX() + 16, current.getY() + 24, GamePanel.AMMO_ID, handler, xCoor, yCoor));
                    game.bullets--;
                }
            }
        }
    }

    //mouseReleased() is a mouse method and does not have a purpose 
    public void mouseReleased(MouseEvent e){}

    //mouseClicked() is a mouse method and does not have a purpose 
    public void mouseClicked(MouseEvent e) {} 

    //mouseEntered() is a mouse method and does not have a purpose 
    public void mouseEntered(MouseEvent e) {}

    //mouseExited() is a mouse method and does not have a purpose 
    public void mouseExited(MouseEvent e) {}
}