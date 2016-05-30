import greenfoot.*;

public class MetalBrick extends Brick
{
    @Override
    public boolean isBreakable()
    {
        return false;
    }
    
    @Override
    public void collideWith(Ball ball)
    {
        // Do nothing
    }
}
