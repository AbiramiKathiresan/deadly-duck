import java.awt.*;

//Purpose of the Portal2 class is to create the Portal game object that transports the player from the last level but this portal object does not have a function
public class Portal2 extends Objects{

    ObjectsHandler handler;
    int width, height;

    //Purpose of this constructor is to call the parent class' constructor and intialize this class' variables
    public Portal2(int x, int y, String id, ObjectsHandler newHandler){
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

    //Purpose of getBounds() is to return a rectangle with location, width, and height of the functionless portal object
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
