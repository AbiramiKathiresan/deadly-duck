import java.util.ArrayList;
import java.awt.*;

//The purpose of the ObjectsHandler class is to add, remove, and accordingly create objects for the game
public class ObjectsHandler
{
    ArrayList<Objects> objects = new ArrayList<>();
    Player player;
    Boss boss;
    boolean up, down, right, left, xPress, zPress;

    //Purpose of this constructor is to initialize the variables of this class
    public ObjectsHandler()
    {
        right = false;
        left = false;
        up = false;
        down = false;
        xPress = false;
        zPress = false;
        player = null;
        boss = null;
    }

    //Purpose of getPlayer() is to return an instance of the player class
    public Player getPlayer() {
        return player;
    }

    //Purpose of getBoss() is to return an instance of the boss class
    public Boss getBoss() {
        return boss;
    }

    //Purpose of tick() is to update each game object
    public void tick() {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).tick();
        }
    }

    //Purpose of render() is to draw each game object 
    public void render(Graphics g) {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }

    //Purpose of addObject() is to add new objects to the array list of objects 
    public void addObject(Objects newObject) {
        if(newObject != null) {
            if(newObject instanceof Player) {
                player = (Player)(newObject);
            } else if(newObject instanceof Boss) {
                boss = (Boss)(newObject);
            }
            objects.add(newObject);
        }
    }

    //Purpose of removeObject() is to remove game objects from the array list of objects 
    public void removeObject(Objects removeObject) {
        objects.remove(removeObject);
    }

    //Purpose of removeAllObjects() is to remove all objects from the game
    public void removeAllObjects() {
        player = null;
        boss = null;
        objects.clear();
    }
}