package hw5;
import org.json.simple.*;
import java.util.*;


public class Data {
    
    public enum Course {
        MATH, READING, WRITING, SCIENCE, HISTORY
    }
    
    private List<Course> createCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(Course.MATH);
        courses.add(Course.HISTORY);
        return courses;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject createStudent() {
        JSONObject student = new JSONObject();
        student.put("id", 0);
        student.put("name", "William Li");
        student.put("courses", createCourses());
        return student;
    }
    
}
