package fr.cedric.haegele.mareu_application.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;


public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    List<Meeting> mMeetings;
    public MeetingApiService apiService = DI.getMeetingApiService();



    public MeetingRecyclerViewAdapter(List<Meeting> meetings) {
        this.mMeetings = meetings;
            }

    @NonNull
    @Override
    public MeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingRecyclerViewAdapter.ViewHolder holder, int position) {

        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(mMeetings.get(position));

        //Delete a meeting
        holder.delete.setOnClickListener(v -> {
            mMeetings.remove(meeting);
            apiService.deleteMeeting(meeting);
            notifyItemRemoved(position);
            Toast.makeText(v.getContext(), "La réunion " + meeting.getTopicMeeting() + " a été supprimée", Toast.LENGTH_SHORT).show();
        });

        // Access to Detail
holder.itemView.setOnClickListener(v -> {
    Intent intent = new Intent(v.getContext(), MeetingDetailActivity.class);
    intent.putExtra("DATA_MEETINGS",meeting);
    v.getContext().startActivity(intent);

});

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView photo;
        public TextView title, room, hour,date;
        public TextView mail;
        public ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.title);
            room = itemView.findViewById(R.id.room);
            hour = itemView.findViewById(R.id.hour);
            date = itemView.findViewById(R.id.date);
            mail = itemView.findViewById(R.id.mail);
            delete = itemView.findViewById(R.id.delete);

        }
        public void displayMeeting(Meeting meeting) {
            photo.setColorFilter(meeting.getDrawable());
            title.setText(meeting.getTopicMeeting());
            hour.setText(meeting.getHourMeeting());
            mail.setText(meeting.getMails().toString()
                    .substring(1, meeting.getMails().toString().length() - 1));
            date.setText(meeting.getDateMeeting());
            room.setText(meeting.getRoomName());

        }

    }

    };




