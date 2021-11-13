package fr.cedric.haegele.mareu_application.Service;

import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.model.Rooms;

public interface MeetingApiService {

    /**
     *  Get all my rooms
     *  @return {@link List}
     */

    List<Rooms> getRooms();

    /**
     *  Get all my meeting
     *  @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     *  Delete a meeting from my list
     *  @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     *  Add a new meeting to my list
     *  @param meeting
     */
    void addMeeting(Meeting meeting);

   }
