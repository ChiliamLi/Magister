package hw5;
import com.github.javafaker.*;
import java.util.*;
import org.json.simple.JSONObject;

public class Student {
    
    Faker faker = new Faker();
    
    private int minStudentGrade = 1;
    private int maxStudentGrade = 12;;
    private int minStudentFrequency = 1;
    private int maxStudentFrequency = 7;
    
    private int id;
    private String name;
    private int grade;
    private List<Course> courses;
    private int frequency;
    
    public Student(int minStudentGrade, int maxStudentGrade, int minStudentFrequency,
            int maxStudentFrequency, int iter) {
        this.minStudentGrade = minStudentGrade;
        this.maxStudentGrade = maxStudentGrade;
        this.minStudentFrequency = minStudentFrequency;
        this.maxStudentFrequency = maxStudentFrequency;
        this.id = iter;
        initStudent();
    }
    
    public Student(int iter) {
        this.id = iter;
        initStudent();
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
    
    private void initStudent() {
        this.name = faker.name().firstName() + " " + faker.name().lastName();
        this.grade = faker.number().numberBetween(minStudentGrade, maxStudentGrade + 1);
        this.courses = createCourses();
        this.frequency = faker.number().numberBetween(minStudentFrequency, 
                maxStudentFrequency + 1);
    }
   
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject createJSON() {
        JSONObject student = new JSONObject();
        student.put("id", id);
        student.put("name", name);
        student.put("grade",  grade);
        student.put("courses", courses);
        student.put("frequency", frequency);
        return student;
    }
}