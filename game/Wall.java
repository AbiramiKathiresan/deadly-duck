import java.awt.*;

//The purpose of the Wall class is to set the basic measurements of the walls that the player has to move around 
public class Wall extends Objects {
    int width, height;

    //Purpose of this constructor is to call the parent class' constructor and initialize this class' variables
    public Wall(int x, int y, int w, int h, String id) {
        super(x, y, id);
        width = w;
        height = h;
    }

    //Purpose of tick() is to update the x and y coordinates of the player
    public void tick() {
        x += speedX;
        y += speedY;
    }
        
    //Purpose of render() is to draw the wall objects 
    public void render(Graphics g) {
        g.drawImage(ReadFromImage.getWall(), x, y, width, height, null);
    }

    //The collision() has no purpose
    public void collision() {}

    //Purpose of this method is to return a rectangle with the location, height, and width of the wall objects for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}