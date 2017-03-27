import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UndirectedGraph implements Graph {

    private Map<Integer, List<Integer>> adj; //adjacency list
    private List<Integer> vertices;
    private int numEdges;

    public UndirectedGraph(int n) {
        this.numEdges = 0;
        adj = new HashMap<>();
        vertices = new LinkedList<>();
        // initialize each adjacency list
        for (int v = 0; v < n; v++) {
            adj.put(v, new LinkedList<>());
            vertices.add(v);
        }
    }

    @Override
    //O(1)
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
        numEdges++;
    }

    //O(1)
    @Override
    public List<Integer> vertices() {
    	return vertices;
    }

    //O(1)
    @Override
    public int numVertices() {
    	return vertices.size();
    }

    //O(1)
    @Override
    public int numEdges() {
    	return numEdges;
    }

    //O(1)
    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	return adj.get(v);
    }

    //O(e/v)
    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	return adj.get(v).contains(w);
    }

}
