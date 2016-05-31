import greenfoot.*;

public class MyActor extends Actor
{
    public BrickBreaker getGame()
    {
        return (BrickBreaker) getWorld();
    }
    
    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
    
    public boolean touchingRightWall()  { return getX() >= getWorld().getWidth() - getWidth() / 2; }
    public boolean touchingBottomWall() { return getY() >= getWorld().getHeight() - getHeight() / 2; }
    public boolean touchingLeftWall()   { return getX() < getWidth() / 2; }
    public boolean touchingTopWall()    { return getY() < getHeight() / 2; }
}