package fr.cedric.haegele.mareu_application.model;

import java.util.List;

public class Meeting {

    // Name of room, Date of meeting,Beginning Meeting, End time meeting,meeting Topic,
    private String dateMeeting, hourMeeting, topicMeeting;
    private String roomName;
    private int drawable;

    // List employees
    private List<String> employee;


    // constructor

    public Meeting(String roomName, String dateMeeting, String hourMeeting, String topicMeeting, int drawable, List<String> employee) {
        this.roomName = roomName;
        this.dateMeeting = dateMeeting;
        this.hourMeeting = hourMeeting;
        this.topicMeeting = topicMeeting;
        this.drawable = drawable;
        this.employee = employee;
    }

    public Meeting(String new_york, int i, int i1, String sujet, int image1) {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String  roomName) {
        this.roomName = roomName;
    }

     public String getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(String dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public String getHourMeeting() {
        return hourMeeting;
    }

    public void setHourMeeting(String hourMeeting) {
        this.hourMeeting = hourMeeting;
    }

    public String getTopicMeeting() {
        return topicMeeting;
    }

    public void setTopicMeeting(String topicMeeting) {
        this.topicMeeting = topicMeeting;
    }

    public List<String> getEmployee() {
        return employee;
    }

    public void setEmployee(List<String> employee) {
        this.employee = employee;
    }


    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
