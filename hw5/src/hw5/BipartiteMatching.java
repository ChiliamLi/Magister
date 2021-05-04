package hw5;

import java.util.HashMap;

public class BipartiteMatching {
 
    // get residual graph from final iteration of FF
    public static StudentTutorGraph doFF(StudentTutorGraph g) {
        
        int u, v;
        int s = 0;
        int t = g.getSize()-1;
        
        StudentTutorGraph rGraph = new StudentTutorGraph(g.numStudents, g.numTutors, g.numCourses);
        
        // initialize residual graph to be copy of g        
        for (int i = 0; i < g.getSize(); i++) {
            HashMap<Integer, Integer> hm = g.g.get(i);
            for (int j : hm.keySet()) {
                rGraph.addEdge(i, j, hm.get(j));
                rGraph.addEdge(j, i, 0);
            }
        }
 
        int max_flow = 0;
 
        // augment the flow while there is path from source to sink
        while (BFS.isReachable(rGraph, s, t)) {
            // find minimum residual capacity of edges along path found by BFS
            int[] parent = BFS.getPath(rGraph, s, t);
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph.getWeight(u, v));
//                System.out.println("edge from " + u + " to " + v  + ": " + rGraph.getWeight(u, v));
            }
 
            // update residual capacities of edges
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph.setEdge(u, v, rGraph.getWeight(u, v) - path_flow); 
                rGraph.setEdge(v, u, rGraph.getWeight(v, u) - path_flow); 
            }

            // add path flow to overall flow
            max_flow += path_flow;
        }
 
        System.out.println("Max flow: " + max_flow);
        return rGraph; 
    }
    
    // get matchings from residual graph
    public static StudentTutorGraph getMatchings(StudentTutorGraph g, StudentTutorGraph rGraph) {
        // subtract final residual graph weights from original weights
        for (int i = 0; i < g.numTutors; i++) {
            for (int j = 0; j < g.numCourses; j++) {
                int tutorIndex = g.getTutorIndex(i) + 1;
                int courseIndex = g.getCourseIndex(j);
                if (g.hasEdge(tutorIndex, courseIndex)) {
                    g.setEdge(tutorIndex, courseIndex, g.getWeight(tutorIndex, courseIndex) - 
                            rGraph.getWeight(tutorIndex, courseIndex));
                } 
            }
        }        
        for (int j = 0; j < g.numCourses; j++) {
            for (int k = 0; k < g.numStudents; k++) {
                int courseIndex = g.getCourseIndex(j);
                int studentIndex = g.getStudentIndex(k);
                if (g.hasEdge(courseIndex, studentIndex)) {
                    g.setEdge(courseIndex, studentIndex, g.getWeight(courseIndex, studentIndex) - 
                            rGraph.getWeight(courseIndex, studentIndex));
                }
            }
        }
               
        // find all matchings
        for (int i = 0; i < g.numTutors; i++) {
            for (int j = 0; j < g.numCourses; j++) {
                for (int k = 0; k < g.numStudents; k++) {
                    int tutorIndex = g.getTutorIndex(i) + 1;
                    int courseIndex = g.getCourseIndex(j);
                    int studentIndex = g.getStudentIndex(k);
//                    System.out.println(tutorIndex + " " + courseIndex + " " + studentIndex);
                    if (g.hasEdge(tutorIndex, courseIndex) && 
                            g.getWeight(tutorIndex, courseIndex) > 0 && 
                            g.hasEdge(courseIndex, studentIndex) && 
                            g.getWeight(courseIndex, studentIndex) > 0) {
                        System.out.println("matching: " + tutorIndex + " " + courseIndex + " " + studentIndex);
                        System.out.println(g.tutors.get(i).getName() + " teaches " + 
                                g.students.get(k).getName() + " in " + 
                                Course.values()[j].toString().toLowerCase());
//                        System.out.println(g.tutors.get(1).getName());
//                        System.out.println(g.tutors.get(1).getID());
                        g.setEdge(tutorIndex, courseIndex, 
                                g.getWeight(tutorIndex, courseIndex) - 1);
                        g.setEdge(courseIndex, studentIndex, 
                                g.getWeight(courseIndex, studentIndex) - 1);
                    }
                }
            }
        }
        
        return g;
    }
   
}
