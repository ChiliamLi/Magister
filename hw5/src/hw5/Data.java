package hw5;
import org.json.simple.*;


public class Data {
    
    public enum Course {
        MATH, READING, WRITING, SCIENCE, HISTORY
    }
    
    public JSONObject createEmployee() {
        JSONObject student = new JSONObject();
        student.put("id", 0);
        student.put("name", "William Li");
        return null;
    }
    
}
