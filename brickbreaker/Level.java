import greenfoot.World;

public abstract class Level  
{
    // Classes that extend Level may also access protected members
    protected GameScreen gs;
    protected final int max_cols;
    protected final int max_rows;
    
    private int brick_count;
    
    public Level(GameScreen gs, int cols, int rows)
    {
        this.gs = gs;
        max_cols = cols;
        max_rows = rows;
    }
    
    public abstract void create();
    
    protected final void addBrick(Brick b, int x, int y)
    {
        int actual_x = x * b.getWidth()  + b.getWidth()  / 2;
        int actual_y = y * b.getHeight() + b.getHeight() / 2;
        
        gs.addObject(b, actual_x, actual_y);
        
        if(b.isBreakable())
            brick_count++;
    }
    
    protected final void destroyBrick(Brick b)
    {
        gs.removeObject(b);
        brick_count--;
        
        if(brick_count == 0)
        {
            // gs.screenCleared() 
        }
    }
}
