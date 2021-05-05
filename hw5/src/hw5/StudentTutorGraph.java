package hw5;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import org.json.simple.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

// Creates an undirected graph of student and tutors

public class StudentTutorGraph {
    
    ArrayList<HashMap<Integer, Integer>> g;
    
    int numStudents;
    int numTutors;
    int numCourses;
    
    List<Student> students;
    List<Tutor> tutors;
    
    JSONArray studentsJSON = new JSONArray();
    JSONArray tutorsJSON = new JSONArray();
    
    // Get index of tutor node in graph
    public int getTutorIndex(int n) {
        return 2*n + 1;
    }
    
    // Get index of course node in graph
    public int getCourseIndex(int n) {
        return n + 1 + 2*numTutors;
    }
    
    // Get index of student node in graph
    public int getStudentIndex(int n) {
        return getCourseIndex(n) + numCourses; 
    }
    
    // Format json
    private String prettifyJSON(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        return gson.toJson(jsonArray);
    }
    
    // Write json files
    public void writeJSON() {
        try {
            Files.deleteIfExists(new File("students.json").toPath());
            Files.deleteIfExists(new File("tutors.json").toPath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try (FileWriter file = new FileWriter("students.json", true)) {
            file.write(prettifyJSON(studentsJSON.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished writing students.json");
        
        try (FileWriter file = new FileWriter("tutors.json", true)) {
            file.write(prettifyJSON(tutorsJSON.toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished writing tutors.json");
    }
    
    // Initialize graph
    
    @SuppressWarnings("unchecked")
    public void init() {
        students = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            Student s = new Student(i);
            students.add(s);
            studentsJSON.add(s.createJSON());
        }
        tutors = new ArrayList<>();
        for (int i = 0; i < numTutors; i++) {
            Tutor t = new Tutor(i);
            tutors.add(t);
            tutorsJSON.add(t.createJSON());
        }
        
        writeJSON();
        
        for (Student s : students) {
            for (Course course : s.getCourses()) {
                addEdge(getCourseIndex(course.ordinal()), getStudentIndex(s.getID()), 1);
            }
            addEdge(getStudentIndex(s.getID()), g.size()-1, Integer.MAX_VALUE);
        }
        for (Tutor t : tutors) {
            addEdge(0, getTutorIndex(t.getID()), Integer.MAX_VALUE);
            addEdge(getTutorIndex(t.getID()), getTutorIndex(t.getID())+1, t.getCapacity());
            for (Course course : t.getProficientCourses()) {
                addEdge(getTutorIndex(t.getID())+1, getCourseIndex(course.ordinal()), 
                        t.getCapacity());
            }
        }    
        System.out.println("Finished initializing graph");
    }
    
    // Initializes student tutor graph
    
    public StudentTutorGraph(int numStudents, int numTutors, int numCourses) {        
        this.numStudents = numStudents;
        this.numTutors = numTutors;
        this.numCourses = numCourses;
        int n = 2 + 2*numTutors + numCourses + numStudents;
        
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        g = new ArrayList<HashMap<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            g.add(new HashMap<Integer, Integer>());
        }
    }

    // Returns the number of vertices in the graph

    public int getSize() {
        return g.size();
    }
    
    // Checks if there is an edge from u to v

    public boolean hasEdge(int u, int v) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        return g.get(u).containsKey(v);
    }

     // Returns weight of edge u-v
      
    public int getWeight(int u, int v) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }
        return g.get(u).get(v);
    }

    // Creates an edge from u to v if edge does not initially exist

    public boolean addEdge(int u, int v, int weight) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size() || u == v) {
            throw new IllegalArgumentException();
        }
        if (hasEdge(u, v)) {
            return false;
        } else {
            g.get(u).put(v, weight);
            return true;
        }
    }
    
    // Replace existing edge weight with specified value
    public void setEdge(int u, int v, int weight) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size() || u == v) {
            throw new IllegalArgumentException();
        }
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }
        g.get(u).put(v, weight);
    }

    // Gets the number of neighbors of a vertex (used for BFS)

    public List<Integer> outNeighbors(int v) {
        if (v < 0 || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        List<Integer> neighbors = new ArrayList<>(g.get(v).keySet());
        Collections.shuffle(neighbors);
        return neighbors;
    }
    
}
