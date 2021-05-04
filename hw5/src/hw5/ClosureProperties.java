package hw5;
import java.util.*;

public class ClosureProperties {
    
//All graph properties can be found in graph.java

    // Inputs our matched graph, and calculates Homophily 
    /*public float homophily (List<List<String>> matchings) {
        return 0; //TODO
    }*/
    
    //Calculates clustering coefficient for tutors
    public double clusteringCoefficientTutor (List<List<String>> matchings, String name) {
        List<List<String>> filtered = new ArrayList<List<String>>();
        ArrayList<String> neighbors = new ArrayList<String>();
        for (List<String> matching: matchings) {
            if (matching.get(0).equals(name)) {
                filtered.add(matching);
                neighbors.add(matching.get(1));
            }
        }
        double count = 0.0;
        System.out.println(neighbors.toString());
        for (String neighbor: neighbors) {
            for(List<String> matching: matchings) {
                if (matching.get(1).equals(neighbor) && neighbors.contains(matching.get(0))) {
                    count++;
                }
            }
        }
        return count/((neighbors.size() * (neighbors.size()-1))/2);
    }
    
    //Calculates clustering coefficient for students
    public float clusteringCoefficientStudent (List<List<String>> matchings, String name) {
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
    /*//Checks if triadic closure is satisfied
    public boolean triadicClosure (List<List<String>> matchings) {
        return true; //TODO
    }*/
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
