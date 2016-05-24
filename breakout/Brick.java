import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class Brick extends Actor
{   
    public Brick(Color color)
    {
        setColor(color);
    }
    
    public Brick()
    {
        // Create a red brick by default
        this(Color.RED);
    }
    
    public void setColor(Color color)
    {
        // TODO explain
        GreenfootImage aux = new GreenfootImage(getWidth(), getHeight());
        aux.setColor(color);
        aux.fill();
        aux.setTransparency(155);
        getImage().drawImage(aux, 0, 0);
    }
    
    public void addedToWorld(World world)
    {
        alignToGrid();
    }
    
    public void act()
    {
        alignToGrid();
    }
    
    public static void addBrick(World world, Color color, int x, int y)
    {
        Brick b = new Brick(color);
        
        // These are arbitrary
        int offset_x = 2;
        int offset_y = 4;
        
        int actual_x = x * b.getWidth()  + b.getWidth()  / 2;
        int actual_y = y * b.getHeight() + b.getHeight() / 2;
        
        world.addObject(b, actual_x, actual_y);
    }
    
    // We want to use the image's dimensions for collision and aligment
    // If we have bricks with different sizes, grid aligment will look funny
    public int getHeight() { return getImage().getHeight(); }
    public int getWidth()  { return getImage().getWidth();  }
    
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
        // Example: round(34, 10)
        // 34 / 10 = 3
        // 3 * 10  = 30
    }
}
