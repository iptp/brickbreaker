import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Brick extends Actor
{       
    public Brick()
    {
        // Does nothing
    }
    
    public Brick(Level level, Color color)
    {
        setColor(color);
    }
    
    @Override
    public void addedToWorld(World world)
    {
        alignToGrid();
    }
    
    @Override
    public void act()
    {
        alignToGrid();
    }
    
    public boolean isBreakable() { return true; }
    
    public void setColor(Color color)
    {
        // Create an image the same size as the brick,
        GreenfootImage aux = new GreenfootImage(getWidth(), getHeight());
        // fill it with the desired color,
        aux.setColor(color);
        aux.fill();
        // make it a bit transparent,
        aux.setTransparency(155);
        // and finally paint it over the brick image
        getImage().drawImage(aux, 0, 0);
    }
    
    public void collideWith(Ball ball)
    {
        getWorld().removeObject(this);
    }
    
    private void alignToGrid()
    {
        // The location where we want the top-left corner of the brick
        int cornerX = round(getX(), getWidth());
        int cornerY = round(getY(), getHeight());
        
        // We have to pass the location of the center, not the corner
        // So, we add half of the brick's dimensions
        setLocation(cornerX + getWidth() / 2, cornerY + getHeight() / 2);
    }
    
    private int round(int num, int to)
    {
        return (num / to) * to;
    }
    
    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
}
