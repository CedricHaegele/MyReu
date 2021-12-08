package fr.cedric.haegele.mareu_application.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class MeetingDetailActivity extends AppCompatActivity {

    /** DATA */
    TextView title, roomR, dateR, hourR, mailsR;
    Meeting meeting;

    /** ON CREATE */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail_activity);

        /* Get Intent that returns meeting data by Parcelable method */
        meeting = getIntent().getParcelableExtra("DATA_MEETINGS");

        setGraphicElements();
        setTextsElements();
    }

    /** Bind views by Id */
    private void setGraphicElements() {
        title = findViewById(R.id.titleRecup);
        roomR = findViewById(R.id.roomRecup);
        dateR = findViewById(R.id.meetingDateRecup);
        hourR = findViewById(R.id.meetingHourRecup);
        mailsR = findViewById(R.id.mailsParticipantsRecup);
    }

    /** Set Texts to Graphic Elements */
    private void setTextsElements() {
        title.setText(meeting.getTopicMeeting());
        roomR.setText(meeting.getRoomName());
        dateR.setText(meeting.getDateMeeting());
        hourR.setText(meeting.getHourMeeting());
        mailsR.setText(meeting.getMails().toString()
                .substring(1, meeting.getMails().toString().length() - 1));
    }
}
