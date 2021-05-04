package hw5;
import org.json.simple.*;
import com.github.javafaker.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;

public class JSONDataGenerator {
    
    Faker faker = new Faker();
    
    private int minStudentGrade = 1;
    private int maxStudentGrade = 12;;
    private int minStudentFrequency = 1;
    private int maxStudentFrequency = 7;
    
    private int minTutorCapacity = 1;
    private int maxTutorCapacity = 10;
    private int minTutorRating = 0;
    private int maxTutorRating = 10;
    private int minTutorProficiency = 0;
    private int maxTutorProficiency = 10;
    
    public JSONDataGenerator() {}
    
    public JSONDataGenerator(int minStudentGrade, int maxStudentGrade, int minStudentFrequency,
            int maxStudentFrequency, int minTutorCapacity, int maxTutorCapacity,
            int minTutorRating, int maxTutorRating, int minTutorProficiency,
            int maxTutorProficiency) {
        this.minStudentGrade = minStudentGrade;
        this.maxStudentGrade = maxStudentGrade;
        this.minStudentFrequency = minStudentFrequency;
        this.maxStudentFrequency = maxStudentFrequency;
        this.minTutorCapacity = minTutorCapacity;
        this.maxTutorCapacity = maxTutorCapacity;
        this.minTutorRating = minTutorRating;
        this.maxTutorRating = maxTutorRating;
        this.minTutorProficiency = minTutorProficiency;
        this.maxTutorProficiency = maxTutorProficiency;
    }

    private List<Course> createCourses() {
        List<Course> courses = new ArrayList<>();
        for (Course course : Course.values()) {
            if (faker.number().randomDigit() % 2 == 0) {
                courses.add(course);
            }
        }
        return courses;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject createStudent(int iter) {
        JSONObject student = new JSONObject();
        student.put("id", iter);
        student.put("name", faker.name().firstName() + " " + faker.name().lastName());
        student.put("grade",  faker.number().numberBetween(minStudentGrade, maxStudentGrade + 1));
        student.put("courses", createCourses());
        student.put("frequency", faker.number().numberBetween(minStudentFrequency, 
                maxStudentFrequency + 1));
        return student;
    }
    
    @SuppressWarnings("unchecked")
    public JSONArray createStudents(int num) {
        JSONArray students = new JSONArray();
        for (int i = 0; i < num; i++) {
            students.add(createStudent(i));
        }
        return students;
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
    
    @SuppressWarnings("unchecked")
    public JSONObject createTutor(int iter) {
        JSONObject tutor = new JSONObject();
        tutor.put("id", iter);
        tutor.put("name", faker.name().firstName() + " " + faker.name().lastName());
        tutor.put("capacity", faker.number().numberBetween(minTutorCapacity, maxTutorCapacity + 1));
        tutor.put("rating", faker.number().randomDouble(2, minTutorRating, maxTutorRating + 1));
        Map<Course, Integer> proficiency = createProficiency();
        tutor.put("proficiency", proficiency);
        tutor.put("proficientCourses", createProficientCourses(proficiency));
        return tutor;
    }
    
    @SuppressWarnings("unchecked")
    public JSONArray createTutors(int num) {
        JSONArray tutors = new JSONArray();
        for (int i = 0; i < num; i++) {
            tutors.add(createTutor(i));
        }
        return tutors;
    }
    
    private String prettifyJSON(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        return gson.toJson(jsonArray);
    }
    
    public void writeJSON(int studentNum, int tutorNum) {
        try {
            Files.deleteIfExists(new File("students.json").toPath());
            Files.deleteIfExists(new File("tutors.json").toPath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try (FileWriter file = new FileWriter("students.json", true)) {
            file.write(prettifyJSON(createStudents(studentNum).toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished writing students.json");
        
        try (FileWriter file = new FileWriter("tutors.json", true)) {
            file.write(prettifyJSON(createTutors(tutorNum).toJSONString()));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished writing tutors.json");
    }
    
}