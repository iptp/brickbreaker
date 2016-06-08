import greenfoot.*;

public class Ball extends Actor
{
    int speed = 3;
    
    public Ball()
    {
        this(0);
    }
    
    public Ball(int rotation)
    {
        setRotation(rotation);
    }
    
    @Override
    public void act() 
    {
        computeWallCollision();
        move(speed);
    }
    
    public void computeWallCollision()
    {

    }
    
    public void bounceOnHorizontalAxis()
    {
        int r = - getRotation();
        setRotation(r);
    }
    
    public void bounceOnVerticalAxis()
    {
        int r = 180 - getRotation();
        setRotation(r);
    }
}
