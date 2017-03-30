import java.util.LinkedList;
import java.util.Queue;

public class ReviewProblem {

    public static int minimumLengthSubArray2(int[] A, int desiredSum) {
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (end < A.length) {
            while (end < A.length && sum < desiredSum) {
                sum += A[end];
                end++;
            }
            if (sum < desiredSum) break;
            while (start < end && sum >= desiredSum){
                sum -= A[start];
                start++;
            }
            if (end - start + 1 < minLen) {
                minLen = end - start + 1;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static int minimumLengthSubArrayArr(int[] A, int desiredSum){
        if (A == null || A.length == 0)
            return 0;
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < A.length) {
            sum += A[j];
            j = j+1;
            while (sum >= desiredSum) {
                min = Math.min(min, j - i);
                sum -= A[i];
                i = i+1;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static int minimumLengthSubArray(int[] A, int desiredSum){
        if (A == null || A.length == 0)
            return 0;
        Queue<Integer> q = new  LinkedList<>();
        int index = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while (index < A.length) {
            while(sum < desiredSum) {
                q.add(A[index]);
                sum += A[index];
                index = index + 1;
            }
            while (sum >= desiredSum) {
                min = Math.min(min, q.size());
                sum -= q.poll();
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
