package hw5;
import java.util.*;

public class ClosureProperties {
    
//All graph properties can be found in graph.java

    
    //Calculates clustering coefficient for tutors
    public double clusteringCoefficientTutor (List<List<String>> matchings, String name) {
        List<List<String>> filtered = new ArrayList<List<String>>();
        ArrayList<String> neighbors = new ArrayList<String>();
        for (List<String> matching: matchings) {
            if (matching.get(0).equals(name)) {
                filtered.add(matching);
                neighbors.add(matching.get(1));
            }
        }
        double count = 0.0;
        System.out.println(neighbors.toString());
        for (String neighbor: neighbors) {
            for(List<String> matching: matchings) {
                if (matching.get(1).equals(neighbor) && neighbors.contains(matching.get(0))) {
                    count++;
                }
            }
        }
        return count/((neighbors.size() * (neighbors.size()-1))/2);
    }
    
    
    //Calculates clustering coefficient for students
    public double clusteringCoefficientStudent (List<List<String>> matchings, String name) {
        List<List<String>> filtered = new ArrayList<List<String>>();
        ArrayList<String> neighbors = new ArrayList<String>();
        for (List<String> matching: matchings) {
            if (matching.get(1).equals(name)) {
                filtered.add(matching);
                neighbors.add(matching.get(0));
            }
        }
        double count = 0.0;
        System.out.println(neighbors.toString());
        for (String neighbor: neighbors) {
            for(List<String> matching: matchings) {
                if (matching.get(0).equals(neighbor) && neighbors.contains(matching.get(1))) {
                    count++;
                }
            }
        }
        return count/((neighbors.size() * (neighbors.size()-1))/2);
    }
    
    
    //Returns a probable focal closure based on tutor-subject frequency
    public String focalClosure (List<List<String>> matchings, String subject) {
        // initialize a tutor hashmap with key = tutor name and value = # of occurrences
        Map<String, Integer> tutorMap = new HashMap<String, Integer>();
        List<List<String>> filtered = new ArrayList<List<String>>();
        for (List<String> matching: matchings) {
            if (matching.get(2).equals(subject)) {
                filtered.add(matching);
            }
        }
        for (List<String> f: filtered) {
            if (!tutorMap.containsKey(f.get(0))){
                tutorMap.put(f.get(0), 1); // create new entry
            }
            else {
                tutorMap.replace(f.get(0), tutorMap.get(f.get(0)) + 1);
            }
        }
        //System.out.println(tutorMap.toString());
        String maxKey = Collections.max(tutorMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        String maxKey2 = "";
        if (tutorMap.size() > 1) {
            tutorMap.remove(maxKey);
            maxKey2 = Collections.max(tutorMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        }
        return (maxKey + " and " + maxKey2 + " are likely to know each other through focal closure."); 
    }
    
    
    //Returns a probable membership closure based on tutor subject - student frequency
    public String membershipClosure (List<List<String>> matchings, String tName) {
     // initialize a subject hashmap with key = subject name and value = # of occurrences
        Map<String, Integer> subjectMap = new HashMap<String, Integer>();
     // initialize a student hashmap with key = student name and value = # of occurrences
        Map<String, Integer> studentMap = new HashMap<String, Integer>();
        List<List<String>> filtered = new ArrayList<List<String>>();
        for (List<String> matching: matchings) {
            if (matching.get(0).equals(tName)) {
                filtered.add(matching);
            }
        }
        for (List<String> f: filtered) {
            if (!subjectMap.containsKey(f.get(2))){
                subjectMap.put(f.get(2), 1); // create new entry
            }
            else {
                subjectMap.replace(f.get(2), subjectMap.get(f.get(2)) + 1);
            }
        }
        for (List<String> f: filtered) {
            if (!studentMap.containsKey(f.get(1))){
                studentMap.put(f.get(1), 1); // create new entry
            }
            else {
                studentMap.replace(f.get(1), studentMap.get(f.get(1)) + 1);
            }
        }
        String maxKeySubject = Collections.max(subjectMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        String maxKeyStudent = Collections.max(studentMap.entrySet(), Map.Entry.comparingByValue()).getKey();;
        return (maxKeyStudent + " is likely to have received tutoring in " + maxKeySubject + 
                " from " + tName + " based on membership closure.");
    }
    
    
    //Checks if student-tutor connection exists
    public boolean triadicClosure (List<List<String>> matchings, String tutorName, String
            studentName, String subject) {
        List<List<String>> filtered = new ArrayList<List<String>>();
        for (List<String> matching: matchings) {
            if (matching.get(0).equals(tutorName)) {
                filtered.add(matching);
            }
        }
        for (List<String> f: filtered) {
            if (f.get(1).equals(studentName) && f.get(2).equals(subject)) {
                return true;
            }
        }
        return false; 
    }
    
    
}
