import greenfoot.*;

public class Ball extends Actor
{
    private int speed = 3;
    
    public Ball()
    {
        setRotation(0);
    }
    
    public Ball(int rotation)
    {
        setRotation(rotation);
    }
    
    public void act()
    {
        int angle  = getRotation();
        int radius = getImage().getWidth() / 2;
        
        int rightWall  = getWorld().getWidth() - 1;
        int bottomWall = getWorld().getHeight() - 1;
        
        getWorld().showText("" + angle, 30, 15);
        
        // Collision against the left wall
        if((90 < angle && angle < 270) && (getX() - radius < 0))
            setRotation(180 - angle);
        // Collision against the right wall
        if((angle < 90 || angle > 270) && (getX() + radius > rightWall))
            setRotation(180 - angle);
        // Collision against the top wall
        if(angle > 180 && getY() - radius < 0)
            setRotation(-angle);
        // Collision against the bottom wall
        if(angle < 180 && getY() + radius > bottomWall)
            setRotation(-angle);
        
        move(speed);
    }
    
    // Workarounds to prevent the image from rotating
    private int realRotation = 0;
    
    public void setRotation(int angle)
    {
        // Keep the rotation within 0 .. 359
        realRotation = ((angle % 360) + 360) % 360;
    }
    
    public int getRotation()
    {
        return realRotation;
    }
    
    public void move(int speed)
    {
        super.setRotation(realRotation);
        super.move(speed);
        super.setRotation(0);
    }
}
