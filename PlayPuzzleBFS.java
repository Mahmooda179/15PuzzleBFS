import java.util.*;

public class PlayPuzzleBFS {

    // goal state that want
    public static final int[][] goal_state = {{1, 2, 3, 4},
	  {5, 6, 7, 8},
	  {9, 10, 11, 12},
	  {13, 14, 15, 0}};
      public static void main(String[] args)
      {
        // grabs time before program is run
        long start_time = System.currentTimeMillis();
        // grabs memory before program is run
        long before_mem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        // get input for board
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter puzzle board: ");

        // read user input
        String start = user_input.nextLine();

        // convert user input string into 2D int array
        String[] start_array = start.split(" ");
        
        int[][] start_state = new int[4][4];
        int index = 0;

        int x_pos = -1;
        int y_pos = -1;
        
        // converts string array to 2d int array
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(index > 15)
                { break; }

                int num = Integer.parseInt(start_array[index]);
                if (num == 0)
                {
                    x_pos = i;
                    y_pos = j;
                }
                start_state[i][j] = num;

                index++;
            }
        }

        // create board object for start state
        boolean n_u;
        boolean n_d; 
        boolean n_l;
        boolean n_r;

        if(x_pos == 0)
        {n_u = false;}
        else {n_u = true;}

        if(x_pos == 3)
        {n_d = false;}
        else {n_d = true;}

        if(y_pos == 0)
        {n_l = false;}
        else {n_l = true;}

        if(y_pos == 3)
        {n_r = false;}
        else {n_r = true;}

        Board start_board = new Board(start_state, n_u, n_d, n_l, n_r, x_pos, y_pos);

        // retrieves hashset of all visited nodes from bfs algorithms
        HashSet<BFS.Node> set = BFS.bfs_algorithm(start_board, goal_state);

        int[][] s = goal_state;
        StringBuilder moves = new StringBuilder();

        // back tracks through hashset to find all the moves made to reach goal state and adds the previous move to moves StringBuilder
        do {

            for(BFS.Node n: set)
            {
                int[][] new_s = n.get_board().get_state();
                int[][] n_s = new int[4][4];

                // if current state being checked is found in HashSet
                if (Arrays.deepEquals(s, new_s))
                {
                    moves.append(n.get_prev_move());
                    int x = n.get_board().get_x_pos();
                    int y = n.get_board().get_y_pos();

                    // goes down if space moved up to get to current state to get the previous state
                    if (n.get_prev_move().equals("U"))
                    {
                        int new_x = x + 1;
                        int old_num = s[new_x][y];

                        n_s[new_x][y] = 0;
                        n_s[x][y] = old_num;

                        for (int i=0; i<4; i++) {
                            for(int j=0; j<4; j++) {
                                if((i==x && j==y) || (i==new_x && j==y))
                                {
                                    continue;
                                }

                                n_s[i][j] = s[i][j];
                            }
                        }
                    }

                    // goes up if space moved down to get to current state to get the previous state
                    else if (n.get_prev_move().equals("D"))
                    {
                        int new_x = x - 1;
                        int old_num = s[new_x][y];

                        n_s[new_x][y] = 0;
                        n_s[x][y] = old_num;

                        for (int i=0; i<4; i++) {
                            for(int j=0; j<4; j++) {
                                if((i==x && j==y) || (i==new_x && j==y))
                                {
                                    continue;
                                }

                                n_s[i][j] = s[i][j];
                            }
                        }

                    }

                    // goes right if space moved left to get to current state to get the previous state
                    else if (n.get_prev_move().equals("L"))
                    {

                        int new_y = y + 1;
                        int old_num = s[x][new_y];

                        n_s[x][new_y] = 0;
                        n_s[x][y] = old_num;

                        for (int i=0; i<4; i++) {
                            for(int j=0; j<4; j++) {
                                if((i==x && j==y) || (i==x && j==new_y))
                                {
                                    continue;
                                }

                                n_s[i][j] = s[i][j];
                            }
                        }

                    }

                    // goes left if space moved right to get to current state to get the previous state
                    else if (n.get_prev_move().equals("R"))
                    {
                        int new_y = y - 1;
                        int old_num = s[x][new_y];

                        n_s[x][new_y] = 0;
                        n_s[x][y] = old_num;

                        for (int i=0; i<4; i++) {
                            for(int j=0; j<4; j++) {
                                if((i==x && j==y) || (i==x && j==new_y))
                                {
                                    continue;
                                }

                                n_s[i][j] = s[i][j];
                            }
                        }

                    }

                    // assigns n_s (new_ tate) to s (current state) and breaks out of loop
                    s = n_s;
                    break;
                }
            }

        } while(!(Arrays.deepEquals(s, start_state)));

        // reverses StringBuilder to get moves from beginning to end and converts StringBuilder to String
        String m = moves.reverse().toString();

        System.out.println("Moves made: " + m);
        
        // prints out HashSet size to get nodes expanded
        System.out.println("Number of Nodes expanded: " + set.size());

        // calculate end time of program to determine time elapses
        long end_time = System.currentTimeMillis();
        double elapsed_time = (end_time - start_time) / 1000.00;

        System.out.println("Time taken: " + elapsed_time + " seconds");

        // calculate current memory to get memory used by program 
        long after_mem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long memory = after_mem - before_mem;

        System.out.println("Memory Used: " + memory/1000.00 + " KB");
          
      }

	
}
