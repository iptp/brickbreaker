import greenfoot.*;
import java.awt.Color;

public class Brick extends MyActor
{
    public static final int GRID_WIDTH  = 60;
    public static final int GRID_HEIGHT = 30;
    protected static final GreenfootImage DEFAULT_IMAGE = new GreenfootImage("brick.png");
    
    public Brick()
    {
        // Does nothing
    }
    
    public Brick(Color color)
    {
        setImage(paintBrick(color));
    }
    
    @Override
    public void addedToWorld(World world)
    {
        alignToGrid();
        if(isBreakable())
            getGame().brickCreated();
    }
    
    @Override
    public void act()
    {
        alignToGrid();
    }
    
    public boolean isBreakable()
    { 
        return true;
    }
    
    public GreenfootImage paintBrick(Color color)
    {
        // Make a copy of the default image
        GreenfootImage img = new GreenfootImage(DEFAULT_IMAGE);
        // Create an image the same size as the brick
        GreenfootImage aux = new GreenfootImage(img.getWidth(), img.getHeight());
        // Fill it with the desired color
        aux.setColor(color);
        aux.fill();
        // Make it a bit transparent
        aux.setTransparency(165);
        // Paint it over the brick's image
        img.drawImage(aux, 0, 0);
        return img;
    }
    
    public void collideWith(Ball ball)
    {
        die();
    }
    
    public void die()
    {
        if(isBreakable())
            getGame().brickRemoved();
        getWorld().removeObject(this);
    }
    
    public void alignToGrid()
    {
        // What grid cell are we in?
        int cellX = getX() / GRID_WIDTH;
        int cellY = getY() / GRID_HEIGHT;
        
        // Position of the center of the cell
        int centerX = cellX * GRID_WIDTH  + GRID_WIDTH / 2;
        int centerY = cellY * GRID_HEIGHT + GRID_HEIGHT / 2;
        
        setLocation(centerX, centerY);
    }
}