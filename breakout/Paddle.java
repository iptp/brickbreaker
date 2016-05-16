import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Paddle extends Actor
{
    public static final int width  = 150;
    public static final int height = 30;
    
    private int speed;
    
    public Paddle()
    {
        // Create a paddle that moves 4 pixels per frame
        this(4);
    }
    
    public Paddle(int speed)
    {
        this.speed = speed;
    }
    
    public void addedToWorld(World world)
    {
        newBall();
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("left") && !touchingLeftWall())
        {
            setRotation(180);
            move(speed);
        }
        
        if(Greenfoot.isKeyDown("right") && !touchingRightWall())
        {
            setRotation(0);
            move(speed);
        }
       
        setRotation(0);
    }
    
    public void newBall()
    {
        getWorld().addObject(new FakeBall(this), getX(), getY() - height/2 - FakeBall.height/2);
    }
    
    private boolean touchingRightWall() { return getX() >= MyWorld.width - width/2; }
    private boolean touchingLeftWall()  { return getX() <= 0 + width/2; }
}
