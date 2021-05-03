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

/**
 * Contains the API necessary for a simple, (optionally) weighted directed graph.
 * We call the graph "optionally weighted" because it can be used by algorithms that use weights
 * (like Dijkstra's) and by algorithms that do not (like BFS). An algorithm like BFS would simply
 * ignore any weights present.
 * <p>
 * By convention, the n vertices will be labeled 0,1,...,n-1. The edge weights can be any int value.
 * Since we are labeling vertices from 0 to n-1, you may find arrays/arraylists helpful!
 * Self loops and parallel edges are not allowed. Your implementation should use O(m + n) space.
 * Please DO NOT use adjacency matrices!
 * <p>
 * Also note that the runtimes given are expected runtimes. As a result, you should be
 * implementing your graph using a HashMap as the primary data structure for the adjacency list.
 * <p>
 * Notice that this class also supports undirected graph. Which means you can implement an
 * undirected graph as each undirected edge between u and v being two directed edge from u to v and
 * from v to u.
 */
public class StudentTutorGraph {
    
    ArrayList<HashMap<Integer, Integer>> g;
    
    int numStudents;
    int numTutors;
    int numCourses;
    
    List<Student> students;
    List<Tutor> tutors;
    
    JSONArray studentsJSON = new JSONArray();
    JSONArray tutorsJSON = new JSONArray();
    
    private int getTutorIndex(int n) {
        return 2*n + 1;
    }
    
    private int getTutorID(int n) {
        return (n - 1) / 2;
    }
    
    private int getCourseIndex(int n) {
        return n + 1 + 2*numTutors;
    }
    
    private int getStudentIndex(int n) {
        return getCourseIndex(n) + numCourses; 
    }
    
    private int getStudentID(int n) {
        return n - numCourses - 2*numTutors - 1;
    }
    
    public Student getStudent(int index) {
        return students.get(getStudentID(index));
    }
    
    public Tutor getTutor(int index) {
        return tutors.get(getTutorID(index));
    }
    
    private String prettifyJSON(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        return gson.toJson(jsonArray);
    }
    
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
    
    /**
     * Initializes a graph of size {@code n}. All valid vertices in this graph thus have integer
     * indices in the half-open range {@code [0, n)}, n > 0.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param n the number of vertices in the graph
     * @throws IllegalArgumentException if {@code n} is zero or negative
     * @implSpec This method should run in expected O(n) time
     */
    @SuppressWarnings("unchecked")
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
                        Integer.MAX_VALUE);
            }
        }
        
        System.out.println("Finished initializing graph");
    }

    /**
     * Returns the number of vertices in the graph.
     * <p/>
     * Do NOT modify this method header.
     *
     * @return the number of vertices in the graph
     * @implSpec This method should run in expected O(1) time.
     */
    public int getSize() {
        return g.size();
    }

    /**
     * Determines if there's an directed edge from u to v.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u a vertex
     * @param v a vertex
     * @return {@code true} if the {@code u-v} edge is in this graph
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public boolean hasEdge(int u, int v) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        return g.get(u).containsKey(v);
    }

    /**
     * Returns the weight of an the directed edge {@code u-v}.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u source vertex
     * @param v target vertex
     * @return the edge weight of {@code u-v}
     * @throws NoSuchElementException   if the {@code u-v} edge does not exist
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public int getWeight(int u, int v) {
        if (u < 0 || v < 0 || u >= g.size() || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }
        return g.get(u).get(v);
    }

    /**
     * Creates an edge from {@code u} to {@code v} if it does not already exist. A call to this
     * method should <em>not</em> modify the edge weight if the {@code u-v} edge already exists.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u      the source vertex to connect
     * @param v      the target vertex to connect
     * @param weight the edge weight
     * @return {@code true} if the graph changed as a result of this call, false otherwise (i.e., if
     * the edge is already present)
     * @throws IllegalArgumentException if a specified vertex does not exist or if u == v
     * @implSpec This method should run in expected O(1) time
     */
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

    /**
     * Returns the out-neighbors of the specified vertex.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param v the vertex
     * @return all out neighbors of the specified vertex or an empty set if there are no out
     * neighbors
     * @throws IllegalArgumentException if the specified vertex does not exist
     * @implSpec This method should run in expected O(outdeg(v)) time.
     */
    public Set<Integer> outNeighbors(int v) {
        if (v < 0 || v >= g.size()) {
            throw new IllegalArgumentException();
        }
        return g.get(v).keySet();
    }
}