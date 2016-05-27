import greenfoot.*;
import java.util.List;

public class Ball extends Actor
{
    public static final int DEFAULT_SIZE = 30;
    
    private int rotation;
    private int speed;
        
    public Ball(int speed, int rotation)
    {
        this.rotation = rotation;
        this.speed    = speed;
    }
        
    @Override
    public int getRotation()
    {
        return rotation;
    }

    @Override
    public void setRotation(int r)
    {
        rotation = r % 360;
        if(rotation < 0)
            rotation += 360;
    }
    
    @Override
    public void move(int speed)
    {
        super.setRotation(rotation);
        super.move(speed);
        super.setRotation(0);
    }
    
    @Override
    public void addedToWorld(World world)
    {
        
    }

    @Override
    public void act() 
    {
        paddleCollision();
        brickCollision();
        screenCollision();
        move(speed);
    }
    
    private void paddleCollision()
    {
        if(facingDown())
        {
            Actor paddle = getOneIntersectingObject(Paddle.class);
            if(paddle != null)  
                turnAwayFrom(paddle.getX(), getWorld().getHeight());
        }
    }
    
    private void brickCollision()
    {
        List<Brick> bricks = getIntersectingObjects(Brick.class);
        if(bricks.size() != 0)
        {
            // Simplified collision for the moment
            bounceOnHorizontalAxis();
            for(Brick b : bricks)
                b.collideWith(this);
        }
    }
    
    private void screenCollision()
    {
        if(facingDown() && touchingBottomWall())
        {
            // TODO rip ball
            getWorld().removeObject(this);
        }
        else if(facingLeft()  && touchingLeftWall())
            bounceOnVerticalAxis();
        else if(facingUp()    && touchingTopWall())
            bounceOnHorizontalAxis();
        else if(facingRight() && touchingRightWall())
            bounceOnVerticalAxis();
    }
    
    private void turnAwayFrom(int x, int y)
    {
        turnTowards(x, y);
        setRotation(rotation + 180);
    }
    
    private void bounceOnVerticalAxis()
    {
        setRotation(180 - rotation);
    }
    
    private void bounceOnHorizontalAxis()
    {
        setRotation(360 - rotation);
    }
    
    private boolean facingRight() { return rotationWithin(271,  90); }
    private boolean facingDown()  { return rotationWithin(  1, 180); }
    private boolean facingLeft()  { return rotationWithin( 91, 270); }
    private boolean facingUp()    { return rotationWithin(181,   0); }
    
    private boolean touchingRightWall()  { return getX() >= getWorld().getWidth() - getWidth() / 2; }
    private boolean touchingBottomWall() { return getY() >= getWorld().getHeight() - 1; }
    private boolean touchingLeftWall()   { return getX() < 0 + getWidth() / 2; }
    private boolean touchingTopWall()    { return getY() < 0 + getHeight() / 2; }
    
    private boolean rotationWithin(int start, int end)
    {
        // Check if rotation is within start .. end
        // That is, start < rotation <= end
        if(start <= end)
            return (start <= rotation && rotation < end);
        else
        {
            // Inverval is actually start .. 360 and 0 .. end
            return (start <= rotation || rotation < end);
        }
    }
    
    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
}
