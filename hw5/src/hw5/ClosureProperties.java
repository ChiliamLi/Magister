package hw5;
import java.util.*;

public class ClosureProperties {
    
//All graph properties can be found in graph.java

    // Inputs our matched graph, and calculates Homophily 
    public float homophily (List<List<String>> matchings) {
        return 0; //TODO
    }
    //Calculates clustering coefficient
    public float clusteringCoefficients (List<List<String>> matchings) {
        return 0; //TODO
    }
    //Checks if focal closure is satisfied
    public boolean focalClosure (List<List<String>> matchings) {
        return true; //TODO
    }
    //Checks if membership closure is satisfied
    public boolean membershipClosure (List<List<String>> matchings) {
        return true; //TODO
    }
    //Checks if triadic closure is satisfied
    public boolean triadicClosure (List<List<String>> matchings) {
        return true; //TODO
    }
    //Checks to see if our tutor/student graph is a bipartite graph
    public boolean BipartiteMatching (List<List<String>> matchings) {
        return true; //TODO
    }
    
    //Calculates the number of subtrees and finds a numerical representation for how connected
    //our graph is (basically how interconnected our tutor/student group is)
    public boolean DFSConnectedness (List<List<String>> matchings) {
        return true; //TODO
    }
    
}
