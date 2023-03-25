package mh.indybook.indy.model;

import org.json.JSONObject;

import java.util.ArrayList;

public class IndyEventDetailed extends IndyEvent {

    private ArrayList<IndySubject> subjects = new ArrayList<>();
    private ArrayList<IndyTeacher> teachers_hour1 = new ArrayList<>();
    private ArrayList<IndyTeacher> teachers_hour2 = new ArrayList<>();

    public IndyEventDetailed(IndyEvent event, JSONObject jsonObject) {
        super(event);
        jsonObject.getJSONArray("subjects").forEach(subject -> {
            this.subjects.add(new IndySubject((String) subject));
        });
        jsonObject.getJSONObject("teachers").getJSONObject(event.getDay()).getJSONArray("3").forEach(teacher -> {
            this.teachers_hour1.add(new IndyTeacher((JSONObject) teacher));
        });
        jsonObject.getJSONObject("teachers").getJSONObject(event.getDay()).getJSONArray("4").forEach(teacher -> {
            this.teachers_hour2.add(new IndyTeacher((JSONObject) teacher));
        });
    }

    public IndyEventDetailed(IndyEvent event, ArrayList<IndySubject> subjects, ArrayList<IndyTeacher> teachers_hour1, ArrayList<IndyTeacher> teachers_hour2) {
        super(event);
        this.subjects = subjects;
        this.teachers_hour1 = teachers_hour1;
        this.teachers_hour2 = teachers_hour2;
    }

    public IndyEventDetailed(IndyEvent indyEvent) {
        super(indyEvent);
    }

    public ArrayList<IndySubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<IndySubject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<IndyTeacher> getTeachers_hour1() {
        return teachers_hour1;
    }

    public void setTeachers_hour1(ArrayList<IndyTeacher> teachers_hour1) {
        this.teachers_hour1 = teachers_hour1;
    }

    public ArrayList<IndyTeacher> getTeachers_hour2() {
        return teachers_hour2;
    }

    public void setTeachers_hour2(ArrayList<IndyTeacher> teachers_hour2) {
        this.teachers_hour2 = teachers_hour2;
    }
}
