public abstract class Level  
{
    protected BrickBreaker game;
    
    public Level()
    {
        game = null;
    }
    
    public void setGame(BrickBreaker game)
    {
        this.game = game;
    }
    
    public abstract void create();
    
    protected final void addBrick(Brick b, int cellX, int cellY)
    {
        int brickX = cellX * Brick.GRID_WIDTH;
        int brickY = cellY * Brick.GRID_HEIGHT;
        
        game.addObject(b, brickX, brickY);
    }
}