package fr.cedric.haegele.mareu_application.Service;

import android.icu.text.TimeZoneFormat;

import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

            new Meeting("Hello World","New York","12/11/2021","12:00", Collections.singletonList("zikced@lamzone.fr"))

    );


    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
