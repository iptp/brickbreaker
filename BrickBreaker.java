import greenfoot.*;
import java.awt.Color;
import java.util.List;

public class BrickBreaker extends World
{   
    // Dimensions of the game screen
    private static int cols = 15;
    private static int rows = 20;
    private static int width  = cols * 60; // <cols> columns of 60-pixel wide blocks
    private static int height = rows * 30; // <rows> rows of 30-pixel wide blocks

    private int lives;
    private int balls;
    private int bricks;
    
    private Level level;
    private Paddle paddle;

    public BrickBreaker()
    {
        super(width, height, 1);
        reset();
    }

    @Override
    public void act()
    {

    }
 
    public void reset()
    {
        lives = 3;
        balls = 0;
        bricks = 0;
        
        List actors = getObjects(null);

        if(actors.size() > 0)
            removeObjects(actors);

        level = new SimpleLevel(this, cols, rows);
        level.create();
        
        paddle = new Paddle();

        addObject(paddle, width / 2, height - Paddle.DEFAULT_HEIGHT / 2);
    }
    
    public void ballCreated()
    {
        balls++;
    }
    
    public void ballRemoved()
    {
        if(balls > 0)
        {
            balls--;
            if(balls == 0)
            {
                if(lives == 0)
                    lose();
                else
                {
                    lives--;
                    paddle.spawnBall();
                }
            }
        }
    }
    
    public void brickCreated()
    {
        bricks++;
    }
    
    public void brickRemoved()
    {
        if(bricks > 0)
        {
            bricks--;
            if(bricks == 0)
                win();
        }
    }
    
    public void win()
    { 
        showText(":)", 60, 60);
    }
    
    public void lose()
    {
        showText(":(", 60, 60);
    }
}
