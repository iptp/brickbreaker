import greenfoot.*;
import java.awt.Color;
import java.math.*;

public class Pong extends World
{
    // The scores of each player
    private int score1 = 0;
    private int score2 = 0;
    private int ballCount = 0;
    
    public Pong()
    {    
        super(1000, 600, 1);
        
        // Make the background gray
        GreenfootImage bg = getBackground();
        bg.setColor(Color.darkGray);
        bg.fill();
        
        addObject(new Paddle(6, "w", "s"), 0, getHeight() / 2);
        addObject(new Paddle(6, "7", "1"), getWidth(), getHeight() / 2);
        //addObject(new Paddle(4, "t", "g"), 0, getHeight() / 2);
        //addObject(new Paddle(4, "9", "3"), getWidth(), getHeight() / 2);
        
        // Spawn a ball to a random direction (left or right) with 50% chance each
        //for(int i = 0; i < 2; i++)
        //{
        //if(Greenfoot.getRandomNumber(2) == 0)
            spawnBall(true);
        //else
            spawnBall(false);
        //}
    }
    
    public void act()
    {
        String padding = "          ";
        showText(score1 + padding + score2, getWidth() / 2, getHeight() / 10); 
    }
    
    // Remove all ball's, increment the player's score and spawn a new ball towards the opposite player
    public void goal(int player)
    {
        ballCount--;
        //removeObjects(getObjects(Ball.class));
        if(player == 1)
        {
            score1++;
            spawnBall(false);
        }
        else if(player == 2)
        {
            score2++;
            spawnBall(true);
        }
        
        if((score1 + score2) % 3 == 0 && ballCount < 5)
            spawnBall(player == 1);
    }
    
    // Spawn a ball from the center of the screen, to the right or to the left
    public void spawnBall(boolean go_left)
    {
        int angle;
        ballCount++;
        
        if(go_left)
            angle = 0;
        else
            angle = 180;
            
        angle += Greenfoot.getRandomNumber(91) - 45;
        
        addObject(new Ball(angle), getWidth() / 2, getHeight() / 2);
        //addObject(new Ball(angle+180), getWidth() / 2, getHeight() / 2);
    }
}
