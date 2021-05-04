package hw5;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // set parameters
        ClosureProperties cp = new ClosureProperties();
        int numStudents = 3;
        int numTutors = 2;
        int numCourses = Course.values().length;
        adjList alist = new adjList(numStudents + numTutors);
       
        // create graph from data
        StudentTutorGraph g = new StudentTutorGraph(numStudents, numTutors, numCourses);
        g.init();
        StudentTutorGraph rGraph = BipartiteMatching.doFF(g);
        
        // get list of matchings, format is ({tutor name}, {student name}, {subject})
        //List<List<String>> matchings = BipartiteMatching.getMatchings(g, rGraph);
        //System.out.println(matchings.toString());
        ArrayList<String> match1 = new ArrayList<String>(Arrays.asList("Rene Mueller", "Justa Bauch", "writing"));
        ArrayList<String> match2 = new ArrayList<String>(Arrays.asList("Rene Mueller", "Lesley Stracke", "science")); // replaced writing w/ science to test focal closure
        ArrayList<String> match3 = new ArrayList<String>(Arrays.asList("Rene Mueller", "Ozzie Wehner", "science")); // replaced writing w/ science to test focal closure
        ArrayList<String> match4 = new ArrayList<String>(Arrays.asList("Lesley Stracke", "Ozzie Wehner", "science")); // replaced margene emard w/ lesley stracke to test clustering coef tutor method + replaced math w/ writing
        ArrayList<String> match5 = new ArrayList<String>(Arrays.asList("Margene Emard", "Lesley Stracke", "science"));
        ArrayList<String> match6 = new ArrayList<String>(Arrays.asList("Margene Emard", "Rene Mueller", "science")); // replaced ozzie wehner w/ rene mueller to test clustering coef student method
        List<List<String>> m = new ArrayList<List<String>>();
        m.add(match1);
        m.add(match2);
        m.add(match3);
        m.add(match4);
        m.add(match5);
        m.add(match6);
        System.out.println(m.toString());
        //System.out.println(cp.clusteringCoefficientTutor(m, "Rene Mueller"));
        //System.out.println(cp.clusteringCoefficientStudent(m, "Lesley Stracke"));
        //System.out.println(cp.focalClosure(m, "science"));
        //System.out.println(cp.membershipClosure(m, "Rene Mueller"));
        //System.out.println(cp.triadicClosure(m, "Rene Mueller", "Justa Bauch", "writing"));
        for (List<String> matching: m) {
            alist.addEdge(matching.get(0), matching.get(1));
        }
        alist.printList();
    }

}
