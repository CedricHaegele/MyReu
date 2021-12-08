package fr.cedric.haegele.mareu_application.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;

/** Meeting API client */
public interface MeetingApiService {

    /** Get all meetings */
    List<Meeting> getMeetings();

    /** Delete a meeting */
    void deleteMeeting(Meeting meeting);

    /** Create a meeting */
    void createMeeting(Meeting meeting);

    /** Filter our meeting list by date */
    List<Meeting> filterByDate(Date dateFilter) throws ParseException;

    /** Filter our meeting list by room */
    List<Meeting> filterByRoom(String room);
}