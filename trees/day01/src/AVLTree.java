import sun.reflect.generics.tree.Tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if(n != null) {
            n.height = height(n);
            return balance(n);
        }
        return null;
    }
    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T>  n, T key) {
        n = super.insert(n, key);
        if(n != null) {
            n.height = height(n);
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n){
        n = super.deleteMin(n);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null){
            return -1;
        }
        return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
    }

//    private int height(TreeNode x) {
//        if (x == null) return -1;
//        return x.height;
//    }

    public int height() {
        return Math.max(height(root),0);
    }

    // Restores the AVL tree property of the subtree.
    TreeNode<T> balance(TreeNode<T> n) {
        if (balanceFactor(n) > 1) {
            if (balanceFactor(n.rightChild) < 0) {
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        }
        else if (balanceFactor(n) < -1) {
            if (balanceFactor(n.leftChild) > 0) {
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> B = n;
        TreeNode<T> A = n.leftChild;
        TreeNode<T> beta = A.rightChild;
        A.rightChild = B;
        B.leftChild = beta;
        B.height = height(B);
        A.height = height(A);
        return A;
    }
//    private TreeNode<T> rotateRight(TreeNode<T> x) {
//        TreeNode<T> y = x.leftChild;
//        x.leftChild = y.rightChild;
//        y.rightChild = x;
//        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
//        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
//        return y;
//    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> A = n;
        TreeNode<T> B = n.rightChild;
        TreeNode<T> beta = B.leftChild;
        B.leftChild = A;
        A.rightChild = beta;
        A.height = height(A);
        B.height = height(B);
        return B;
    }
//    private TreeNode<T> rotateLeft(TreeNode<T> x) {
//        TreeNode<T> y = x.rightChild;
//        x.rightChild = y.leftChild;
//        y.leftChild = x;
//        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
//        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
//        return y;
//    }
}
