import greenfoot.*;
import java.awt.Color;

public class BallMarker extends Actor
{
    Ball ball;
    GreenfootImage marker;
    
    public BallMarker(Ball ball)
    {
        this.ball = ball;
        marker = new GreenfootImage(Brick.GRID_WIDTH, Brick.GRID_HEIGHT);
        marker.setColor(Color.RED);
        marker.fill();
        marker.setTransparency(100);
        setImage(marker);
    }
    
    public void act() 
    {
        setLocation(
            (ball.getX() / Brick.GRID_WIDTH) * Brick.GRID_WIDTH + Brick.GRID_WIDTH / 2,
            (ball.getY() / Brick.GRID_HEIGHT) * Brick.GRID_HEIGHT + Brick.GRID_HEIGHT / 2);
    }    
}
