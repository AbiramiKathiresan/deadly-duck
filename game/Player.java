import java.awt.*;

//Purpose of the Player class is to keep track of the movements of the player during the game
public class Player extends Objects {

    ObjectsHandler handler;
    GamePanel game;
    int width = 40, height = 40;
    boolean portalIntersects;

    //Purpose of this constructor is to call the parent class' constructor and initialize this class' variables
    public Player(int x, int y, ObjectsHandler newHandler, GamePanel newGame)
    {
        super(x, y, GamePanel.PLAYER_ID);
        handler = newHandler;
        game = newGame;
        portalIntersects = false;
    }

    //Purpose of tick() is to move the player object according to the user's key motions
    public void tick() {
        if(GamePanel.playerHealth <= 0) {
            return;
        }
        x += speedX;
        y += speedY;
        collision();

        if(handler.up)
           speedY = -5;   
        else if(!(handler.down))
            speedY = 0;

        if(handler.right)
            speedX = 5;
        else if(!(handler.left))
            speedX = 0;

        if(handler.down)
           speedY = 5;
        else if(!(handler.up))
            speedY = 0;

        if(handler.left)
            speedX = -5;
        else if(!(handler.right))
            speedX = 0;
    }
    
    //Purpose of render() is to draw the player object depending on the key the player has pressed
    public void render(Graphics g) {
        if(handler.left){
            g.drawImage(ReadFromImage.getPlayerDuckFlipped(), x, y, width, height, null);
        } 
        else {
            g.drawImage(ReadFromImage.getPlayerDuck(), x, y, width, height, null);
        }
    }

    //Purpose of collision() is to detect whether the player object is colliding with another game object
    public void collision() {
        for(int i = 0; i < handler.objects.size(); i++)
        {
            if(handler.objects.get(i).getID().equals(GamePanel.WALL_ID)){
                Rectangle bounds = getBounds();
                if(bounds.intersects(handler.objects.get(i).getBounds()))
                {
                        x -= speedX;
                        y -= speedY;
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.GIFT_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    game.bullets += 5;
                    handler.removeObject(handler.objects.get(i));
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.ENEMY_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    game.playerHealth--;
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.COIN_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                   GamePanel.coins += 5;
                   handler.removeObject(handler.objects.get(i));
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.HEALTH_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    handler.removeObject(handler.objects.get(i));
                    GamePanel.playerHealth += 10;
                    if (GamePanel.playerHealth >= 200)
                        GamePanel.playerHealth = 200;
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.PORTAL_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    portalIntersects = true;
                }
            }

            if(handler.objects.get(i).getID().equals(GamePanel.BOSSAMMO_ID)){
                if(getBounds().intersects(handler.objects.get(i).getBounds()))
                {
                    GamePanel.playerHealth -= 5;
                }
            }
        }
    }

    //Purpose of getBounds() is to return a rectangle with the location, width, and height of the player object for collision detection
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
