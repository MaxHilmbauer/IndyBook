package mh.indybook.indy.model;

import org.json.JSONObject;

public class IndyTeacher {


    private String id;
    private String firstname;
    private String lastname;
    private String room;
    private boolean consultation;
    private int studentAmount;
    private int studentLimit;

    public IndyTeacher(JSONObject object) {
        this.id = object.getString("tid");
        this.firstname = object.getString("firstname");
        this.lastname = object.getString("lastname");
        this.room = object.getString("room");
        this.consultation = Boolean.getBoolean(object.getString("consultation"));
        if (object.getString("studentAmount").equals("") || object.getString("limit").equals("")) {
            this.studentAmount = 0;
            this.studentLimit = 0;
        } else {
            this.studentAmount = Integer.parseInt(object.getString("studentAmount"));
            this.studentLimit = Integer.parseInt(object.getString("limit"));
        }

    }

    public IndyTeacher() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isConsultation() {
        return consultation;
    }

    public void setConsultation(boolean consultation) {
        this.consultation = consultation;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }
}
