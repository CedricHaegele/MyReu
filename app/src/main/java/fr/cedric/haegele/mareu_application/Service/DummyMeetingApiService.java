package fr.cedric.haegele.mareu_application.Service;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;

/** Dummy mock for the Api */

public class DummyMeetingApiService implements MeetingApiService {

    /** Return all meetings */
    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /** Get all meetings */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /** Delete a meeting */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /** Create a meeting */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /** Filter our meeting list by date */
    @Override
    public List<Meeting> filterByDate(Date dateFilter) throws ParseException {

        Calendar cal1= Calendar.getInstance();
        cal1.setTime(dateFilter);
        int day= cal1.get(Calendar.DAY_OF_MONTH);
        int month= cal1.get(Calendar.MONTH);
        int year= cal1.get(Calendar.YEAR);

        ArrayList<Meeting> newMeetings= new ArrayList<>();

        for (int i = 0; i < meetings.size(); i++ ) {
            String dateS = meetings.get(i).getDateMeeting();


            @SuppressLint("SimpleDateFormat") Date dateD = new SimpleDateFormat("dd/MM/yy").parse(dateS);
            assert dateD != null;
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dateD);
            int day2= cal2.get(Calendar.DAY_OF_MONTH);
            int month2= cal2.get(Calendar.MONTH);
            int year2= cal2.get(Calendar.YEAR);

            if(day==day2 && month==month2 && year==year2){
                newMeetings.add(meetings.get(i));
            }
        }
        return newMeetings;
    }

    /** Filter our meeting list by room */
    @Override
    public List<Meeting> filterByRoom(String location) {
        List<Meeting> roomFilter = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getRoomName().equals(location)) {
                roomFilter.add(meeting);
            }
        }
        return roomFilter;
    }
}


