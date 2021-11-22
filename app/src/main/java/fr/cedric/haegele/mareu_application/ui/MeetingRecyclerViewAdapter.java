package fr.cedric.haegele.mareu_application.ui;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    List<Meeting> mMeetings;

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

    }


    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    void update(List<Meeting> meetings) {
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }
    public interface OnMeetingClickListener {
        void onMeetingClicked(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView photo;
        public TextView title, room;

        public TextView mail;
        public ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            title = itemView.findViewById(R.id.title);
            room = itemView.findViewById(R.id.room);

            mail = itemView.findViewById(R.id.mail);
            delete = itemView.findViewById(R.id.delete);

        }

        public void displayMeeting(Meeting meeting) {
            photo.setColorFilter(meeting.getDrawable());
            title.setText(meeting.getTopicMeeting());
            mail.setText(meeting.getMails());
            room.setText(meeting.getRoomName());

        }}

        /** Update mReunion **/
        void setData(List<Meeting> list){
            this.mMeetings = list;
            notifyDataSetChanged();

    }
}
