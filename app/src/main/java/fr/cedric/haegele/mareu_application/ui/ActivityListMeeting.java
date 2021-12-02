package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class ActivityListMeeting extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MeetingApiService apiService = DI.getMeetingApiService();
    private Configuration config;
    FloatingActionButton createMeetingBtn;
    List<Meeting> meeting = new ArrayList<>(apiService.getMeetings());
    MeetingRecyclerViewAdapter adapter = new MeetingRecyclerViewAdapter(meeting);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        recyclerView = findViewById(R.id.recyclerView);
        createMeetingBtn = findViewById(R.id.create_meeting_btn);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initList();
        config = getResources().getConfiguration();
        landscape();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.Filtrer_par_salle);

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
                filterByRoom();
                Toast.makeText(this, "Sélection par Salle", Toast.LENGTH_LONG).show();
                break;
            case R.id.Réinitiliser:
                filterReset();
                Toast.makeText(this, "Réinitialiser", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void filterReset() {
        meeting.clear();
        meeting.addAll(apiService.getMeetings());
        adapter.notifyDataSetChanged();
    }


    private void filterByRoom() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setTitle("Salles de réunions");
        String rooms[] = {"New York", "Berlin", "Yaoundé", "Paris", "Rome", "Madrid", "Rio", "Vienne", "Quebec", "Dublin",};
        materialAlertDialogBuilder.setItems(rooms, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                for (String room : rooms) {
                    room = rooms[i];
                    adapter = new MeetingRecyclerViewAdapter(meeting);
                    recyclerView.setAdapter(adapter);
                    meeting.clear();
                    meeting.addAll(apiService.filterByRoom(room));

                    adapter.notifyDataSetChanged();

                }

            }
        });

        materialAlertDialogBuilder.show();
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
                try {
                    meeting = apiService.filterByDate(calendar.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter = new MeetingRecyclerViewAdapter(meeting);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

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

    //Landscape Orientation
    public void landscape() {
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            meeting.clear();
            apiService.getMeetings().clear();
        }
    }

}










