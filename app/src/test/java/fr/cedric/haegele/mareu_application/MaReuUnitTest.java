package fr.cedric.haegele.mareu_application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.Service.DummyMeetingGenerator;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class MaReuUnitTest {

    private MeetingApiService service;

    private Meeting meetingTest = new Meeting("Organisation", "New York", "01012021", "1200", Collections.singletonList("Hervé@lamzone.fr"));

    @Before
    public void setUp() {
        service = DI.getNewInstanceMeetingApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetingList = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertEquals(meetingList, expectedMeetings);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        service.getMeetings();
        Meeting meetingToDelete = service.getMeetings().get(3);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        service.createMeeting(meetingTest);
        List<Meeting> meetingList = service.getMeetings();
        assertTrue(meetingList.contains(meetingTest));

    }

    @Test
    public void filterByRoomWithSuccess() {
        List<Meeting> filteredList = service.filterByRoom("New York");
        List<Meeting> newMeetings = new ArrayList<>();

        do {
            newMeetings.addAll(filteredList);

        } while (newMeetings.equals(filteredList));
        {
            assertEquals(2, filteredList.size());
        }
    }

    @Test
    public void filterByDateWithSuccess() throws ParseException {
        String str = "15/12/2021";
        List<Meeting> allMeetings= service.getMeetings();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(str);

        List<Meeting> filteredListDate = service.filterByDate(date);
        List<Meeting> newMeetings = new ArrayList<>();

        if (allMeetings.containsAll(filteredListDate)){
            newMeetings.addAll(filteredListDate);
        }

        assertEquals("15/12/2021",newMeetings.get(0).getDateMeeting());
        assertEquals("15/12/2021",newMeetings.get(1).getDateMeeting());
        assertNotEquals("15/12/2021",allMeetings.get(0).getDateMeeting());
    }
}