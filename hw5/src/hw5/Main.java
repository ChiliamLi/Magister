package hw5;

public class Main {

    public static void main(String[] args) {
        // set parameters
        int numStudents = 500;
        int numTutors = 100;
        int numCourses = Course.values().length;
        
        // create graph from data
        StudentTutorGraph g = new StudentTutorGraph(numStudents, numTutors, numCourses);
    }

}
