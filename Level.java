import greenfoot.World;

public abstract class Level  
{
    // Classes that extend Level may also access protected members
    protected BrickBreaker world;
    protected final int max_cols;
    protected final int max_rows;
    
    private int brick_count;
    
    public Level(BrickBreaker w, int cols, int rows)
    {
        world = w;
        max_cols = cols;
        max_rows = rows;
    }
    
    public abstract void create();
    
    protected final void addBrick(Brick b, int x, int y)
    {
        int actual_x = x * b.getWidth()  + b.getWidth()  / 2;
        int actual_y = y * b.getHeight() + b.getHeight() / 2;
        
        world.addObject(b, actual_x, actual_y);
        
        if(b.isBreakable())
            brick_count++;
    }
    
    protected final void destroyBrick(Brick b)
    {
        world.removeObject(b);
        brick_count--;
        
        if(brick_count == 0)
        {
            // victory() 
        }
    }
}
