import java.util.*;

public class BFS {
    // node class that contains Board object, node children and the previous move made to reach this class
    public static class Node<T> {
        private Board board;
        private List<Node<T>> children;
        private String prev_move;

        // Node constructor
        public Node (Board board, String prev_move)
        {
            this.board = board;
            this.prev_move = prev_move;
            this.children = new ArrayList<Node<T>>();
        }

        // getter methods
        public Board get_board()
        {
            return this.board;
        }

        public List<Node<T>> get_children()
        {
            return this.children;
        }

        public String get_prev_move()
        {
            return this.prev_move;
        }

        // adds all the node's children to children list
        public void add_children(Board board)
        {
            boolean up = board.get_up();
            boolean down = board.get_down();
            boolean left = board.get_left();
            boolean right = board.get_right();

            boolean n_u;
            boolean n_d; 
            boolean n_l;
            boolean n_r;

            // creates child for current board ONLY IF space can move up
            if(up) {
                int[][] s = board.get_up_state();

                int x = board.get_x_pos() - 1;
                int y = board.get_y_pos();

                if(x == 0)
                {n_u = false;}
                else {n_u = true;}

                if(x == 3)
                {n_d = false;}
                else {n_d = true;}

                if(y == 0)
                {n_l = false;}
                else {n_l = true;}

                if(y == 3)
                {n_r = false;}
                else {n_r = true;}

                Board up_board = new Board(s, n_u, n_d, n_l, n_r, x, y);

                Node up_child = new Node(up_board, "U");

                children.add(up_child);  
            }

            // creates child for current board ONLY IF space can move down
            if(down) {
                int[][] s = board.get_down_state();

                int x = board.get_x_pos() + 1;
                int y = board.get_y_pos();

                if(x == 0)
                {n_u = false;}
                else {n_u = true;}

                if(x == 3)
                {n_d = false;}
                else {n_d = true;}

                if(y == 0)
                {n_l = false;}
                else {n_l = true;}

                if(y == 3)
                {n_r = false;}
                else {n_r = true;}

                Board down_board = new Board(s, n_u, n_d, n_l, n_r, x, y);
                Node down_child = new Node(down_board, "D");

                children.add(down_child);  
            }

            // creates child for current board ONLY IF space can move left
            if(left) {
                int[][] s = board.get_left_state();

                int x = board.get_x_pos();
                int y = board.get_y_pos() - 1;

                if(x == 0)
                {n_u = false;}
                else {n_u = true;}

                if(x == 3)
                {n_d = false;}
                else {n_d = true;}

                if(y == 0)
                {n_l = false;}
                else {n_l = true;}

                if(y == 3)
                {n_r = false;}
                else {n_r = true;}

                Board left_board = new Board(s, n_u, n_d, n_l, n_r, x, y);

                Node left_child = new Node(left_board, "L");

                children.add(left_child);  
            }

            // creates child for current board ONLY IF space can move right
            if(right) {
                int[][] s = board.get_right_state();

                int x = board.get_x_pos();
                int y = board.get_y_pos() + 1;

                if(x == 0)
                {n_u = false;}
                else {n_u = true;}

                if(x == 3)
                {n_d = false;}
                else {n_d = true;}

                if(y == 0)
                {n_l = false;}
                else {n_l = true;}

                if(y == 3)
                {n_r = false;}
                else {n_r = true;}

                Board right_board = new Board(s, n_u, n_d, n_l, n_r, x, y);

                Node right_child = new Node(right_board, "R");

                children.add(right_child);  
            }
        }
    }

    // bfs algorithm
    public static HashSet<Node> bfs_algorithm(Board start_board, int[][] goal_state)
    {   
        
        Queue<Node> queue = new ArrayDeque<>();             // queue to go through all nodes
        HashMap<int[][], String> set = new HashMap<>();     // HashMap that contains all board states already visited and move made to get to that state
        HashSet<Node> visited = new HashSet<>();            // HashSet the contains all visited nodes

        // creates root node for start state and adds root to queue
        Node root = new Node(start_board, "Root");
        queue.add(root);

        
        while (!(queue.isEmpty()))
        {
            // pops off first node from queue
            Node current = queue.remove();

            // gets the board object from current node and adds its children to children array
            Board current_board = current.get_board();
            current.add_children(current_board);

            // gets the board state from board object
            int[][] current_state = current_board.get_state();

            // boolean variable to check if board was already visited
            boolean isVisit = false;

            // iterates through HashMAp
            for (Map.Entry element: set.entrySet())
            {   
                // gets key (board state) from hashmap
                int[][] key = (int[][]) element.getKey();
                // if key is the same as the current_state being checked marks isVisit to true and breaks out of loop
                if (Arrays.deepEquals(key, current_state))
                {
                    isVisit = true; 
                    break;
                }
            }

            // if board has not been visited yet
            if(!isVisit) 
            {
                // adds board to hashmap and hashset
                set.put(current_state, current.get_prev_move());
                visited.add(current);
                
                // gets boards children and adds it to the queue
                List<Node> children = current.get_children();

                for(Node child: children)
                {
                    queue.add(child);
                }
            }

            // if current board is equal to the end state, adds state to hashset and hashmap and returns hashset
            if (Arrays.deepEquals(current_state, goal_state))
            {
                set.put(current_state, current.get_prev_move());
                visited.add(current);
                return visited;
            }

        }

        // returns completed hashset (assumes that final state was found all the way at the end)
        return visited;
    }
}