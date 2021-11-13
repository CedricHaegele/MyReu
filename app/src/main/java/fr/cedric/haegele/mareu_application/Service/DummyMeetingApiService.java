package fr.cedric.haegele.mareu_application.Service;

import java.util.Collections;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.model.Rooms;

public class
DummyMeetingApiService implements MeetingApiService {
    private List<Meeting> meetings = Collections.emptyList();
    List<Rooms> rooms = DummyMeetingGenerator.generateRooms();

    @Override
    public List<Rooms> getRooms() {
        return rooms;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }


}
