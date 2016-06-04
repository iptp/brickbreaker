import greenfoot.*;
import java.util.List;

public class Ball extends MyActor
{
    private int rotation;
    BallMarker mk;
    
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
        mk = new BallMarker(this);
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
        // Ball stuck
        if(rotation == 0 || rotation == 180)
            rotation += 45; // Turn arbitrarily to get it unstuck
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
        getWorld().addObject(mk, 0, 0);
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
            //bounceOnHorizontalAxis();
            complexCollision(bricks);
            for(Brick b : bricks)
                b.collideWith(this);
        }
    }
    
    private void screenCollision()
    {
        if(wentOffScreen())
        {
            getGame().ballRemoved();
            getWorld().removeObject(mk);
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
    
    public void complexCollision(List<Brick> bricks)
    {
        if(bricks.size() == 0)
            return;
            
        // The ball's position on the brick grid
        int ballX = getX() / Brick.GRID_WIDTH;
        int ballY = getY() / Brick.GRID_HEIGHT;
        
        // Did we hit a brick that's on the same column/row of the ball on the brick grid?
        boolean column = false;
        boolean row = false;
        
        // The brick's position on the brick grid
        int brickX = 0;
        int brickY = 0;
        
        for(Actor brick : bricks)
        {
            brickX = brick.getX() / Brick.GRID_WIDTH;
            brickY = brick.getY() / Brick.GRID_HEIGHT;
            
            if(brickX == ballX)
                column = true;
            if(brickY == ballY)
                row = true;
        }
        
        boolean bounce_horz = false;
        boolean bounce_vert = false;
        
        if(column == true && row == false)
            bounce_horz = true;
        else if(column == false && row == true)
            bounce_vert = true;
        else
        {
            bounce_horz = true;
            bounce_vert = true;
            // Check for a funny case: hitting only one block, on the corner
            // Won't work properly if there are bricks occupying the same position
            if(bricks.size() == 1 && !column && !row)
            {
                if(ballX < brickX && facingLeft() || ballX > brickX && facingRight())
                    bounce_vert = false;
                if(ballY < brickY && facingUp() || ballY > brickY && facingDown())
                    bounce_horz = false;
            }
        }
        
        if(bounce_horz)
            bounceOnHorizontalAxis();
        if(bounce_vert)
            bounceOnVerticalAxis();
    }
}