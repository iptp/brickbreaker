import greenfoot.*;

public class FakeBall extends Actor
{
    private Paddle paddle;
    
    public FakeBall(Paddle paddle)
    {
        this.paddle = paddle;
    }
    
    @Override
    public void act()
    {
        // Follow the paddle
        setLocation(paddle.getX(), paddle.getY() - paddle.getHeight() / 2 - getHeight() / 2);
    }

    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
}
