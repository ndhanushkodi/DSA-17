/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves;
        public int cost;
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO: Compute cost of board state according to A*
            cost = this.moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        /*
         * Return the cost difference between two states
         */
        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        State current = state;
        while (current.prev != null) {
            current = current.prev;
        }
        return current;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {

        Set<State> open = new HashSet<>();
        Set<State> closed = new HashSet<>();
        if (initial == null) throw new IllegalArgumentException("Initial board state was null");
        if(!initial.solvable()) return; // unsolvable
        // Create the initial "START" state and insert on open set
        State initBoard = new State(initial, 0, null);
        open.add(initBoard);

        while(!open.isEmpty()){
            // Get node from open set with lowest cost and remove from set
            State current = Collections.min(open);
            open.remove(current);

            //If this is solution, update things accordingly
            if(current.board.isGoal()){
                State root = root(current);
                if(!root.board.equals((initial))) break;
                solved = true;
                minMoves = current.moves;
                solutionState = current;
                break;
            }

            Iterable<Board> neighbors = current.board.neighbors();
            for(Board neighbor : neighbors){
                State neighborState = new State(neighbor, current.moves+1, current);
                //add neighborState to open only if it's board is not already in the open
                //or closed sets. if its already there, add it to openset ONLY if the cost is
                //less than what it was
                State openState = find(open, neighbor);
                State closedState = find(closed, neighbor);
                if (openState != null && neighborState.cost > openState.cost) continue;
                if (closedState != null && neighborState.cost > closedState.cost) continue;

                //passed through those conditions, add it to the open set
                open.add(neighborState);
            }
            closed.add(current);
        }
    }


    /*
     * Is the input board a solvable state
     */
    public boolean isSolvable() {
    	return solved;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        if (isSolvable()) {
            Stack<Board> sol = new Stack<>();
            State current = solutionState;
            while (current != null) {
                sol.push(current.board);
                current = current.prev;
            }
            return sol;
        }
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space: Your solution can have whatever output you find useful
     * Optional challenge: create a command line interaction for users to input game
     * states
     */
    public static void main(String[] args) {
    //int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
        int[][] initState = {{1, 2, 3}, {0, 4, 5}, {8, 6, 7}};
        Board initial = new Board(initState);

        // Solve the puzzle
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
            System.out.println("No solution");
        else {
            for (Board board : solver.solution()) {
                board.printBoard();
            }
            System.out.println(solver.minMoves);
        }
    }


}
