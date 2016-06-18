import greenfoot.*;
import java.util.List;

public class Paddle extends Actor
{
    private int width = 24;
    private int height = 104;
    
    private int speed = 4;
    private String upKey = "up";
    private String downKey = "down";
    
    public Paddle()
    {
        // Create a paddle with the default values above
    }
    
    public Paddle(int spd, String up, String down)
    {
        speed = spd;
        upKey = up;
        downKey = down;
    }
    
    public void addedToWorld(World w)
    {
        // Height and width are inverted because the paddle will be vertical and the image is horizontal
        getImage().scale(height, width);
        
        setRotation(90);
        
        // Fix the X position of the paddle, so the image is only touching the side of the screen
        if(getX() == 0)
            setLocation(getX() + width/2, getY());
        else if(getX() == getWorld().getWidth() - 1)
            setLocation(getX() - width/2, getY());
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown(upKey))
        {
            setRotation(270);
            move(speed);
            // Set the rotation back to 90, to the image doesn't look rotated
            setRotation(90);
        }
        else if(Greenfoot.isKeyDown(downKey))
        {
            setRotation(90);
            move(speed);
        }
        
        // Paddle went past the top wall
        if(getY() - height/2 < 0)
            setLocation(getX(), height/2);
        // Paddle went past the bottom wall
        else if(getY() + height/2 > getWorld().getHeight() - 1)
            setLocation(getX(), getWorld().getHeight() - height/2);
    }
}
