import greenfoot.*;
import java.awt.Color;
import java.math.*;

public class Pong extends World
{
    // The scores of each player
    private int score1 = 0;
    private int score2 = 0;
    
    public Pong()
    {    
        super(1000, 600, 1);
        
        // Make the background gray
        GreenfootImage bg = getBackground();
        bg.setColor(Color.darkGray);
        bg.fill();
        
        addObject(new Paddle(4, "w", "s"), 0, getHeight() / 2);
        addObject(new Paddle(4, "8", "5"), getWidth(), getHeight() / 2);
        spawnBall(Greenfoot.getRandomNumber(2) == 0);
    }
    
    public void act()
    {
        String padding = "          ";
        showText(score1 + padding + score2, getWidth() / 2, getHeight() / 10); 
    }
    
    public void goal(int player)
    {
        removeObjects(getObjects(Ball.class));
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
    }
    
    public void spawnBall(boolean go_left)
    {
        int angle = 0;
        
        if(go_left)
            angle = 180;
        
        addObject(new Ball(4, angle), getWidth() / 2, getHeight() / 2);
    }
}
