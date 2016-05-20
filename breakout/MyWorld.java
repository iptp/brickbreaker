import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class MyWorld extends World
{
    public static final int width  = 900;
    public static final int height = 600;
    
    public MyWorld()
    {    
        super(width, height, 1); 
        addObject(new Paddle(), width / 2, height - Paddle.height / 2);
        createLevel();
    }
    
    private void createLevel()
    {
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };
        for(int x = 2; x < 13; x++)
        {
            for(int y = 3; y < 13; y++)
            {
                int aux = Greenfoot.getRandomNumber(3);
                Brick.addBrick(this, colors[aux], x, y);
            }
        }
        
    }
}
