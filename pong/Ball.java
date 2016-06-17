import greenfoot.*;
import java.util.List;

public class Ball extends Actor
{
    private int radius = 0;
    private int speed = 4;
    private int trueRotation = 0;
    
    public Ball()
    {
        // Create a ball with the default values above
    }
    
    public Ball(int spd, int angle)
    {
        speed = spd;
        trueRotation = angle;
    }
    
    public void addedToWorld(World w)
    {
        radius = getImage().getWidth() / 2;
    }

    public void act() 
    {
        // Retrieve the stored rotation
        setRotation(trueRotation);
        int angle = getRotation();
        
        // Bounce off the paddles
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if(paddle != null)
        {
            if(speed < 15)
                speed++;
                
            // Aim away from the center of the paddle
            turnTowards(paddle.getX(), paddle.getY());
            setRotation(getRotation() + 180);
            
            // We don't wan the new angle to be too vertical, so we cap it
            angle = getRotation();
            int cap = 30;
            
            // There are four possible directions:
            if(90 - cap < angle && angle <= 90) // Down-right
                angle = 90 - cap;
            else if(90 < angle && angle < 90 + cap) // Down-left
                angle = 90 + cap;
            else if(270 - cap < angle && angle <= 270) // Up-left
                angle = 270 - cap;
            else if(270 < angle && angle < 270 + cap) // Up-right
                angle = 270 + cap;
            
            setRotation(angle);
        }
        // Bounce off the top wall
        else if(angle > 180 && getY() - radius <= 0)
            setRotation(-angle);
        // Bounce of the bottom wall
        else if(angle < 180 && getY() + radius >= getWorld().getHeight() - 1)
            setRotation(-angle);
        else // Check if someone scored
        {
            // Get the world as a Pong object
            Pong pong = (Pong) getWorld();
            
            // Touching left wall, player 2 scores
            if(getX() - radius <= 0)
                pong.goal(2);
            // Touching right wall, player 1 scores
            else if(getX() + radius >= getWorld().getWidth() - 1)
                pong.goal(1);
        }
                
        move(speed);
        
        // Store the rotation
        trueRotation = getRotation();
        // Set the actor's rotation to 0 so the image doesn't look rotated
        setRotation(0);
    }
}
