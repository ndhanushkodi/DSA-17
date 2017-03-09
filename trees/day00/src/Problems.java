import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Collections.sort(values);
        binaryAdd(bst, values, 0, values.size());
        return bst;

    }
    private static void binaryAdd(BinarySearchTree<Integer> bst, List<Integer> values, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        bst.add(values.get(mid));
        binaryAdd(bst, values, lo, mid);
        binaryAdd(bst, values, mid+1, hi);
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1==null && n2==null)
            return true;
        if (n1==null || n2==null)
            return false;
        if (!n1.equals(n2))
            return false;
        // There are two possible cases for n1 and n2 to be isomorphic
        // Case 1: The subtrees rooted at these nodes have NOT been
        // "Flipped".
        // Both of these subtrees have to be isomorphic.
        // Case 2: The subtrees rooted at these nodes have been "Flipped"
        if (isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild))
            return true;
        if (isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild))
            return true;
        return false;
    }
}
