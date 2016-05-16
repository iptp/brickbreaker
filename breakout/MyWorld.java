import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public static final int width  = 900;
    public static final int height = 600;
    
    public MyWorld()
    {    
        super(width, height, 1); 
        prepare();
    }

    private void prepare()
    {
        addObject(new Paddle(), width/2, height - Paddle.height/2);
    }
}
