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
    
    private Level  level;
    
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
        List actors = getObjects(null);
        
        if(actors.size() > 0)
            removeObjects(actors);
                
        level = new SimpleLevel(this, cols, rows);
        level.create();
        
        addObject(new Paddle(), width / 2, height - Paddle.DEF_HEIGHT / 2);
    }
    

}
