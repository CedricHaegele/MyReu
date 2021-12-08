package fr.cedric.haegele.mareu_application.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.cedric.haegele.mareu_application.model.Meeting;

public class DummyMeetingGenerator {

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Organisation", "New York", "01/10/2021", "10:00", Collections.singletonList("Bob@lamzone.fr, Gerard@lamzone")),
            new Meeting("Planning", "Vienne", "15/12/2021", "16:00", Collections.singletonList("Rob@lamzone.fr, Francois@lamzone")),
            new Meeting("Projet", "Madrid", "22/05/2021", "18:00", Collections.singletonList("John@lamzone.fr")),
            new Meeting("Coordination", "Rio", "02/03/2021", "09:00", Collections.singletonList("Hervé@lamzone.fr")),
            new Meeting("Organisation", "New York", "01/10/2021", "10:00", Collections.singletonList("Bob@lamzone.fr, Gerard@lamzone")),
            new Meeting("Planning", "Vienne", "15/12/2021", "16:00", Collections.singletonList("Rob@lamzone.fr, Francois@lamzone")),
            new Meeting("Projet", "Berlin", "22/05/2021", "18:00", Collections.singletonList("John@lamzone.fr"))
    );
}
