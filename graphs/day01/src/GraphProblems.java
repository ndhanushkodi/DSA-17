import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GraphProblems {

    private static void dfsConnected(Graph g, int v, Set<Integer> visited){
        visited.add(v);

        for (int n : g.getNeighbors(v)){
            if(!visited.contains(n)){
                dfsConnected(g, n, visited);
            }
        }
    }

    public static boolean connected(Graph g, int v, int u) {
        Set<Integer> visited = new HashSet<>();
        dfsConnected(g, v, visited);
        return visited.contains(u);
    }

    public static List<Integer> topologicalOrder(Digraph g) {
        LinkedList<Integer> topoOrder = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int v : g.vertices())
            if (!visited.contains(v))
                dfsTopo(g, v, topoOrder, visited);
        return topoOrder;
    }

    private static void dfsTopo(Digraph g, int v, LinkedList<Integer> topoOrder, Set<Integer> visited) {
        visited.add(v);
        for (int n : g.getNeighbors(v))
            if (!visited.contains(n))
                dfsTopo(g, n, topoOrder, visited);
        // Add the current vertex to the head of a LinkedList
        topoOrder.addFirst(v);
    }

    public static boolean hasCycle(UndirectedGraph g) {
        Set<Integer> marked = new HashSet<>();
        // Check if there's a cycle starting from each vertex using DFS
        for (int v : g.vertices()) {
            if (!marked.contains(v)) {
                if (dfsCycle(g, v, -1, marked)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean dfsCycle(Graph g, int v, int cameFrom, Set<Integer> marked) {
        marked.add(v);
        for (int n : g.getNeighbors(v)) {
            if (!marked.contains(n)) {
                if (dfsCycle(g, n, v, marked)) {
                    return true;
                }
            }
            // if we reach a marked vertex and it's not the one we came from, we've found
            // a cycle.
            else if (n != cameFrom) {
                return true;
            }
        }
        return false;
    }



}