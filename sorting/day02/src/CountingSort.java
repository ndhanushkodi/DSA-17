public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     * O (n + k)
     */

    /*
    *   k = findMax(A)
        counts = new int[k]
        for (e in A) counts[e]++;
        i = 0
        for j from 0 to max:
        while counts[j]-- > 0:
        A[i++] = j
    * */
    static void countingSort(int[] A) {
        int k = A[0];
        for (int i = 1; i < A.length; i++)
            k = (A[i] > k) ? A[i] : k;
        k++;

        int[] counts = new int[k];
        for (int i : A)
            counts[i]++;

        int m = 0;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < counts[i]; j++)
                A[m++] = i;
    }

}
