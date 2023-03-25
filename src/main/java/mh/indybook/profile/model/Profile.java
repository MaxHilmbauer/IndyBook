package mh.indybook.profile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile {

    private ArrayList<String> subjectActivity = new ArrayList<>();
    private HashMap<Integer, String> rooms = new HashMap<>();
    private HashMap<Integer, String> teachers = new HashMap<>();

    public Profile() {
    }

    public HashMap<Integer, String> getPriorities() {
        HashMap<Integer, String> priorities = new HashMap<>();

        priorities.putAll(rooms);
        priorities.putAll(teachers);
        for (Map.Entry<Integer, String> entry : priorities.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        return priorities;
    }

    public ArrayList<String> getSubjectActivity() {
        return subjectActivity;
    }

    public void setSubjectActivity(ArrayList<String> subjectActivity) {
        this.subjectActivity = subjectActivity;
    }

    public HashMap<Integer, String> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Integer, String> rooms) {
        this.rooms = rooms;
    }

    public HashMap<Integer, String> getTeachers() {
        return teachers;
    }

    public void setTeachers(HashMap<Integer, String> teachers) {
        this.teachers = teachers;
    }
}
