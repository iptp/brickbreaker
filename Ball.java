import greenfoot.*;
import java.util.List;

public class Ball extends MyActor
{
    private int rotation;
    private int speed;
    
    public Ball()
    {
        this(4, 270);
    }
    
    public Ball(int rotation)
    {
        this(4, rotation);
    }
    
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
        getGame().ballCreated();
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
            Actor p = getOneIntersectingObject(Paddle.class);
            if(p != null)
            {
                // If the ball is too low (past center of paddle), it will keep going down offscren
                if(getY() > p.getY())
                    bounceOnVerticalAxis();
                else
                    turnAwayFrom(p.getX(), getWorld().getHeight());
            }
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
        if(wentOffScreen())
        {
            getGame().ballRemoved();
            getWorld().removeObject(this);
        }
        else if(facingLeft() && touchingLeftWall())
            bounceOnVerticalAxis();
        else if(facingUp() && touchingTopWall())
            bounceOnHorizontalAxis();
        else if(facingRight() && touchingRightWall())
            bounceOnVerticalAxis();
    }
    
    public void turnAwayFrom(int x, int y)
    {
        turnTowards(x, y);
        setRotation(rotation + 180);
    }
    
    public void bounceOnVerticalAxis()
    {
        setRotation(180 - rotation);
    }
    
    public void bounceOnHorizontalAxis()
    {
        setRotation(360 - rotation);
    }
    
    public boolean wentOffScreen() { return getY() == getWorld().getHeight() - 1; }
    
    public boolean facingRight() { return rotationWithin(271,  90); }
    public boolean facingDown()  { return rotationWithin(  1, 180); }
    public boolean facingLeft()  { return rotationWithin( 91, 270); }
    public boolean facingUp()    { return rotationWithin(181,   0); }
    
    public boolean rotationWithin(int start, int end)
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
    
    public void setSpeed(int s) {speed = s;}
}