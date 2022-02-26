import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;

//Purpose of GamePanel class is to create and add basic componenents for the game play of the Deadly Duck game
public class GamePanel extends JPanel implements ComponentListener {
    boolean run;
    Thread thread;
    ObjectsHandler handler;
    PlayerCamera camera;
    Wall wall;
    CardLayout cards;
    JPanel mainPanel;
    int birdType;
    boolean firstRun;

    public static String PLAYER_ID = "PLAYER";
    public static String WALL_ID = "WALL";
    public static String AMMO_ID = "AMMO";
    public static String ENEMY_ID = "ENEMY";
    public static String COIN_ID = "COIN";
    public static String GIFT_ID = "GIFT";
    public static String PORTAL_ID = "PORTAL";
    public static String HEALTH_ID = "HEALTH";
    public static String BOSS_ID = "BOSS";
    public static String PORTAL2_ID = "PORTAL2";
    public static String BOSSAMMO_ID = "BOSSAMMO";
    public static int bullets;
    public static int playerHealth;
    public static int coins;
    public static int currentLevel;

    JButton back;
    Timer animation;
    Leaderboard leader;

    //Purpose of this constructor is to initialize all variables in this class and create the animation timer
    public GamePanel()
    {
        super(null);
        run = false;
        handler = new ObjectsHandler();
        camera = new PlayerCamera(0, 0);
        this.addKeyListener(new Keys(handler));
        this.addMouseListener(new Mouse(handler, camera, this));
        this.addComponentListener(this);
        animation = new Timer(15, new TimerListener());
        this.setDoubleBuffered(true);
        birdType = 1;
        firstRun = true;
    }

