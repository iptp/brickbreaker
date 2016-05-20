import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FakeBall extends Actor
{
    private Paddle paddle;
    
    FakeBall(Paddle paddle)
    {
        this.paddle = paddle;
    }
    
    public void act()
    {
        // Follow the home paddle
        setLocation(paddle.getX(), paddle.getY() - paddle.getHeight() / 2 - getHeight() / 2);
    }
    
    // We want to use the image's dimensions for collision and aligment
    // If we have bricks with different sizes, grid aligment will look funny
    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
}
