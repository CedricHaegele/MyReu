package fr.cedric.haegele.mareu_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Rooms;

public class ActivityAddMeetings extends AppCompatActivity {
   TextInputEditText meetingTopic,meetingDate,meetingHour,mailParticipant;
   AutoCompleteTextView meetingRoom;
   ChipGroup chipGroup;
   Button btnAddMail, btnAddMeeting;
   MeetingApiService meetingApiService;
   DatePickerDialog.OnDateSetListener onDateSetListener;
   private ArrayList<String> mails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        meetingTopic=findViewById(R.id.meeting_topic);
        meetingDate=findViewById(R.id.meeting_date);
        meetingHour=findViewById(R.id.meeting_hour);
        mailParticipant= findViewById(R.id.mailParticipant);
        meetingRoom=findViewById(R.id.meeting_room);
        chipGroup=findViewById(R.id.chip_group);
        btnAddMail=findViewById(R.id.btn_add_mail);
        btnAddMeeting=findViewById(R.id.btn_add_meeting);

        meetingApiService = DI.getMeetingApiService();
        getDate();
        setMailButton();


        meetingHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute =calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ActivityAddMeetings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        meetingHour.setText(hourOfDay +":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.setTitle("Choisir l'heure");
                timePickerDialog.show();

            }

        });
    }

    private void getDate() {
        meetingDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAddMeetings.this,R.style.AppTheme,
                    onDateSetListener, year, month, day);
            datePickerDialog.show();

        });
        onDateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String date = dayOfMonth + "/" + month + "/" + year;
            meetingDate.setText(date);

        };
    }

    private boolean mailIsOk(CharSequence mailParticipant) {
        return Patterns.EMAIL_ADDRESS.matcher(mailParticipant).matches();
    }

    private void ifMailIsOk(Chip chip){
        if (!mailIsOk(mailParticipant.getText().toString())) {
            Toast.makeText(ActivityAddMeetings.this, "Veuillez renseigner un mail valide", Toast.LENGTH_LONG).show();
        } else {
            chipGroup.addView(chip);
            mails.add(mailParticipant.getText().toString());
        }
        mailParticipant.setText("");
    }


    private void setMailButton() {

        btnAddMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Chip chip = new Chip(ActivityAddMeetings.this);
                ChipDrawable drawable = ChipDrawable.createFromAttributes(ActivityAddMeetings.this, null,
                        0, R.style.Widget_MaterialComponents_Chip_Entry);
                chip.setChipDrawable(drawable);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setChipIconResource(R.drawable.ic_baseline_mail_24);
                chip.setIconStartPadding(3f);
                chip.setPadding(60, 10, 60, 10);
                chip.setText(mailParticipant.getText().toString());
//                chip.setOnCloseIconClickListener(view1 -> chipGroup.removeView(chip));
                chip.setOnCloseIconClickListener(view1 -> {
                    chipGroup.removeView(chip);
                    mails.remove(chip.getText().toString());
                });

                ifMailIsOk(chip);
            }
        });
    }

    public void setSpinner() {
        ArrayAdapter<Rooms> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, meetingApiService.getRooms());
        meetingRoom.setAdapter(adapter);
        adapter.notifyDataSetChanged();
            }
    ;

        }



