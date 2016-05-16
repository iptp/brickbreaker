import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Brick extends Actor
{
    public static final int width  = 60;
    public static final int height = 30;
     
    public Brick()
    {
        // Create a red brick by default
        this("red");
    }
    
    public Brick(String color)
    {
        setColor(color);
    }
    
    public void addedToWorld(World world)
    {
        alignToGrid();
    }
    
    public void act()
    {
        alignToGrid();
    }
    
    public void setColor(String color)
    {
        setImage(new GreenfootImage("brick_" + color + ".png"));
    }
    
    private void alignToGrid()
    {
        // The location where we want the top-left corner of the brick
        int cornerX = round(getX(), width);
        int cornerY = round(getY(), height);
        
        // We have to pass the location of the center, not the corner
        // So, we add half of the brick's dimensions
        setLocation(cornerX + width / 2, cornerY + height / 2);
    }
    
    private int round(int num, int to)
    {
        return (num / to) * to;
    }
}
