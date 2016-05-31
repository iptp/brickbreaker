import java.awt.Color;

public class ComplicatedLevel extends Level 
{
    private Color[] brick_colors  = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private Color[] sturdy_colors = {Color.ORANGE, Color.CYAN, Color.MAGENTA};
    private Color   powerup_color = Color.WHITE;
    
    // The four colors, (S)turdy, (M)etal, and (P)owerup
    private String symbols = "0123SMP";
    
    // TODO better level
    private String[] level = 
    {
        "---------------",
        "---------------",
        "---------------",
        "--0SSSSSSSSS0--",
        "--1PPPPPPPPP1--",
        "--2MMMMMMMMM2--",
        "--33333333333--",
        "--00000000000--",
        "--11111111111--",
        "--22222222222--",
        "--33333333333--",
        "--00000000000--",
        "--11111111111--",
        "--22222222222--",
        "--33333333333--"
    };
    
    public void create()
    {
        for(int i = 0; i < level.length; i++)
        {
            for(int j = 0; j < level[i].length(); j++)
            {
                char ch = level[i].charAt(j);
                // If the character is a valid symbol
                if(symbols.indexOf(ch) != -1)
                {
                    Brick brick = null;
                    if(ch == 'M')
                        brick = new MetalBrick();
                    else if(ch == 'S')
                        brick = new SturdyBrick(sturdy_colors.length, sturdy_colors);
                    else if(ch == 'P')
                        brick = new PowerUpBrick(powerup_color);
                    else
                    { 
                        int color_index = ch - '0';
                        brick = new Brick(brick_colors[color_index]);
                    }
                    
                    // The order here is (j, i). This is not a typo
                    addBrick(brick, j, i);
                }
            }
        }
    }
}