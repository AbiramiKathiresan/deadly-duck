
//Purpose of PlayerCamera class is to create the movement of the game that will display when a player moves off of the main frame dimensions
public class PlayerCamera {
    double x, y;

    //Purpose of this constructor is to initialize the variables for this class
    public PlayerCamera(double newX, double newY)
    {
        x = newX;
        y = newY;
    }

    //Purpose of this tick() method is to move the camera display according to the player movements 
    public void tick(Objects player)
    {
        x += ((player.getX() - x) - 960 / 2);
        y += ((player.getY() - y) - 540 / 2);

        if(x < 0)
        {
            x = 0;
        }

        if (x > 960 * 2 - 625)
        {
            x = 960 * 2 - 625;
        }

        if(y < 0)
        {
            y = 0;
        }

        if(y > 540 * 2 - 325)
        {
            y = 540 * 2 - 325;
        }
    }
}