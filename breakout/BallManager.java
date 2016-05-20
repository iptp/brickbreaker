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
            paddle.getWorld().addObject(fake, getSpawnX(), getSpawnY());
        ball_count++;
    }
    
    public void shoot()
    {
        if(ball_count > 0)
        {
            ball_count--;
            paddle.getWorld().addObject(new Ball(paddle), getSpawnX(), getSpawnY());
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
        return paddle.getY() - paddle.getHeight() / 2 - Ball.size/2;
    }
}
