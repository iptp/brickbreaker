import greenfoot.World;

public abstract class Level  
{
    protected BrickBreaker game;
    protected final int max_cols;
    protected final int max_rows;
    
    public Level(BrickBreaker game, int cols, int rows)
    {
        this.game = game;
        max_cols = cols;
        max_rows = rows;
    }
    
    public abstract void create();
    
    protected final void addBrick(Brick b, int cellX, int cellY)
    {
        int brickX = cellX * Brick.GRID_WIDTH;
        int brickY = cellY * Brick.GRID_HEIGHT;
        
        game.addObject(b, brickX, brickY);
    }
}
