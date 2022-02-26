import java.awt.*;

//Purpose of Health class is to create the health objects that will allow the player to gain more health points
public class Health extends Objects{

    ObjectsHandler handler;
    int width, height;

    //Purpose of this constructor is to call the parent class' constructor and initialize this class' variables
    public Health(int x, int y, String id, ObjectsHandler newHandler) {
        super(x, y, id);
        handler = newHandler;
        width = 32;
        height = 32;
    }

    //This tick() does not have a purpose
    public void tick(){
    }

    //Purpose of render() is to draw the hearts that hold the health points on to the game
    public void render (Graphics g){
        g.setColor(Color.white);
        g.drawImage(ReadFromImage.getHealth(), x, y, 32, 32, null);
    }

    //Purpose of getBounds() is to return a rectangle with the height, width, and location of the health objects for collision detection
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }
}