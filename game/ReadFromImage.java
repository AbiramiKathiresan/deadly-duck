import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//Purpose of ReadFromImage class is to read the image files to use when drawing the game objects 
public class ReadFromImage {
    public static BufferedImage[] playerDuck;
    public static BufferedImage[] playerDuckFlipped;
    public static int playerDuckFrameNum;
    public static int maxPlayerDuckFrameNum;
    public static int playerDuckFlippedFrameNum;
    public static int maxPlayerDuckFlippedFrameNum;
    public static BufferedImage[] enemyDuck;
    public static BufferedImage wall;
    public static BufferedImage ammo;
    public static BufferedImage floor;
    public static BufferedImage ammoChest;
    public static BufferedImage health;
    public static BufferedImage coin;
    public static BufferedImage portal;
    public static BufferedImage bossAmmo;
    public static BufferedImage[] bossDuck;
    public static int bossDuckFrameNum;
    public static int constantDelay;

    //Purpose of this constructor is to initialize all the variables for this class
    public ReadFromImage() {
        playerDuck = null;
        playerDuckFlipped = null;
        playerDuckFrameNum = 0;
        maxPlayerDuckFrameNum = 0;
        playerDuckFlippedFrameNum = 0;
        maxPlayerDuckFlippedFrameNum = 0;
        enemyDuck = null;
        wall = null;
        ammo = null;
        floor = null;
        ammoChest = null;
        health = null;
        coin = null;
        portal = null;
        bossAmmo = null;
        bossDuck = null;
        bossDuckFrameNum = 0;
        constantDelay = 20;
    }

    //Purpose of getFileName() is to recieve information from the GamePanel class to know which sprite file to read
    public static String getFileName(String filename, int index) {
        return filename + "_" + index + ".png";
    }

    //Purpose of readPlayerDuckFlipped() is to read the flipped player duck character's sprite files store all the animation sequences
    public static void readPlayerDuckFlipped(String filename, int length) {
        try {
            playerDuckFlipped = new BufferedImage[length];
            for(int i = 0; i < length; i++) {
                String temp = getFileName(filename, i + 1);
                playerDuckFlipped[i] = (BufferedImage) ImageIO.read(new File(temp));
            }
            maxPlayerDuckFlippedFrameNum = length * constantDelay;
            playerDuckFlippedFrameNum = 0;
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            e.printStackTrace();
            System.exit(1);
        }
    }

    //Purpose of getPlayerDuckFlipped() is to return the index of an array of the of player duck character's flipped frames 
    public static BufferedImage getPlayerDuckFlipped() {
        int temp = playerDuckFlippedFrameNum / constantDelay;
        playerDuckFlippedFrameNum = (playerDuckFlippedFrameNum + 1) % maxPlayerDuckFlippedFrameNum;

        return playerDuckFlipped[temp];
    }

    //Purpose of readPlayerDuck() is to read the player duck character's sprite files store all the animation sequences
    public static void readPlayerDuck(String filename, int length) {
        try {
            playerDuck = new BufferedImage[length];
            for(int i = 0; i < length; i++) {
                String temp = getFileName(filename, i + 1);
                playerDuck[i] = (BufferedImage) ImageIO.read(new File(temp));
            }
            maxPlayerDuckFrameNum = length * constantDelay;
            playerDuckFrameNum = 0;
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            e.printStackTrace();
            System.exit(1);
        }
    }

    //Purpose of getPlayerDuck() is to return the index of an array the of player duck character's frames 
    public static BufferedImage getPlayerDuck() {
        int temp = playerDuckFrameNum / constantDelay;
        playerDuckFrameNum = (playerDuckFrameNum + 1) % maxPlayerDuckFrameNum;

        return playerDuck[temp];
    }

    //Purpose of readEnemyDuck() is to read the enemy duck character's sprite files and store all the animation sequences
    public static void readEnemyDuck(String filename, int length) {
        try {
            enemyDuck = new BufferedImage[length];
            for(int i = 0; i < length; i++) {
                String temp = getFileName(filename, i + 1);
                enemyDuck[i] = (BufferedImage) ImageIO.read(new File(temp));
            }
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }
    
    //Purpose of getEnemyDuck() is to return the index of an array of the enemy duck character's frames 
    public static BufferedImage getEnemyDuck(int index) {
        return enemyDuck[index];
    }

    //Purpose of readWall() is to read the wall image file and store the wall image
    public static void readWall(String filename) {
        try {
            wall = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getWall() is to return the wall image
    public static BufferedImage getWall() {
        return wall;
    }

    //Purpose of readAmmo() is to read the ammo image file and store the bullet image 
    public static void readAmmo(String filename) {
        try {
            ammo = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getAmmo() is to return the ammo image
    public static BufferedImage getAmmo() {
        return ammo;
    }

    //Purpose of readFloor() is to read the floor image file and store the floor image
    public static void readFloor(String filename) {
        try {
            floor = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //purpose of getFloor() is to return the floor image
    public static BufferedImage getFloor() {
        return floor;
    }

    //Purpose of readAmmoChest() is to read the treausre chest image file and store the treasure chest image
    public static void readAmmoChest(String filename) {
        try {
            ammoChest = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getAmmoChest() is to return the treasure chest image
    public static BufferedImage getAmmoChest() {
        return ammoChest;
    }

    //Purpose of readHealth() is to read the heart image file and store the heart image
    public static void readHealth(String filename) {
        try {
            health = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getHealth() is to return the heart image
    public static BufferedImage getHealth() {
        return health;
    }

    //Purpose of readCoin() is to read the coin image file and store the coin image
    public static void readCoin(String filename) {
        try {
            coin = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getCoin() is to return the coin image
    public static BufferedImage getCoin() {
        return coin;
    }

    //Purpose of readPortal() is to read the portal image file and store the portal image
    public static void readPortal(String filename) {
        try {
            portal = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getPortal() is to return the portal image
    public static BufferedImage getPortal() {
        return portal;
    }

    //Purpose of readBossAmmo() is to read the boss duck's ammo image file and store the ammo image
    public static void readBossAmmo(String filename) {
        try {
            bossAmmo = (BufferedImage) ImageIO.read(new File(filename));
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }

    //Purpose of getBossAmmo() is to return the boss duck's ammo image
    public static BufferedImage getBossAmmo() {
        return bossAmmo;
    }

    //Purpose of readBossDuck() is to read the boss duck character's sprite files store all the animation sequences
    public static void readBossDuck(String filename, int length) {
        try {
            bossDuck = new BufferedImage[length];
            for(int i = 0; i < length; i++) {
                String temp = getFileName(filename, i);
                bossDuck[i] = (BufferedImage) ImageIO.read(new File(temp));
            }
            bossDuckFrameNum = length;
        } catch(IOException e) {
            System.err.println("Error reading file " + filename);
            System.err.println("Goodbye");
            System.exit(1);
        }
    }
    
    //Purpose of getBossDuck() is to return the index of an array of the boss duck character's frames 
    public static BufferedImage getBossDuck(int index) {
        return bossDuck[index];
    } 
}