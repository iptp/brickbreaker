import greenfoot.*;
import java.awt.Color;


public class PowerUpBrick extends Brick
{
    public PowerUpBrick()
    {
        super(Color.WHITE);
    }
    
    public PowerUpBrick(java.awt.Color color)
    {
        super(color);
    }
    
    public void collideWith(Ball ball)
    {
        getWorld().addObject(new Ball(ball.getRotation() + 180), ball.getX(), ball.getY());
        die();
    }
}