import greenfoot.*;

public class Paddle extends MyActor
{
    public static int DEFAULT_WIDTH  = 150;
    public static int DEFAULT_HEIGHT = 30;
    
    private boolean space_pressed;
    private int speed;
    
    private int ball_stock;
    private FakeBall fake;
    
    public Paddle()
    {
        // Create a paddle that moves 4 pixels per frame
        this(4);
    }
    
    public Paddle(int speed)
    {
        this.speed = speed;
        space_pressed = Greenfoot.isKeyDown("space");
        ball_stock = 0;
        fake = new FakeBall(this);
    }
    
    @Override
    public void addedToWorld(World world)
    {
        spawnBall();
    }
    
    @Override
    public void act() 
    {
        if(Greenfoot.isKeyDown("space"))
        {
            if(space_pressed == false)
                shootBall();
            space_pressed = true;
        }
        else
            space_pressed = false;
        
        if(Greenfoot.isKeyDown("left") && !touchingLeftWall())
        {
            setRotation(180);
            move(speed);
            // We don't want the image to look rotated
            setRotation(0);
        }
        
        if(Greenfoot.isKeyDown("right") && !touchingRightWall())
        {
            setRotation(0);
            move(speed);
        }
    }
    
    public void shootBall()
    {
        if(ball_stock > 0)
        {
            ball_stock--;
            getWorld().addObject(new Ball(), fake.getX(), fake.getY());
            if(ball_stock == 0)
                getWorld().removeObject(fake);
        }
    }
    
    public void spawnBall()
    {
        if(ball_stock == 0)
            getWorld().addObject(fake, 0, 0);
        ball_stock++;
    }
    
    public void setSpeed(int s) {speed = s;}
}