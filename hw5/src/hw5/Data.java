package hw5;
import org.json.simple.*;
import java.util.*;


public class Data {
    
    public enum Course {
        MATH, READING, WRITING, SCIENCE, HISTORY
    }
    
    private List<Course> createCourses() {
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject createEmployee() {
        JSONObject student = new JSONObject();
        student.put("id", 0);
        student.put("name", "William Li");
        return null;
    }
    
}
