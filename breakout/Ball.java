import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // (List)

public class Ball extends Actor
{
    // The ball's height and width (it will behave more like a square)
    public static int size = 30;
    
    // When the ball falls off, it will placed back on this paddle (if it exists)
    private Paddle home_paddle;
    
    private int rotation;
    private int speed;
        
    public Ball(Paddle paddle, int speed, int rotation)
    {
        this.home_paddle = paddle;
        this.rotation    = rotation;
        this.speed       = speed;
    }
     
    public Ball(Paddle paddle)
    {
        this(paddle, 4, 90);
    }
    
    public Ball(int rotation)
    {
        this(null, 4, rotation);
    }
    
    public Ball()
    {
        this(null, 4, 90);
    }

    public void act() 
    {
        // Handle paddle collision
        Actor paddle = getOneIntersectingObject(Paddle.class);
        
        // If we didn't touch any Paddle, paddle will be null
        if(paddle != null && facingDown())
        {
            // We want the ball to move away from the paddle's center
            turnAwayFrom(paddle);
        }
        
        // Handle brick collision
        List bricks = getIntersectingObjects(Brick.class);
        
        // If we didn't touch any Brick, the list will be empty
        if(bricks.size() != 0)
        {
            // List returns an Object, we have to convert it to Actor before assigning
            // We use the first brick of the list to compute the collision
            // "Brick" is the type (class) of the variable; "brick" is the name!
            Brick brick = (Brick) bricks.get(0);
            
            // Check from which direction we collided with the brick to
            // determine how to bounce correctly
            if(!aboveOrBelow(brick))
                bounceOnVerticalAxis();
            if(!atLeftOrRight(brick))
                bounceOnHorizontalAxis();

            // Destroy all bricks we collided with
            getWorld().removeObjects(bricks);
        }
        
        // Handle wall collision
        fixRotationRange();
        
        if(facingDown() && touchingBottomWall())
        {
            if(home_paddle != null)
                home_paddle.newBall();
            // Destroy this lost ball
            getWorld().removeObject(this);
        }
        else if(facingLeft()  && touchingLeftWall())
            bounceOnVerticalAxis();
        else if(facingUp()    && touchingTopWall())
            bounceOnHorizontalAxis();
        else if(facingRight() && touchingRightWall())
            bounceOnVerticalAxis();

        setRotation(rotation);
        move(speed);
        
        // Always reset direction to 0, since we don't want to show a rotated image
        // Actual direction is kept in the rotation attribute
        setRotation(0);
    }
    
    private void turnAwayFrom(Actor actor)
    {
        turnTowards(actor.getX(), actor.getY());
        rotation = getRotation() + 180;
    }
    
    private void fixRotationRange()
    {
        // When we use the rotation want it to be within 0 <= x < 360
        // The methods that change it may put it below or beyond that range
        
        // This will make it within -359 <= x < 360
        rotation = rotation % 360;
        // This will make it within 1 <= x < 720
        rotation += 360;
        // And now, within 0 <= x < 360
        rotation = rotation % 360;
    }
    
    private void bounceOnVerticalAxis()
    {
        rotation = 180 - rotation;
    }
    
    private void bounceOnHorizontalAxis()
    {
        rotation = 360 - rotation;
    }

    private boolean facingRight() { return rotationWithin(270,  90); }
    private boolean facingDown()  { return rotationWithin(  0, 180); }
    private boolean facingLeft()  { return rotationWithin( 90, 270); }
    private boolean facingUp()    { return rotationWithin(180,   0); }
    
    private boolean touchingRightWall()  { return getX() >= MyWorld.width - size/2; }
    private boolean touchingBottomWall() { return getY() >= MyWorld.height - size/2; }
    private boolean touchingLeftWall()   { return getX() < 0 + size/2; }
    private boolean touchingTopWall()    { return getY() < 0 + size/2; }
            
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
      
    private boolean aboveOrBelow(Brick brick)
    {
        // Checks if X location of the ball is within the space
        // between the left and right walls of the brick
        return XWithin(brick.getX() - brick.getWidth() / 2, brick.getX() + brick.getWidth() / 2);
    }
    
    private boolean atLeftOrRight(Brick brick)
    {
        return YWithin(brick.getY() - brick.getHeight() / 2, brick.getY() + brick.getHeight() / 2);
    }
    
    private boolean XWithin(int left, int right)
    {
        return (left <= getX() && getX() < right);
    }
    
    private boolean YWithin(int top, int bottom)
    {
        return (top <= getY() && getY() < bottom);
    }
}
