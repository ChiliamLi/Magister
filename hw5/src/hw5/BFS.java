package hw5;
import java.util.*;

public class BFS {
    
    private BFS() {};
    
    // check if there exists path from s to t in g
    public static boolean isReachable(StudentTutorGraph g, int s, int t) {
        LinkedList<Integer> q;
        boolean[] discovered;
        
        discovered = new boolean[g.getSize()];
        
        for (int i = 0; i < g.getSize(); i++) {
            discovered[i] = false;
        }
        
        q = new LinkedList<>();
        q.addLast(s);
        discovered[s] = true;
        
        while (q.size() > 0) {
            int v = q.pollFirst();
            if (v == t) {
                return true;
            }
            for (int u : g.outNeighbors(v)) {
                if (!discovered[u] && g.getWeight(v, u) > 0) {
                    discovered[u] = true;
                    q.addLast(u);
                }
            }
        }
        return false;
    }
      
    // return path, if there exists one, from s to t in g
    public static int[] getPath(StudentTutorGraph g, int s, int t) {
        LinkedList<Integer> q;
        boolean[] discovered;
        int[] parent;
        
        discovered = new boolean[g.getSize()];
        parent = new int[g.getSize()];
        
        for (int i = 0; i < g.getSize(); i++) {
            discovered[i] = false;
            parent[i] = -1;
        }
        
        q = new LinkedList<>();
        q.addLast(s);
        discovered[s] = true;
        
        while (q.size() > 0) {
            int v = q.pollFirst();
            if (v == t) {
                return parent;
            }
            for (int u : g.outNeighbors(v)) {
                if (!discovered[u] && g.getWeight(v, u) > 0) {
                    discovered[u] = true;
                    q.addLast(u);
                    parent[u] = v;
                }
            }
        }
        System.out.println("No path found");
        return null;
    }
}