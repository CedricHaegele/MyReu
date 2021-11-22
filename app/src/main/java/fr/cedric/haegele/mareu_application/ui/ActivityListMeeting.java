package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class ActivityListMeeting extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MeetingApiService apiService;
    FloatingActionButton createMeetingBtn;
    MeetingRecyclerViewAdapter adapter;
    List<Meeting> meeting;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        recyclerView = findViewById(R.id.recyclerView);
        createMeetingBtn = findViewById(R.id.create_meeting_btn);
        apiService = DI.getMeetingApiService();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Filtrer_par_date:
                filterByDate();
                Toast.makeText(this, "Sélection par date", Toast.LENGTH_LONG).show();
                break;
            case R.id.Filtrer_par_salle:

                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.Réinitiliser:

                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterByDate() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date;
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                meeting = MeetingApiService.filterByDate(calendar.getTime());
                adapter.setData(meeting);

            }

            public void filterByRoom(){
recyclerView.setAdapter(adapter);
            }
        };



        DatePickerDialog myDatePicker = new DatePickerDialog(ActivityListMeeting.this, R.style.DialogTheme, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        myDatePicker.show();

    }

    public void initList() {
        List<Meeting> meetingList = apiService.getMeetings();
        MeetingRecyclerViewAdapter adapter = new MeetingRecyclerViewAdapter(meetingList);
        recyclerView.setAdapter(adapter);


        createMeetingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityListMeeting.this, ActivityAddMeetings.class);
            startActivity(intent);

        });
    }

}









