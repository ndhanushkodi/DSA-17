
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j-1] > array[j]) {
                swap(array, j, j-1);
                j--;
            }
        }
        return array;
    }
}
