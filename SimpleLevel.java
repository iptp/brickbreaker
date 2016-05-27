import greenfoot.*;
import java.awt.Color;

public class SimpleLevel extends Level 
{
    private int gap_sides  = 2;
    private int gap_top    = 3;
    private int gap_bottom = 5;
    
    public SimpleLevel(BrickBreaker world, int cols, int rows)
    {
        super(world, cols, rows);
    }
    
    public void create()
    {
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
        
        // How many rows and columns of bricks will be created
        int num_cols = max_cols - 2 * gap_sides;
        int num_rows = max_rows - gap_top - gap_bottom;
        
        // x, y represent the position of the Brick on the brick grid
        for(int x = 0; x < num_cols; x++)
        {
            for(int y = 0; y < num_rows; y++)
            {
                int aux = y % 4; // We use the height to determine the color
                addBrick(new Brick(this, colors[aux]), x + gap_sides, y + gap_top);
            }
        }
    }
    
}