
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private InsertionSort insertionSort = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * <p>
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     * <p>
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length < INSERTION_THRESHOLD) return insertionSort.sort(array);

        int mid = array.length / 2;

        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {
        int[] out = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                out[k] = a[i];
                i++;
            } else {
                out[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < a.length) {
            out[k] = a[i];
            i++;
            k++;
        }

        while (j < b.length) {
            out[k] = b[j];
            j++;
            k++;
        }

        return out;
    }


    //divide up solution into 2 functions
    public int[] merge2(int[] left, int[] right) {

        int[] out = new int[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (k < out.length) {
            if (chooseLeft(i, j, left, right)) {
                out[k] = left[i];
                i++;
            } else {
                out[k] = right[j];
                j++;
            }
            k++;
        }
        return out;
    }

    // Helper function that determines if the next item to merge should be from
    // the left array
    private boolean chooseLeft(int i, int j, int[] left, int[] right) {
        if (j == right.length)
            return true;
        if (i == left.length)
            return false;
        return left[i] < right[j];


    }
}
