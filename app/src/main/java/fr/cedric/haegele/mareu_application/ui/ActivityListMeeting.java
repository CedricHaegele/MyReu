package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    private MeetingApiService apiService= DI.getMeetingApiService();
    FloatingActionButton createMeetingBtn;
    List<Meeting> meeting=new ArrayList<>(apiService.getMeetings());
    MeetingRecyclerViewAdapter adapter=new MeetingRecyclerViewAdapter(meeting);
    SearchView searchView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        recyclerView = findViewById(R.id.recyclerView);
        createMeetingBtn = findViewById(R.id.create_meeting_btn);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem=menu.findItem(R.id.Filtrer_par_salle);
        searchView = (SearchView) searchItem.getActionView();
        setSearchViewListener();

        return true;
    }

    public void setSearchViewListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Filtrer_par_date:
                filterByDate();
                Toast.makeText(this, "Sélection par date", Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityListMeeting.this);
        alertDialog.setTitle("Salles de réunions");
        String items[] = {"New York", "Berlin", "Yaoundé", "Paris", "Rome", "Madrid", "Rio", "Vienne", "Quebec", "Dublin",};
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(ActivityListMeeting.this, "New York", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(ActivityListMeeting.this, "Berlin", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(ActivityListMeeting.this, "Yaoundé", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(ActivityListMeeting.this, "Paris", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(ActivityListMeeting.this, "Rome", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(ActivityListMeeting.this, "Madrid", Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(ActivityListMeeting.this, "Rio", Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        Toast.makeText(ActivityListMeeting.this, "Vienne", Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        Toast.makeText(ActivityListMeeting.this, "Quebec", Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        Toast.makeText(ActivityListMeeting.this, "Dublin", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
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

    public void onMeetingClicked(int position) {
        Meeting meeting = apiService.getMeetings().get(position);
        Intent i = new Intent(this, MeetingDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("DATA_MEETING", meeting);
        i.putExtras(bundle);
        startActivity(i);

    }
}










