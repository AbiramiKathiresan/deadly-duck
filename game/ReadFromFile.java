import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Handler;

//Purpose of ReadFromFile class is to read the level file for each level and create objects according to the listed coordinates in the text file
public class ReadFromFile {
    Scanner keyboard;
    ObjectsHandler handler;
    GamePanel game;
    public static int SCALING_FACTOR = 35;

    //Purpose of this constructor is to initialize the variables of this class
    public ReadFromFile(String filename, ObjectsHandler newHandler, GamePanel newGame) {
        try {
            keyboard = new Scanner(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
        handler = newHandler;
        game = newGame;
    }

    //Purpose of close() is to close the scanner
    public void close() {
        keyboard.close();
    }

    //Purpose of hasObject() is to return a boolean value which determines whether there is still text in the file
    public boolean hasObject() {
        return keyboard.hasNext();
    }

    //Purpose of readObject() is to read into the text file, separate the numbers that indicate the location of the game objects, and send the information to other classes
    public Objects readObject() {
        String line = keyboard.nextLine();
        String[] info = line.split(",");

        int x = Integer.parseInt(info[1]) * SCALING_FACTOR;
        int y = Integer.parseInt(info[2]) * SCALING_FACTOR;

        if(GamePanel.WALL_ID.equals(info[0])) {
            return new Wall(x, y, SCALING_FACTOR, SCALING_FACTOR, GamePanel.WALL_ID);
        } else if(GamePanel.PLAYER_ID.equals(info[0])) {
            return new Player(x, y, handler, game);
        } else if(GamePanel.GIFT_ID.equals(info[0])) {
            return new Gift(x, y, GamePanel.GIFT_ID);
        } else if(GamePanel.ENEMY_ID.equals(info[0])) {
            return new Enemy(x, y, handler);
        } else if(GamePanel.PORTAL_ID.equals(info[0])) {
            return new Portal(x, y, GamePanel.PORTAL_ID, handler);
        } else if(GamePanel.COIN_ID.equals(info[0])) {
            return new Coin(x, y, GamePanel.COIN_ID, handler);
        } else if(GamePanel.HEALTH_ID.equals(info[0])) {
            return new Health(x, y, GamePanel.HEALTH_ID, handler);
        } else if(GamePanel.BOSS_ID.equals(info[0])) {
            return new Boss(x, y, GamePanel.BOSS_ID, handler);
        } else if(GamePanel.PORTAL2_ID.equals(info[0])) {
            return new Portal2(x, y, GamePanel.PORTAL2_ID, handler);
        } else {
            return null;
        }
    }
}