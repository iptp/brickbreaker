import greenfoot.*;
import java.awt.Color;

public class SturdyBrick extends Brick
{
    int health;
    GreenfootImage[] images;
    
    public SturdyBrick()
    {
        this(3, new Color[]{Color.ORANGE, Color.CYAN, Color.MAGENTA});
    }
    
    public SturdyBrick(int health, Color[] colors)
    {
        this.health = health;
        images = new GreenfootImage[4];
        for(int i = 0; i < health; i++)
        {
            images[i] = paintBrick(colors[i]);
        }
        setImage(images[health - 1]);
    }
    
    @Override
    public void collideWith(Ball ball)
    {
        health--;
        if(health == 0)
            die();
        else
            setImage(images[health - 1]);
    }
}