    //Purpose of this constructor is to call the card layout and add the back button to the game panel
    public GamePanel(JPanel mp, Leaderboard lb)
    {
        this();
        mainPanel = mp;
        leader = lb;
        cards = (CardLayout)(mp.getLayout());
        back = new JButton("Back");
        back.setBackground(Color.yellow); 
        Font f = new Font ("Franklin Gothic Heavy", Font.BOLD, 18);
        back.setFont(f);
        back.setBounds(5, 5, 80, 35);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout temp = (CardLayout) mainPanel.getLayout();
                temp.show(mainPanel, "Click");
            }
        });
        this.add(back);
    }

    //Purpose of run() is to start the animation timer and request focus toward the game
    public void run()
    {
        this.requestFocusInWindow();
        animation.start();
    }

    //Purpose of reset() is to reset the player's health, ammo, and coins at the start of a new game
    public void reset() {
        handler.removeAllObjects();
        bullets = 10;
        playerHealth = 200;
        currentLevel = 1;
        coins = 0;
    }

    //Purpose of start() is to call the reset and createLevel1 methods and set the run boolean to true and the portalIntersects boolean to false
    public void start()
    {
        reset();
        run = true;
        createLevel1();
        handler.getPlayer().portalIntersects = false;
    }

    //Purpose of stop() is to set the run boolean to false, stop the animation timer, and call the write method in the Leaderboard class
    public void stop()
    {   
        run = false;
        if(!(firstRun)) {
            leader.write(coins);
        }
        animation.stop();
    }

    //Purpose of tick() is to move the player's camera and go the main menu if x is pressed and quit if y is pressed 
    public void tick()
    {
        if(playerHealth <= 0 || handler.getBoss() != null && handler.getBoss().bossHealth <= 0)
        {
            if(handler.xPress)
            {
                stop();
                cards.show(mainPanel, "Click");
            }
            else if(handler.zPress)
            {
                stop();
                System.exit(0);
            }
        } 
        else {
            for(int i = 0; i < handler.objects.size(); i++)
            {
                if(handler.objects.get(i).getID().equals(GamePanel.PLAYER_ID)){
                    camera.tick(handler.objects.get(i));
                }
            }
            handler.tick();
        }
    }

    //Purpose of render() is to collect information to draw the images on the game screen
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if(run) {
            Graphics2D d = (Graphics2D) g;

            int scaling = ReadFromFile.SCALING_FACTOR;
            for(int x = 0; x < 960 + scaling; x += scaling) {
                for(int y = 0; y < 540 + scaling; y += scaling) {
                    g.drawImage(ReadFromImage.getFloor(), x, y, scaling, scaling, null);
                }
            }
            
            d.translate(-camera.x, -camera.y);
            handler.render(g);
            d.translate(camera.x, camera.y);

            g.setColor(Color.red);
            g.fillRect(745, 5, 200, 32);
            g.setColor(Color.green);
            g.fillRect(745, 5, playerHealth, 32);
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            g.setColor(Color.black);
            g.drawRect(745, 5, 200, 32);
            g.setColor(Color.black); 
            g.drawImage(ReadFromImage.getCoin(), 845, 31, 32, 32, null);
            g.drawImage(ReadFromImage.getAmmo(), 760, 38, 18, 18, null);
            if(currentLevel == 3){
                g.setColor(Color.white);
            }
            else{
                g.setColor(Color.black);
            }
            g.drawString("Ammo: " + bullets, 785, 50);
            g.drawString("Coins: " + coins, 875, 50);
            g.setColor(Color.black);
            if(playerHealth <= 0) {
                Font write = new Font("Franklin Gothic Heavy", Font.PLAIN, 20);
                g.setFont(write);
                g.setColor(Color.yellow);
                g.fillRect(330, 170, 300, 200);
                g.setColor(Color.black);
                g.drawString("Game Over!", 420, 200);
                Font normal = new Font("ariel", Font.PLAIN, 16);
                g.setFont(normal);
                g.drawString("Press x to go back to the main menu", 350, 240);
                g.drawString("Press z to quit", 420, 280);
                g.drawString("Coins: " + coins, 450, 320);
                g.drawImage(ReadFromImage.getCoin(), 420, 298, 32, 32, null);
            }

            if(handler.getBoss() != null && handler.getBoss().bossHealth <= 0){
                Font write = new Font("Franklin Gothic Heavy", Font.PLAIN, 20);
                g.setFont(write);
                g.setColor(Color.yellow);
                g.fillRect(330, 170, 300, 200);
                g.setColor(Color.black);
                g.drawString("You Won!", 428, 200);
                Font normal = new Font("ariel", Font.PLAIN, 16);
                g.setFont(normal);
                g.drawString("Press x to go back to the main menu", 350, 240);
                g.drawString("Press z to quit", 420, 280);
                g.drawString("Coins: " + coins, 450, 320);
                g.drawImage(ReadFromImage.getCoin(), 420, 298, 32, 32, null);
            }
        }
    }

    //Purpose of readText() is to create an instance of the ReadFromFile class and add objects to the game by reading the text file
    public void readText(String filename) {
        ReadFromFile input = new ReadFromFile(filename, handler, this);
        while(input.hasObject()) {
            handler.addObject(input.readObject());
        }
    }

    //Purpose of getTotalFrames() is to return the number of frames each player bird has depending on the selected bird
    public int getTotalFrames() {
        if(birdType == 1 || birdType == 2) {
            return 2;
        } else {
            return 4;
        }
    }

    //Purpose of readAllImages() is to read into all the image files that are not specific to each level
    public void readAllImages() {
        ReadFromImage.readPlayerDuck("sprites/player_" + birdType, getTotalFrames());
        ReadFromImage.readPlayerDuckFlipped("sprites/playerFlipped_" + birdType, getTotalFrames());
        ReadFromImage.readAmmo("sprites/ammo.png");
        ReadFromImage.readAmmoChest("sprites/ammo_chest.png");
        ReadFromImage.readHealth("sprites/heart.png");
        ReadFromImage.readCoin("sprites/goldCoin5.png");
        ReadFromImage.readPortal("sprites/portal.png");
    }

    //Purpose of createLevel1() is to send the neccessary image files to the ReadFromImage class for the first level
    public void createLevel1() {
        readText("levels/level1.txt");
        readAllImages();
        ReadFromImage.readEnemyDuck("sprites/enemy_1", 4);
        ReadFromImage.readFloor("sprites/grass2.png");
        ReadFromImage.readWall("sprites/woodTexture.png");
    }

    //Purpose of createLevel2() is to send the neccessary image files to the ReadFromImage class for the second level
    public void createLevel2() {
        readText("levels/level2.txt");
        readAllImages();
        ReadFromImage.readEnemyDuck("sprites/enemy_2", 4);
        ReadFromImage.readWall("sprites/brick.png");
        ReadFromImage.readFloor("sprites/water.png");
    }

    //Purpose of createLevel3() is to send the neccessary image files to the ReadFromImage class for the third level
    public void createLevel3() {
        readText("levels/level3.txt");
        readAllImages();
        ReadFromImage.readEnemyDuck("sprites/enemy_3", 4);
        ReadFromImage.readWall("sprites/blackFloor.png");
        ReadFromImage.readFloor("sprites/blackWalls.png");
    }

    //Purpose of createLevel4() is to send the neccessary image files to the ReadFromImage class for the fourth level
    public void createLevel4() {
        readText("levels/level4.txt");
        readAllImages();
        ReadFromImage.readWall("sprites/stoneWall.png");
        ReadFromImage.readFloor("sprites/snow.png");
        ReadFromImage.readBossDuck("sprites/skeleton-01_fly", 16);
        ReadFromImage.readBossAmmo("sprites/BossAmmo.png");
    }

    //Purpose of componenetHidden() is to check whether the start panel is hidden and if so set the firstRun boolean to false
    public void componentHidden(ComponentEvent e) {
        // stop();
        firstRun = false;
    }

    //componenetMoved() is a component method that is unused
    public void componentMoved(ComponentEvent e) { }

    //componenetResized() is a component method that is unused
    public void componentResized(ComponentEvent e) { }

    //Purpose of componenetShown() is to check whether the start panel is shown and if so run the game
    public void componentShown(ComponentEvent e) {
        run();
    }

    //Purpose of setBirdType is to return the type of the player bird the user has selected
    public void setBirdType(int bt) {
        birdType = bt;
    }

    //Purpose of this class is to listen to the timer actions to update the game
    class TimerListener implements ActionListener {

        //Purpose of actionPerformed() is to remove all objects, increment currentLevel, and create the next level if the user enters the portal
        public void actionPerformed(ActionEvent e) {
            tick();
            repaint();
            // System.out.println(player.portalIntersects);
            if(handler.getPlayer().portalIntersects && currentLevel == 1)
            {
                handler.removeAllObjects();
                currentLevel++;
                createLevel2();
            }

            if(handler.getPlayer().portalIntersects && currentLevel == 2)
            {
                handler.removeAllObjects();
                currentLevel++;
                createLevel3();
            }

            if(handler.getPlayer().portalIntersects && currentLevel == 3)
            {
                handler.removeAllObjects();
                currentLevel++;
                createLevel4();
            }
        }
    }
}

