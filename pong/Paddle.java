import greenfoot.*;
import java.util.List;

public class Paddle extends Actor
{
    public enum Side { LEFT, RIGHT };
    
    private int width  = 30;
    private int height = 150;
    
    private Side side = Side.LEFT;
    private int speed = 3;
    
    private String upKey   = "up";
    private String downKey = "down";
    
    // Create a paddle with the default values above
    public Paddle() { }
    
    public Paddle(Side s, int spd, String up, String down)
    {
        side = s;
        speed = spd;
        upKey = up;
        downKey = down;
    }
    
    public void addedToWorld(World w)
    {
        getImage().scale(width, height);
        fixLocation();
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown(upKey))
        {
            setRotation(270);
            move(speed);
        }
        else if(Greenfoot.isKeyDown(downKey))
        {
            setRotation(90);
            move(speed);
        }
        
        fixLocation();
    }
    
    public void bounceAway(Ball ball)
    {
        int originX = getX();
        
        if(side == Side.LEFT)
            originX -= width / 2;
        else // Side.RIGHT
            originX += width / 2;
            
        ball.turnTowards(originX, getY());
        ball.setRotation(ball.getRotation() + 180);
    }
    
    // Prevents the paddle from being partially offscreen
    private void fixLocation()
    {
        // Make the paddle face the correct direction
        if(side == Side.LEFT)
            setRotation(0);
        else // Side.RIGHT
            setRotation(180);
            
        // Prevent the paddle from going past the top or bottom walls
        if(getY() - height/2 < 0)
            setLocation(getX(), height/2);
        else if(getY() + height/2 > getWorld().getHeight() - 1)
            setLocation(getX(), getWorld().getHeight() - height/2);
            
        // Prevent the paddle from going past the left or right walls)
        if(getX() - width/2 < 0)
            setLocation(width/2, getY());
        else if(getX() + width/2 > getWorld().getWidth() - 1)
            setLocation(getWorld().getWidth() - width/2, getY());
    }
}
