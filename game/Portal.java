import java.awt.*;

//Purpose of the Portal class is to create the portal game objects that will transport the player to the next level
public class Portal extends Objects{

    ObjectsHandler handler;
    int width, height;

    //Purpose of this constructor is to call the parent class' constructor and intialize this class' variables
    public Portal(int x, int y, String id, ObjectsHandler newHandler){
        super(x, y, id);
        handler = newHandler;
        width = 32;
        height = 32;
    }

    //This tick() does not have a purpose
    public void tick(){}

    //Purpose of render() is to draw the portal game object
    public void render (Graphics g){
        g.drawImage(ReadFromImage.getPortal(), x, y, width, height, null);
    }

    //Purpose of getBounds() is to return a rectangle with location, height, and width of the portal object
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}