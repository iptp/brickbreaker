import greenfoot.*;
import java.util.List;

public class Ball extends Actor
{
    private int radius = 11;
    private int speed = 4;
    private int trueRotation = 0;
    
    // Sound effects
    private GreenfootSound hitPaddle = new GreenfootSound("tone1.mp3");
    private GreenfootSound hitWall = new GreenfootSound("twoTone2.mp3");
    private GreenfootSound score = new GreenfootSound("threeTone1.mp3");
    
    
    public Ball(int spd, int angle)
    {
        hitPaddle.setVolume(50);
        hitWall.setVolume(50);
        score.setVolume(50);
        
        speed = spd;
        trueRotation = angle;
    }
    
    public void addedToWorld(World w)
    {
        getImage().scale(radius * 2, radius * 2);
    }

    public void act() 
    {
        // Retrieve the stored rotation
        setRotation(trueRotation);
        
        int angle = getRotation();
        Pong pong = (Pong) getWorld();
        Actor paddle = getOneIntersectingObject(Paddle.class);
        
        // Bounce off the paddles
        if(paddle != null)
        {
            sound(hitPaddle);

            // Become faster, if not already too fast
            if(speed < 15)
                speed++;
                
            // Aim away from the center of the paddle
            turnTowards(paddle.getX(), paddle.getY());
            setRotation(getRotation() + 180);
            
            // We don't wan the new angle to be too vertical, so we cap it
            angle = getRotation();
            int cap = 30;
            
            // There are four directions we have to take into account
            if(90 - cap < angle && angle <= 90) // Down-right |\
                angle = 90 - cap;
            else if(90 < angle && angle < 90 + cap) // Down-left /|
                angle = 90 + cap;
            else if(270 - cap < angle && angle <= 270) // Up-left \|
                angle = 270 - cap;
            else if(270 < angle && angle < 270 + cap) // Up-right |/
                angle = 270 + cap;
            
            setRotation(angle);
        }
        // Touching left wall, player 2 scores
        else if(getX() - radius <= 0)
        {
            sound(score);
            pong.goal(2);   
        }
        // Touching right wall, player 1 scores
        else if(getX() + radius >= getWorld().getWidth() - 1)
        {
            sound(score);
            pong.goal(1);
        }
        // Bounce off the top wall
        else if(angle > 180 && getY() - radius <= 0)
        {
            sound(hitWall);
            setRotation(-angle);
        }
        // Bounce of the bottom wall
        else if(angle < 180 && getY() + radius >= getWorld().getHeight() - 1)
        {
            sound(hitWall);
            setRotation(-angle);   
        }
                
        move(speed);
        
        // Store the rotation
        trueRotation = getRotation();
        // Set the actor's rotation to 0 so the image doesn't look rotated
        setRotation(0);
    }
    
    private void sound(GreenfootSound s)
    {
        if(s.isPlaying())
            s.stop();
        s.play();
    }
}