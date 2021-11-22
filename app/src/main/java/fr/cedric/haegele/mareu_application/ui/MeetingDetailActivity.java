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
        Meeting meeting = getIntent().getParcelableExtra("MEETINGS_DATA");

        title=findViewById(R.id.titleRecup);
        roomR=findViewById(R.id.roomRecup);
        dateR=findViewById(R.id.meetingDateRecup);
        hourR=findViewById(R.id.meetingHourRecup);
        mailsR=findViewById(R.id.mailsParticipantsRecup);

        title.setText(meeting.getTopicMeeting());
        roomR.setText(meeting.getRoomName());
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        String date = format.format(meeting.getDateMeeting());
        dateR.setText(date);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String hour = tf.format(meeting.getHourMeeting());
        hourR.setText(hour);
        mailsR.setText(meeting.getMails());

    }
}
