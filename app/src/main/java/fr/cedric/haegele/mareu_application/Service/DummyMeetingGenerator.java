package fr.cedric.haegele.mareu_application.Service;

import android.icu.text.TimeZoneFormat;

import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("lionel","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(14112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(14112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr"),
            new Meeting("Hello World","New York",new Date(12112021),new Time(1200),"zikced@lamzone.fr")

    );


    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
