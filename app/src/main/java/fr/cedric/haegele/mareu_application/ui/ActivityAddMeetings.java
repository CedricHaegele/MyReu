package fr.cedric.haegele.mareu_application.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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
   ImageView imageTop;
   AutoCompleteTextView meetingRoom;
   ChipGroup chipGroup;
   Button btnAddMail, btnAddMeeting;
   MeetingApiService meetingApiService;
   DatePickerDialog.OnDateSetListener onDateSetListener;
   private ArrayList<String> mails = new ArrayList<>();
   String[] Rooms;




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
        imageTop=findViewById(R.id.imageTop);


        meetingApiService = DI.getMeetingApiService();
        getDate();
        setMailButton();


        //value to be shown in the spinner
        String str []={"New York","Berlin","Yaoundé","Paris","Rome","Madrid","Rio","Vienne","Quebec","Dublin"};

        //array adapter used to bind values in the spinner
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_dropdown_item,str);
        meetingRoom.setAdapter(adapter);

        //select Room and the image change
        meetingRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (str[0].equals(meetingRoom.getOnItemClickListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image);}
                else if(str[1].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image1);}
                else if(str[2].equals(meetingRoom.getOnItemClickListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image2);
                }else if(str[3].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image3);
                }else if(str[4].equals(meetingRoom.getOnItemClickListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image4);
                }else if(str[5].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image5);
                }else if(str[6].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image7);
                }else if(str[7].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image8);
                }else if(str[8].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image9);
                }else if(str[9].equals(meetingRoom.getOnItemSelectedListener().toString()))
                {
                    imageTop.setImageResource(R.drawable.image10);}}


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
            }



