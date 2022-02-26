
import java.awt.*;

//Purpose of Gift class is to create the gift game objects the player collects to gain bullets 
public class Gift extends Objects{
    int width, height;

    //Purpose of this constructor is to call the parent class' constructor and initialize this class' variables
    public Gift(int x, int y, String id){
        super(x, y, id);
        width = 25;
        height = 25;
    }

    //This tick() does not have a purpose
    public void tick() {
    }

    //Purpose of render() is to draw the gift objects on to the game
    public void render(Graphics g){
        g.drawImage(ReadFromImage.getAmmoChest(), x, y, width, height, null);
    }

    //Purpose of getBounds() is to return a rectangle with the location, height, and width of the gift obejcts for collision detection
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}