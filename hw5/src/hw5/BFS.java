package hw5;
import java.util.*;

public class BFS {
    
    private BFS() {};
    
    public static int[] doBFS1(StudentTutorGraph g, int s) {
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
            for (int u : g.outNeighbors(v)) {
                if (!discovered[u]) {
                    discovered[u] = true;
                    q.addLast(u);
                    parent[u] = v;
                }
            }
        }
        
        return parent;
    }
    
}