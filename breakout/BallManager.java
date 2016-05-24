import greenfoot.*;

public class BallManager  
{
    private Paddle paddle;
    private FakeBall fake;
    private int ball_count;
    
    public BallManager(Paddle paddle)
    {
        this.paddle = paddle;
        fake = new FakeBall(paddle);
        ball_count = 0;
    }
    
    public void newBall()
    {
        if(ball_count == 0)
        {
            // We can't use just "getWorld()" without "paddle." because a BallManager is not an Actor
            // TNOTE show wrong way
            paddle.getWorld().addObject(fake, getSpawnX(), getSpawnY());
        }

        ball_count++;
    }
    
    public void shoot()
    {
        if(ball_count > 0)
        {
            ball_count--;
            paddle.getWorld().addObject(new Ball(paddle, 270), getSpawnX(), getSpawnY());
            if(ball_count == 0)
                paddle.getWorld().removeObject(fake);
        }
    }
    
    public int getSpawnX()
    {
        return paddle.getX();
    }
    
    public int getSpawnY()
    {
        // TNOTE display image with measurements
        return paddle.getY() - paddle.getHeight() / 2 - Ball.size / 2;
    }
}
