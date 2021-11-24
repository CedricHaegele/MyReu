package fr.cedric.haegele.mareu_application.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;

/**
 * Meeting API client
 */

public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetingsFilteredByDate(Date date);

    List<Meeting> filterByDate(Date dateFilter);

    List<Meeting> filterByRoom(String room);
}
