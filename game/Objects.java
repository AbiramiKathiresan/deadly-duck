import java.awt.*;

//Purpose of Objects class is to keep track of the x coordinate, y coordinate, and identification of each game object
public class Objects 
{
    int x, y;
    double speedX, speedY;
    String id;

    //Purpose of this constructor is to initialize the variables for this class
    public Objects(int newX, int newY, String newID)
    {
        x = newX;
        y = newY;
        speedX = 0;
        speedY = 0;
        id = newID;
    }

    //This method is overidden in the subclass
    public void tick() { }
    
    //This method is overidden in the subclass
    public void render(Graphics g) { }
    
    //This method is overidden in the subclass
    public void collision() { }

    //This method is overidden in the subclass
    public Rectangle getBounds()
    {
        return null;
    }
    
    //Purpose of getID() is to return the string identification of the game object
    public String getID()
    {
        return id;
    }

    //Purpose of setID() is to set the string identificatiom of the game object to a new id when the object changes
    public void setID(String newID)
    {
        id = newID;
    }

    //Purpose of getX() is to return the x coordinate of a game object 
    public int getX()
    {
        return x;
    }

    //Purpose of getY() is to return the y coordinate of a game object
    public int getY()
    {
        return y;
    }

    //Purpose of setX() is to set the x coordinate of a game object to a new x value
    public void setX(int newX) {
        x = newX;
    }

    //Purpose of getY() is to set the y coordinate of a game object to a new y value 
    public void setY(int newY) {
        y = newY;
    }
}