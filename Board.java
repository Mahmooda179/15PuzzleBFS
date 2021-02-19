import java.util.*;

public class Board {

    private int[][] state;  // state that board looks like
    private boolean up;     // check to see if space can move up
    private boolean down;   // check to see if space can move down
    private boolean left;   // check to see if space can move left
    private boolean right;  // check to see if space can move right
    private int x_pos;      // x position of space
    private int y_pos;      // y position of space

    // board constructor
    public Board(int[][] state, boolean up, boolean down, boolean left, boolean right, int x_pos, int y_pos)
    {
        this.state = state;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    // setter methods
    public void set_state(int[][] n_state)
    {
        this.state = n_state;
    }

    public void set_up(boolean n_up)
    {
        this.up = n_up;
    }

    public void set_down(boolean n_down)
    {
        this.down = n_down;
    }

    public void set_left(boolean n_left)
    {
        this.left = n_left;
    }

    public void set_right(boolean n_right)
    {
        this.right = n_right;
    }

    public void set_x_pos(int n_x_pos)
    {
        this.x_pos = n_x_pos;
    }

    public void set_y_pos(int n_y_pos)
    {
        this.y_pos = n_y_pos;
    }


    // getter methods
    public int[][] get_state()
    {
        return this.state;
    }

    public boolean get_up()
    {
        return this.up;
    }

    public boolean get_down()
    {
        return this.down;
    }

    public boolean get_left()
    {
        return this.left;
    }

    public boolean get_right()
    {
        return this.right;
    }

    public int get_x_pos()
    {
        return this.x_pos;
    }

    public int get_y_pos()
    {
        return this.y_pos;
    }

    // gets state of board if space moved up
    public int[][] get_up_state()
    {
        int[][] up_state = new int [4][4];

        int new_x = this.x_pos -1;
        int old_num = this.state[new_x][this.y_pos];

        up_state[new_x][this.y_pos] = 0;
        up_state[this.x_pos][this.y_pos] = old_num;

        for (int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if((i==this.x_pos && j==this.y_pos) || (i==new_x && j==this.y_pos))
                {
                    continue;
                }

                up_state[i][j] = this.state[i][j];
            }
        }

        return up_state;
    }

    // gets state of board is space moved down
    public int[][] get_down_state()
    {
        int[][] down_state = new int [4][4];

        int new_x = this.x_pos + 1;
        int old_num = this.state[new_x][this.y_pos];

        down_state[new_x][this.y_pos] = 0;
        down_state[this.x_pos][this.y_pos] = old_num;

        for (int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if((i==this.x_pos && j==this.y_pos) || (i==new_x && j==this.y_pos))
                {
                    continue;
                }

                down_state[i][j] = this.state[i][j];
            }
        }

        return down_state;
    }

    // gets state of board of space moved left
    public int[][] get_left_state()
    {
        int[][] left_state = new int [4][4];

        int new_y = this.y_pos - 1;
        int old_num = this.state[this.x_pos][new_y];

        left_state[this.x_pos][new_y] = 0;
        left_state[this.x_pos][this.y_pos] = old_num;

        for (int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if((i==this.x_pos && j==this.y_pos) || (i==this.x_pos && j==new_y))
                {
                    continue;
                }

                left_state[i][j] = this.state[i][j];
            }
        }

        return left_state;
    }

    // gets state of board if space moved right
    public int[][] get_right_state()
    {
        int[][] right_state = new int [4][4];

        int new_y = this.y_pos + 1;
        int old_num = this.state[this.x_pos][new_y];

        right_state[this.x_pos][new_y] = 0;
        right_state[this.x_pos][this.y_pos] = old_num;

        for (int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if((i==this.x_pos && j==this.y_pos) || (i==this.x_pos && j==new_y))
                {
                    continue;
                }

                right_state[i][j] = this.state[i][j];
            }
        }

        return right_state;
    }
}
