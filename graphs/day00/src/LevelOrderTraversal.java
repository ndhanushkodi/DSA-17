import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        List order = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current == null)
                continue;
            order.add(current.key);
            q.offer(current.leftChild);
            q.offer(current.rightChild);
        }
        return order;
    }
}
