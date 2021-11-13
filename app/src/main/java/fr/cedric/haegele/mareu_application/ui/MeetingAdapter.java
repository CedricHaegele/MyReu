package fr.cedric.haegele.mareu_application.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private final ArrayList<Meeting> meetings;


    public MeetingAdapter (ArrayList<Meeting>meetings){
        this.meetings=meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Meeting meeting = meetings.get(position);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView circle;
        public final TextView meetingName;
        public final TextView meetingMail;
        public final ImageView delete;


        public ViewHolder(@NonNull View view) {
            super(view);
            circle = view.findViewById(R.id.photo);
            meetingName = view.findViewById(R.id.title);
            meetingMail = view.findViewById(R.id.mails);
            delete = view.findViewById(R.id.delete);


        }}}
