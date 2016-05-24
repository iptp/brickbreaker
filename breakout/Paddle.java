import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Paddle extends Actor
{
    public static int width  = 150;
    public static int height = 30;
    
    private boolean space_pressed;
    private int speed;
    private BallManager ballmg;
    
    public Paddle()
    {
        // Create a paddle that moves 4 pixels per frame
        this(4);
    }
    
    public Paddle(int speed)
    {
        this.speed = speed;
        space_pressed = false;
        ballmg = null;
    }
    
    public void addedToWorld(World world)
    {
        ballmg = new BallManager(this);
        newBall();
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("space"))
        {
            // TNOTE do without space_pressed first
            // We want to shoot only the moment space is pressed, not on every frame it is pressed down
            if(!space_pressed)
                ballmg.shoot();
            space_pressed = true;
        }
        else
            space_pressed = false;
        
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
       
        // We don't want the image to look rotated
        setRotation(0);
    }
    
    public void newBall()
    {
        ballmg.newBall();
    }

    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
    
    private boolean touchingRightWall() { return getX() >= GameScreen.width - getWidth() / 2; }
    private boolean touchingLeftWall()  { return getX() < 0 + getWidth() / 2; }
}
