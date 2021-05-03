package hw5;
import com.github.javafaker.*;
import java.util.*;
import org.json.simple.JSONObject;

public class Tutor {

    Faker faker = new Faker();
    
    private int minTutorCapacity = 1;
    private int maxTutorCapacity = 10;
    private int minTutorRating = 0;
    private int maxTutorRating = 10;
    private int minTutorProficiency = 0;
    private int maxTutorProficiency = 10;
    
    private int id;
    private String name;
    private int capacity;
    private double rating;
    private Map<Course, Integer> proficiency;
    private List<Course> proficientCourses;
    
    
    public Tutor(int minTutorCapacity, int maxTutorCapacity,
            int minTutorRating, int maxTutorRating, int minTutorProficiency,
            int maxTutorProficiency, int iter) {
        this.minTutorCapacity = minTutorCapacity;
        this.maxTutorCapacity = maxTutorCapacity;
        this.minTutorRating = minTutorRating;
        this.maxTutorRating = maxTutorRating;
        this.minTutorProficiency = minTutorProficiency;
        this.maxTutorProficiency = maxTutorProficiency;
        this.id = iter;
        initTutor();
    }
    
    public Tutor(int iter) {
        this.id = iter;
        initTutor();
    }
    
    private Map<Course, Integer> createProficiency() {
        Map<Course, Integer> proficiency = new HashMap<>();
        for (Course course : Course.values()) {
            proficiency.put(course, faker.number().numberBetween(minTutorProficiency, 
                    maxTutorProficiency + 1));
        }
        return proficiency;
    }
    
    private List<Course> createProficientCourses(Map<Course, Integer> proficiency) {
        List<Course> proficientCourses = new ArrayList<>();
        for (Course course : proficiency.keySet()) {
            if (proficiency.get(course) >= 5) {
                proficientCourses.add(course);
            }
        }
        return proficientCourses;
    }
    
    private void initTutor() {
        this.name = faker.name().firstName() + " " + faker.name().lastName();
        this.capacity = faker.number().numberBetween(minTutorCapacity, maxTutorCapacity + 1);
        this.rating = faker.number().randomDouble(2, minTutorRating, maxTutorRating + 1);
        Map<Course, Integer> proficiency = createProficiency();
        this.proficiency = proficiency;
        this.proficientCourses = createProficientCourses(proficiency);
    }
    
    public int getID() {
        return id;
    }
   
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public double getRating() {
        return rating;
    }
    
    public Map<Course, Integer> getProficiency() {
        return proficiency;
    }
    
    public List<Course> getProficientCourses() {
        return proficientCourses;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject createJSON() {
        JSONObject tutor = new JSONObject();
        tutor.put("id", id);
        tutor.put("name", name);
        tutor.put("capacity", capacity);
        tutor.put("rating", rating);
        tutor.put("proficiency", proficiency);
        tutor.put("proficientCourses", proficientCourses);
        return tutor;
    }
}
