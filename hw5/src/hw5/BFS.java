package hw5;
import java.util.*;

public class BFS {
    
    private BFS() {};
    
    public static boolean doBFS1(StudentTutorGraph g, int s, int t, int[] parent) {
        LinkedList<Integer> q;
        boolean[] discovered;
        
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
                    //Added this part to return true or false for reachability; 
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    discovered[u] = true;
                    q.addLast(u);
                    parent[u] = v;
                }
            }
        }
        return false;
    }
    
    //Change BFS to be reachable @jeffrey plz
    //Change BFS to take in a parent array that's modified iteritavely: 
    
}