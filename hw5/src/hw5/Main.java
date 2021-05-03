package hw5;

public class Main {

    public static void main(String[] args) {
        // set parameters
        int numStudents = 500;
        int numTutors = 100;
        int numCourses = Course.values().length;
        int n = 2 + 2*numTutors + numCourses + numStudents; 
        // create graph from data
        StudentTutorGraph g = new StudentTutorGraph(numStudents, numTutors, numCourses);
        StudentTutorGraph rGraph = new StudentTutorGraph(numStudents, numTutors, numCourses); 
        rGraph = BipartiteMatching.findFinalResidualGraph(g, 0, g.getSize()-1, n, numStudents, 
                numTutors, numCourses); 
    }

}
