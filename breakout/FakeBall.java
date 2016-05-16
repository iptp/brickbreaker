import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FakeBall extends Actor
{
    public static final int height = 30;
    public static final int width = 30;
    private Paddle paddle;
    
    FakeBall(Paddle paddle)
    {
        this.paddle = paddle;
    }
    
    public void act()
    {
        // Follow the home paddle
        setLocation(paddle.getX(), paddle.getY() - height/2 - FakeBall.height/2);
        
        if(Greenfoot.isKeyDown("space"))
        {
            // Time to start
            // Create an actual ball at the same position and destroy this one
            getWorld().addObject(new Ball(paddle), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
