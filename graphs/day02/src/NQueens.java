import java.util.LinkedList;
import java.util.List;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    static int count = 0;
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }
    public static char[][] makesol(int[] q){
        int n = q.length;
        char[][] sol = new char[q.length][q.length];
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(q[i] == j){
                    sol[i][j] = 'Q';
                }
                else{
                    sol[i][j] = '.';
                }
            }
        }
        return sol;
    }

    public static char[][] printsol(int[] q){
        int n = q.length;
        char[][] sol = new char[q.length][q.length];
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(q[i] == j){
                    sol[i][j] = 'Q';
                    System.out.print("Q");
                }
                else{
                    sol[i][j] = '.';
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        return sol;
    }


    public static boolean isValid(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n])             return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }

    public static List<char[][]> solve(int[] q, int qNum, List<char[][]> solns){
        int n = q.length;
        if (qNum == n){ //base case, have filled in a column for each queen
            char[][] boardsol = printsol(q);
            solns.add(boardsol);
            System.out.println(count);
            count++;
        }
        else {
            for (int i = 0; i < n; i++) { //every possible solution for where q[i] can go
                q[qNum] = i; //place queen of rown qNum in column i
                if (isValid(q, qNum)) solve(q, qNum + 1, solns);
                //the implied else is the backtracking, we're just moving to next choice!
            }
        }
        return solns;
    }
    public static List<char[][]> nQueensSolutions(int n) {
        LinkedList<char[][]> solns = new LinkedList();
        return solve(new int[n], 0, solns);
    }

}
