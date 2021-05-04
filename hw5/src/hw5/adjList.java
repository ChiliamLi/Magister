package hw5;
import java.util.*;
import java.util.Map.Entry;

public class adjList {
    private HashMap<String, ArrayList<String>> alist;
    private int numVer;
    
    public adjList(int nodes){
        numVer = nodes;
        alist = new HashMap<String, ArrayList<String>>();
    }
    
    /* Adding directed edges based on vertex and connector value. 
     * If keys or values are missing, the code adds them in to the alist HashMap
     * */
    
    public void addEdge(String vertex, String connector){
        if (alist.containsKey(vertex)){
             if (!alist.get(vertex).contains(connector)) {
                 alist.get(vertex).add(connector);
             }
        }
        if (alist.containsKey(connector)){
            if (!alist.get(connector).contains(vertex)) {
                alist.get(connector).add(vertex);
            }
        }
        if (!alist.containsKey(vertex)) {
            alist.put(vertex, new ArrayList<String>());
            alist.get(vertex).add(connector);
            
        }
        if (!alist.containsKey(connector)) {
            alist.put(connector, new ArrayList<String>());
            alist.get(connector).add(vertex);
            
        }
    }
    
    public boolean hasEdge(int v, int c){
        // alist.get(v) is an ArrayList while c is one of v's possible neighbors
        if (alist.containsKey(v)){
             return alist.get(v).contains(c);
        }
        else if (alist.containsKey(c)){
            return alist.get(c).contains(v);
        }       
        return false; 
    }
    /*
     * Method to print adjacency list for debugging purposes, not used in code output
     */
    public void printList() {
        for (Entry<String, ArrayList<String>> entry : alist.entrySet()) {
            String head = entry.getKey();
            System.out.println("HEAD:" + head);
            List<String> list = entry.getValue();
             System.out.println("\nAdjacency list of vertex: " + head); 
             for (String i: list) { 
                 System.out.print(" -> "+ i); 
             } 
             System.out.println(); 
             System.out.println(); 
        }
    }
}
