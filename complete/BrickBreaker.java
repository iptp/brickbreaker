import greenfoot.*;
import java.awt.Color;
import java.util.List;

public class BrickBreaker extends World
{   
    // Dimensions of the game screen
    public static final int cols = 15;
    public static final int rows = 20;
    public static final int width  = cols * 60; // <cols> columns of 60-pixel wide blocks
    public static final int height = rows * 30; // <rows> rows of 30-pixel wide blocks

    // Indicates if the game has ended
    private boolean game_over;
    // If this reaches zero, loss
    private int lives;
    // How many balls there are flying on-screen
    // The ball that follows the paddle does not count
    // If this reaches zero, decrement lives and create a new ball on the paddle
    private int balls; 
    // How many breakable bricks we have
    // If this reaches zero, win
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
        showInterface();
        if(game_over && Greenfoot.isKeyDown("space"))
        {
            eraseGameOverText();
            reset();   
        }
    }
 
    public void reset()
    {
        reset(new SimpleLevel());
    }
    
    public void reset(Level new_level)
    {
        game_over = false;
        lives = 3;
        balls = 0;
        bricks = 0;
        
        List actors = getObjects(null);

        if(actors.size() > 0)
            removeObjects(actors);

        level = new_level;
        level.setGame(this);
        level.create();
        
        paddle = new Paddle();

        addObject(paddle, width / 2, height - Paddle.DEFAULT_HEIGHT / 2);
    }
    
    public void showInterface()
    {
        String[] numbers = {"Zero!", "One!", "Two!", "Three!", "Four!", "Five!"};
        String text = "Lives: ";
        
        if(lives > 0)
            text += "" + lives;
        else
            text += "Last one!";
        
        text += "\nBricks: ";
        if(bricks > 5)
            text += "" + bricks;
        else
            text += numbers[bricks];
        showText(text, 120, 40);
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
            if(balls == 0 && paddle.outOfStock())
            {
                if(lives == 0)
                    gameOver(false);
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
                gameOver(true);
        }
    }
    
    public void gameOver(boolean won)
    {
        game_over = true;
        String text;
        if(won)
            text = "Cleared!\nPress space to restart";
        else
            text = "Game Over!\nPress space to restart";
        showText(text, getWidth() / 2, 40);
    }

    public void eraseGameOverText()
    {
        showText("", getWidth() / 2, 40);
    }
}
