package fr.cedric.haegele.mareu_application.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.cedric.haegele.mareu_application.R;

public class ActivityListMeeting extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton createMeetingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        recyclerView = findViewById(R.id.recyclerview);
        createMeetingBtn = findViewById(R.id.create_meeting_btn);

        createMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListMeeting.this, ActivityAddMeetings.class);
                startActivity(intent);
            }
        });

    }

}