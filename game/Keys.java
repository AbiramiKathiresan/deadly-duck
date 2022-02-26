import java.awt.event.*;

//Purpose of Keys class is to detect the keys a player presses and releases to move the player accordingly
public class Keys implements KeyListener {
    ObjectsHandler handler;

    //Purpose of this constructor is to intialize this class' variables
    public Keys(ObjectsHandler newHandler) {
        handler = newHandler;
    }

    //Purpose of keyPressed is to determine which key the user has pressed and change each keys' boolean value to match that
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            handler.up = true;
        } else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            handler.down = true;
        } else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            handler.left = true;
        } else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            handler.right = true;
        }
        handler.xPress = key == KeyEvent.VK_X;
        handler.zPress = key == KeyEvent.VK_Z;
    }

    //Purpose of keyReleased is to determine which key a user has released and change the keys' boolean value back to false 
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){
            Objects current = handler.objects.get(i);
            if(current.getID().equals(GamePanel.PLAYER_ID))
            {
                if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
                {
                    handler.up = false;
                }
                if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
                {
                    handler.down = false;
                }
                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
                {
                    handler.left = false;
                }
                if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
                {
                    handler.right = false;
                }
            }
        }
    }
    
    //keyTyped() is one of the key methods and is not used
    public void keyTyped(KeyEvent e) {}
}