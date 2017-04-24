import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {
//    private static double cost7(String[] words, int lo, int hi, int m) {
//        if (hi <= lo)
//            throw new IllegalArgumentException("Hi must be higher than Lo");
//        int length = hi-lo-1; // account for spaces;
//        for (int i = lo; i < hi; i++) {
//            length += words[i].length();
//        }
//        if (length > m)
//            return Double.POSITIVE_INFINITY;
//        return Math.pow(m-length, 3);
//    }

//    public static List<Integer> justifyText2(String[] w, int m) {
//        double[] DP = new double[w.length + 1];
//        DP[w.length] = 0;
//        int[] lineBreaks = new int[w.length];
//        lineBreaks[w.length-1] = w.length;
//
//        for (int i = w.length-1; i >= 0; i--) {
//            int endLine = i+1;
//            double lowest_cost = Double.POSITIVE_INFINITY;
//            for (int j = i+1; j <= w.length; j++) {
//                double c = cost7(w, i, j, m) + DP[j];
//                if (c < lowest_cost) {
//                    lowest_cost = c;
//                    endLine = j;
//                }
//            }
//            DP[i] = lowest_cost + DP[endLine];
//            lineBreaks[i] = endLine;
//        }
//
//        int index = 0;
//        List<Integer> breaks = new LinkedList<>();
//        while (index < w.length) {
//            breaks.add(index);
//            index = lineBreaks[index];
//        }
//
//        return breaks;
//    }

    //
    //top down

    public static List<Integer> justifyText(String[] w, int m) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] memo = new int[w.length+1];
        int[] trace = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            trace[i] = -1;
            memo[i] = -1;
        }
        justifyTextHelper(w, memo, trace, m, 0);

        int i = 0;
        while(i<w.length) {
            answer.add(i);
            if(i == trace[i]){
                return answer;
            }
            i = trace[i];

        }
        return answer;
    }

    private static int length(String[] w, int i , int j){
        int totalSum = 0;
        for (int k = i; k < j ; k++) totalSum += w[k].length() + 1;
        return totalSum - 1;
    }

    //calcuates how bad it is to have words of index i to j in a line
    private static int cost(String[] w, int i, int j, int m){
        if(length(w, i, j) > m) return Integer.MAX_VALUE;      //if they do not fit, then return largest integer
        return (int) Math.pow(m-length(w, i, j),3);       //if they fit return cost; cubed to avoid confusion
    }


    private static int justifyTextHelper (String[] w, int[] memo, int[] trace, int m, int i){

        if(memo[i] != -1) return memo[i];

        int minIndex = i+1;
        int currentMin = Integer.MAX_VALUE;
        int total;

        for (int j = i+1; j <= w.length; j++) {
            int c1 = justifyTextHelper(w,memo, trace, m, j);
            int c2 = cost(w,i,j,m);

            if(c2 == Integer.MAX_VALUE){
                total = Integer.MAX_VALUE;
            }
            else {
                total = c1+c2;
            }
            if(total<currentMin){
                currentMin = total;
                minIndex = j;
            }
        }
        memo[i] = currentMin;
        trace[i] = minIndex;
        return currentMin;

    }

}