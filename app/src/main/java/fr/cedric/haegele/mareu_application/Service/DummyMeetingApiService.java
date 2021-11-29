package fr.cedric.haegele.mareu_application.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;

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
    public List<Meeting> filterByDate(Date dateFilter) throws ParseException {

        Calendar cal= Calendar.getInstance();
        cal.setTime(dateFilter);
        int day= cal.get(Calendar.DAY_OF_MONTH);
        int month= cal.get(Calendar.MONTH);
        int year= cal.get(Calendar.YEAR);



            ArrayList<Meeting> newMeetings= new ArrayList<>();

        for (int i = 0; i < meetings.size(); i++ ) {
            Calendar cal2 = Calendar.getInstance();
            String dateS = meetings.get(i).getDateMeeting();


            Date dateD = new SimpleDateFormat("dd/MM/yy").parse(dateS);
            assert dateD != null;
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


    @Override
    public List<Meeting> filterByRoom(String room) {
        return null;
    }

}


