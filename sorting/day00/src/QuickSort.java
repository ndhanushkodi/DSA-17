import java.util.concurrent.ThreadLocalRandom;
public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case
        shuffleArray(array);
        quickSort(array, 0, array.length-1);
        return array;
    }
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {
        if(low < high){
            int p = partition(a, low, high);
            quickSort(a, low, p-1);
            quickSort(a, p+1, high);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low + 1;

        for (int j = low+1; j<=high; j++){ //inclusive
            if(array[j] < pivot){

                swap(array, i, j);
                i++;
            }
        }
        swap(array, low, i-1);
        return i-1;

    }

}
