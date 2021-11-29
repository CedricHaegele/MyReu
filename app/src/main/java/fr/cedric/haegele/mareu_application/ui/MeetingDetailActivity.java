package fr.cedric.haegele.mareu_application.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class MeetingDetailActivity extends AppCompatActivity {
    TextView title,roomR, dateR,hourR,mailsR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail_activity);
        Meeting meeting = getIntent().getParcelableExtra("DATA_MEETINGS");

        title=findViewById(R.id.titleRecup);
        roomR=findViewById(R.id.roomRecup);
        dateR=findViewById(R.id.meetingDateRecup);
        hourR=findViewById(R.id.meetingHourRecup);
        mailsR=findViewById(R.id.mailsParticipantsRecup);

        title.setText(meeting.getTopicMeeting());
        roomR.setText(meeting.getRoomName());
        dateR.setText(meeting.getDateMeeting());
        hourR.setText(meeting.getHourMeeting());

        mailsR.setText(meeting.getMails().toString()
                .substring(1, meeting.getMails().toString().length() - 1));

    }
}
