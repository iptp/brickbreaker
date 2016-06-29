import greenfoot.*;
import java.util.List;

public class Ball extends Actor
{
    private int radius = 15;
    private int speed = 4;
    private int trueRotation = 0;
    
    private int max_speed = 15;
    private int pow_speed = 20;
    
    // Sound effects
    private GreenfootSound hitPaddle = new GreenfootSound("tone1.mp3");
    private GreenfootSound hitWall   = new GreenfootSound("twoTone2.mp3");
    private GreenfootSound score     = new GreenfootSound("threeTone1.mp3");
    
    GreenfootImage powerBall = new GreenfootImage("ballPurple.png");
    
    public Ball(int angle)
    {
        hitPaddle.setVolume(100);
        hitWall.setVolume(100);
        score.setVolume(100);
        
        trueRotation = angle;
    }
    
    public void addedToWorld(World w)
    {
        getImage().scale(radius * 2, radius * 2);
        powerBall.scale(radius * 2, radius * 2);
    }

    public void act() 
    {
        // Retrieve the stored rotation
        setRotation(trueRotation);
        
        int angle = getRotation();
        Pong pong = (Pong) getWorld();
        Paddle paddle = (Paddle) getOneIntersectingObject(Paddle.class);
        
        // Bounce off the paddles
        if(paddle != null)
        {
            sound(hitPaddle);

            // Become faster, if not already too fast
            if(speed < 15)
                speed++;
                
            // Aim away from the center of the paddle
            paddle.bounceAway(this);
            
            // We don't wan the new angle to be too vertical, so we cap it
            // If the ball the ball bounces nearlyz vertically, we want it to be powered up
            angle = getRotation();
            int cap = 25;
            int pow = 10;
            
            // There are four directions we have to take into account
            if(90 - cap < angle && angle <= 90) // Down-right |\
                angle = 90 - cap;
            else if(90 < angle && angle < 90 + cap) // Down-left /|
                angle = 90 + cap;
            else if(270 - cap < angle && angle <= 270) // Up-left \|
                angle = 270 - cap;
            else if(270 < angle && angle < 270 + cap) // Up-right |/
                angle = 270 + cap;
            // Ball power-up
            else if(angle < pow || angle >= 360 - pow || 180 - pow < angle && angle <= 180 + pow)
            {
                setImage(powerBall);
                speed = 20;
            }
            
            setRotation(angle);
        }
        // Touching left wall, player 2 scores
        else if(getX() - radius <= 0)
        {
            sound(score);
            pong.goal(2);
            getWorld().removeObject(this);
            return;
        }
        // Touching right wall, player 1 scores
        else if(getX() + radius >= getWorld().getWidth() - 1)
        {
            sound(score);
            pong.goal(1);
            getWorld().removeObject(this);
            return;
        }
        // Bounce off the top or bottom walls
        else if(
            (angle > 180 && getY() - radius <= 0) ||
            (angle < 180 && getY() + radius >= getWorld().getHeight() - 1)
        )
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
        s.stop(); // in case it is already playing
        s.play();
    }
}