package fr.cedric.haegele.mareu_application.Service;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.ui.ActivityListMeeting;

/**
 * Dummy mock for the Api
 */

public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

        /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     *
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public ArrayList<Meeting> getMeetingsFilteredByDate(Date date) {
        return null;
    }

    @Override
    public List<Meeting> filterByDate(Date dateFilter){

        Calendar cal= Calendar.getInstance();
        cal.setTime(dateFilter);
        int day= cal.get(Calendar.DAY_OF_MONTH);
        int month= cal.get(Calendar.MONTH);
        int year= cal.get(Calendar.YEAR);

        ArrayList<Meeting> newMeetings= new ArrayList<>();

        for (Meeting element: meetings) {
            Date dat= element.getDateMeeting();
            Calendar cal2= Calendar.getInstance();
            cal2.setTime(dat);
            int day2= cal2.get(Calendar.DAY_OF_MONTH);
            int month2= cal2.get(Calendar.MONTH);
            int year2= cal2.get(Calendar.YEAR);

            if(day==day2 && month==month2 && year==year2){
                newMeetings.add(element);
            }
        }
        return newMeetings;
    }


      @Override
    public List<Meeting> filterByRoom(String room) {
        return null;
    }

}




