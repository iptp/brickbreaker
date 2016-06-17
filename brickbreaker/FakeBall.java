import greenfoot.*;

public class FakeBall extends MyActor
{
    private Paddle paddle;
    
    public FakeBall(Paddle paddle)
    {
        this.paddle = paddle;
    }
    
    @Override
    public void addedToWorld(World world)
    {
        setLocation(paddle.getX(), paddle.getY() - paddle.getHeight() / 2 - getHeight() / 2);
    }
    
    @Override
    public void act()
    {
        // Follow the paddle
        setLocation(paddle.getX(), paddle.getY() - paddle.getHeight() / 2 - getHeight() / 2);
    }
}