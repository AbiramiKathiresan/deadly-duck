import java.awt.*;

//Purpose of this class is to create the coin objects the player collects during the game
public class Coin extends Objects{

    ObjectsHandler handler;
    int width, height;

    //Purpose of this constructor is to initialize this class' variables and call the parent class' constructor
    public Coin(int x, int y, String id, ObjectsHandler newHandler) {
        super(x, y, id);
        handler = newHandler;
        width = 40;
        height = 40;
    }

    //This tick() does not have a purpose 
    public void tick(){
    }

    //Purpose of render() is to draw the coin objects on to the game
    public void render (Graphics g){
        g.setColor(Color.yellow);
        g.drawImage(ReadFromImage.getCoin(), x, y, width, height, null);
    }

    //Purpose of getBounds() is to return a rectangle with height, width, and location of the coin objects for collision detection
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}