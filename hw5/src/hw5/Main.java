package hw5;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // set parameters
        int numStudents = 500;
        int numTutors = 100;
        int numCourses = Course.values().length;
       
        // create graph from data
        StudentTutorGraph g = new StudentTutorGraph(numStudents, numTutors, numCourses);
        g.init();
        StudentTutorGraph rGraph = BipartiteMatching.doFF(g);
        
        // get list of matchings, format is ({tutor name}, {student name}, {subject})
        List<List<String>> matchings = BipartiteMatching.getMatchings(g, rGraph);
    }

}
