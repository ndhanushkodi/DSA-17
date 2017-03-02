public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int i) {
        int left = leftChild(i), right = rightChild(i);

        int largest = i;
        //figure out the largest between i, left, and right
        if (left < size && heap[left] > heap[i]) largest = left;
        if (right < size && heap[right] > heap[largest]) largest = right;

        if(largest != i){
            swap(heap, largest, i);
            heapify(largest);
        }

    }

    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;

        int m = this.size/2;
        for(int i=m;i>=0; i--){
            heapify(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        buildHeapFrom(array);
        for (int i=size-1; i>0; i--) {
            swap(heap, i, 0);
            size --;
            heapify(0);
        }
        return heap;
    }
}
