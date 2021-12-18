package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class ActivityAddMeetings extends AppCompatActivity {

    /** DATA */
    TextInputEditText meetingTopic, meetingDate, meetingHour, mailParticipant;
    ImageView imageTop;
    TextView txtView;
    Spinner meetingRoom;
    ChipGroup chipGroup;
    Button btnAddMail, btnAddMeeting;
    private Configuration config;

    MeetingApiService meetingApiService = DI.getMeetingApiService();
    private final List<String> mails = new ArrayList<>();
    private final Calendar calendar = Calendar.getInstance();

    /** ON CREATE */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        setGraphicElements();
        setRoomAdapter();
        defineDate();
        defineHour();
        setMailButton();
        createMeetingUnderConditions();
        config = getResources().getConfiguration();
        onConfigurationChanged(config);
        }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
           Toast.makeText(this, "landscape",Toast.LENGTH_SHORT).show();
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this,"portrait", Toast.LENGTH_SHORT).show();
        }
    }

    /** Bind views by Id */
    private void setGraphicElements() {
        meetingTopic = findViewById(R.id.meeting_topic);
        meetingDate = findViewById(R.id.meeting_date);
        meetingHour = findViewById(R.id.meeting_hour);
        mailParticipant = findViewById(R.id.mailParticipant);
        meetingRoom = findViewById(R.id.meeting_room);
        txtView = findViewById(R.id.txtVw);
        chipGroup = findViewById(R.id.chip_group);
        btnAddMail = findViewById(R.id.btn_add_mail);
        btnAddMeeting = findViewById(R.id.btn_add_meeting);
        imageTop = findViewById(R.id.imageTop);
    }

    public void setRoomAdapter(){
        //value to be shown in the spinner
        String[] rooms = {" ","New York", "Berlin", "Yaoundé", "Paris", "Rome", "Madrid", "Rio", "Vienne", "Quebec", "Dublin"};
        int[]  images = {R.drawable.image10,
                        R.drawable.image1,
                        R.drawable.image2,
                        R.drawable.image3,
                        R.drawable.image4,
                        R.drawable.image5,
                        R.drawable.image7,
                        R.drawable.image8,
                        R.drawable.image9,
        } ;



        //array adapter used to bind values in the spinner
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rooms);
        meetingRoom.setAdapter(adapter);

        //select Room and the image change
        meetingRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (String room : rooms) {
                    if (room.equals(meetingRoom.getItemAtPosition(position).toString())){
                        imageTop.setImageResource(images[position]);
                    }
                    imageTop.setImageResource(images[position]);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setSelection(images[0]);
            }
        });
    }

    /** Select a date */
    private void defineDate() {
        DatePickerDialog.OnDateSetListener date = (datePicker, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        };
        meetingDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAddMeetings.this, R.style.DialogTheme, date, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

    private void updateLabelDate() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        meetingDate.setText(sdf.format(calendar.getTime()));
    }

    /** Select an Hour */
    public void defineHour() {
        meetingHour.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityAddMeetings.this, R.style.DialogTheme, (view, hourOfDay, minute1) -> meetingHour.setText(hourOfDay + ":" + minute1), hour, minute, true);
            timePickerDialog.setTitle("Choisissez l'heure");
            timePickerDialog.show();
        });
    }

    /** Add mails of the participants with Chips */
    private void setMailButton() {
        btnAddMail.setOnClickListener(view -> {
            final Chip chip = new Chip(ActivityAddMeetings.this);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(ActivityAddMeetings.this, null,
                    0, R.style.Widget_MaterialComponents_Chip_Entry);
            chip.setChipDrawable(drawable);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setChipIconResource(R.drawable.ic_baseline_mail_24);
            chip.setIconStartPadding(3f);
            chip.setPadding(60, 10, 60, 10);
            chip.setText(Objects.requireNonNull(mailParticipant.getText()).toString());
//                chip.setOnCloseIconClickListener(view1 -> chipGroup.removeView(chip));
            chip.setOnCloseIconClickListener(view1 -> {
                chipGroup.removeView(chip);
                mails.remove(chip.getText().toString());
            });
            ifMailIsOk(chip);
        });
    }

    private void ifMailIsOk(Chip chip) {
        if (!mailIsOk(Objects.requireNonNull(mailParticipant.getText()).toString())) {
            Toast.makeText(ActivityAddMeetings.this, "Veuillez renseigner un mail valide", Toast.LENGTH_LONG).show();
        } else {
            chipGroup.addView(chip);
            mails.add(mailParticipant.getText().toString());
        }
        mailParticipant.setText("");
    }
    private boolean mailIsOk(CharSequence mailParticipant) {
        return Patterns.EMAIL_ADDRESS.matcher(mailParticipant).matches();
    }

    /** Click on Create Button to add a meeting */
    public void createMeetingUnderConditions(){
        btnAddMeeting.setOnClickListener(v -> checkInputs());
    }

    /** Validation of conditions before create meeting */
    public void checkInputs() {
        RelativeLayout roomSpinner=findViewById(R.id.relative);
        String textTopicInput = Objects.requireNonNull(meetingTopic.getText()).toString();
        String relative = roomSpinner.toString();
        String textDateInput = Objects.requireNonNull(meetingDate.getText()).toString();
        String textHourInput = Objects.requireNonNull(meetingHour.getText()).toString();
        if (textTopicInput.isEmpty()) {
            Toast.makeText(this, "Merci de renseigner votre sujet de réunion !", Toast.LENGTH_LONG).show();
            return;
        }
        if (relative.isEmpty()) {
            Toast.makeText(this, "Merci de choisir une salle !", Toast.LENGTH_LONG).show();
            return;
        }
        if (textDateInput.isEmpty()) {
            Toast.makeText(this, "Merci de renseigner une date !", Toast.LENGTH_LONG).show();
            return;
        }
        if (textHourInput.isEmpty()) {
            Toast.makeText(this, "Merci de renseigner une heure !", Toast.LENGTH_LONG).show();
            return;
        }
        if (mails.isEmpty()) {
            Toast.makeText(this, "Merci de renseigner votre ou vos emails de participant(s) !", Toast.LENGTH_LONG).show();
            return;
        }

        createMeeting();
    }

    /** Create Meeting */
    private void createMeeting() {
        Meeting meeting = new Meeting(
                Objects.requireNonNull(meetingTopic.getText()).toString(),
                meetingRoom.getSelectedItem().toString(),
                meetingDate.getEditableText().toString(),
                meetingHour.getEditableText().toString(),
                mails);

                meetingApiService.createMeeting(meeting);
        Toast.makeText(ActivityAddMeetings.this.getApplicationContext(), "La réunion a été ajoutée", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(ActivityAddMeetings.this, ActivityListMeeting.class);
        startActivity(intent);
    }
}











