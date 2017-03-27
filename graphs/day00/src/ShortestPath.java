import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> pred = new HashMap<>(); //key, where it came from
        Set<Integer> visited = new HashSet<>();
        q.offer(v);
        visited.add(v);

        while(!q.isEmpty()){
            int current = q.poll();
            for(int neighbor: g.getNeighbors(current)){
                if(!visited.contains(neighbor)){
                    pred.put(neighbor, current);
                    visited.add(neighbor);
                    q.offer(neighbor);
                }
            }
        }

        if(!visited.contains(w)){
            return null;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int curr = w;
        path.add(w);

        //start at end and add to beginning until v
        while(curr!=v){
            curr = pred.get(curr);
            path.addFirst(curr);
        }

        return path;

    }

    public static int distanceBetween(Graph g, int v, int w) {
        List<Integer> path = shortestPath(g, v, w);
        if(path == null)
            return -1;
        return path.size()-1;
    }

}